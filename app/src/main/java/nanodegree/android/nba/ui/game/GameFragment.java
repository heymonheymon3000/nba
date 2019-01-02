package nanodegree.android.nba.ui.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import nanodegree.android.nba.BuildConfig;
import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.R;
import nanodegree.android.nba.rest.response.boxScore.BoxScore;
import nanodegree.android.nba.rest.response.dailySchedule.DailySchedule;
import nanodegree.android.nba.rest.response.dailySchedule.Game;
import nanodegree.android.nba.rest.response.standing.Conference;
import nanodegree.android.nba.rest.response.standing.Division;
import nanodegree.android.nba.rest.response.standing.Standing;
import nanodegree.android.nba.rest.response.standing.Team;
import nanodegree.android.nba.rest.ApiUtils;
import nanodegree.android.nba.rest.GameService;
import nanodegree.android.nba.utils.DisplayDateUtils;
import nanodegree.android.nba.utils.DisplayMetricUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import io.reactivex.functions.Predicate;

public class GameFragment extends Fragment {
    private String TAG = GameFragment.class.getSimpleName();

    private final static Integer delay = 1;

    private final static String YEAR = "YEAR";
    private final static String MONTH = "MONTH";
    private final static String DAY = "DAY";
    private final static String LOAD_DATA = "LOAD_DATA";
    private final static String FILTER_TEAMS = "FILTER_TEAMS";
    private final static String TAB_NAME = "TAB_NAME";
    private final static String TAB_INDEX = "TAB_INDEX";
    private Integer year;
    private Integer month;
    private Integer day;
    private String  tabName;
    private Boolean loadData;
    private Boolean filterTeams;
    private Integer tabIndex;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private TextView mGameDateTextView;
    private ProgressBar spinner;
    private ImageView mBackNavImageView;
    private ImageView mForwardNavImageView;
    private LinearLayout linearLayout;
    private TextView noGames;
    private OnFragmentInteractionListener mListener;
    private CompositeDisposable disposable =
            new CompositeDisposable();
    private ArrayList<Game> gamesList =
            new ArrayList<>();
    private HashMap<String, String> recordMap =
            new HashMap<String, String>();
    private int cardWidthInDp;
    private int cardHeightInDp;
    private GameAdapter mGameAdapter;


    public static GameFragment newInstance(String tabName, Integer tabIndex,
                                           Calendar cal, Boolean loadData,
                                           Boolean filterTeams) {
        GameFragment fragment = new GameFragment();

        Bundle args = new Bundle();
        args.putInt(YEAR, cal.get(Calendar.YEAR));
        args.putInt(MONTH, cal.get(Calendar.MONTH));
        args.putInt(DAY, cal.get(Calendar.DATE));
        args.putString(TAB_NAME, tabName);
        args.putInt(TAB_INDEX, tabIndex);
        args.putBoolean(LOAD_DATA, loadData);
        args.putBoolean(FILTER_TEAMS, filterTeams);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        year = getArguments().getInt(YEAR);
        month = getArguments().getInt(MONTH) + 1;
        day = getArguments().getInt(DAY);
        tabName = getArguments().getString(TAB_NAME);
        tabIndex = getArguments().getInt(TAB_INDEX);
        loadData = getArguments().getBoolean(LOAD_DATA);
        filterTeams = getArguments().getBoolean(FILTER_TEAMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        spinner = rootView.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        mRecyclerView = rootView.findViewById(R.id.rv_game_card);
        mGameDateTextView = rootView.findViewById(R.id.game_date);
        mBackNavImageView = rootView.findViewById(R.id.back_image_view);
        mForwardNavImageView = rootView.findViewById(R.id.forward_image_view);
        linearLayout = rootView.findViewById(R.id.linearLayout);
        noGames = rootView.findViewById(R.id.no_games);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupClickListeners(tabName, tabIndex);
        setupRecyclerView();
        fetchData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        if (mContext instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) mContext;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private void setupRecyclerView() {
        int marginInDp = 8;
        int marginInPixel = DisplayMetricUtils.convertDpToPixel(8);
        int deviceWidthInDp = DisplayMetricUtils.convertPixelsToDp(
                DisplayMetricUtils.getDeviceWidth(getActivity()));

        int column = deviceWidthInDp / 300;
        int totalMarginInDp = marginInDp * (column + 1);
        cardWidthInDp = (deviceWidthInDp - totalMarginInDp) / column;
        cardHeightInDp = 100; //(int) (2.0f / 3.0f * cardWidthInDp);

        RecyclerViewMarginDecoration decoration =
                new RecyclerViewMarginDecoration(RecyclerViewMarginDecoration.ORIENTATION_VERTICAL,
                        marginInPixel, column);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, column,
                LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setHasFixedSize(true);
    }

    private void setupClickListeners(String tabName, Integer index) {
        mGameDateTextView.setText(DisplayDateUtils.getTodayDate(tabName));
        mBackNavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameDateTextView.setText(DisplayDateUtils.getYesterdayDate(tabName));
                if (mListener != null) {
                    mListener.updateFragment(index);
                }
            }
        });

        mForwardNavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameDateTextView.setText(DisplayDateUtils.getTomorrowDate(tabName));
                if (mListener != null) {
                    mListener.updateFragment(index);
                }
            }
        });
    }

    private void fetchData() {
        mGameAdapter =
                new GameAdapter(Objects.requireNonNull(mContext),
                        cardWidthInDp,
                        cardHeightInDp);
        mRecyclerView.setAdapter(mGameAdapter);

        if(loadData) {
            disableView();

            ConnectableObservable<List<Game>> gamesObservable =
                    getGamesObservable().replay();

            /**
             * Fetching all games for a given day
             * Observable emits List<Game> at once
             * All the items will be added to RecyclerView
             * */
            disposable.add(
                gamesObservable
                    .delay(delay, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<Game>>(){
                        @Override
                        public void onNext(List<Game> games) {
                            if(games.size() == 0) {
                                enableView();
                            }

                            // Refreshing list
                            gamesList.clear();
                            gamesList.addAll(games);
                        }

                        @Override
                        public void onError(Throwable e) {
                            showError(e);
                        }

                        @Override
                        public void onComplete() {
                            if(gamesList.isEmpty()) {
                                mRecyclerView.setVisibility(View.GONE);
                                noGames.setVisibility(View.VISIBLE);
                            } else {
                                noGames.setVisibility(View.GONE);
                                mRecyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    })
            );

            /**
             * Fetching individual team box score
             * First FlatMap converts single List<Game> to multiple emissions
             * Second concatMap makes HTTP call on each Game emission
             * */
            disposable.add(
                gamesObservable
                    .subscribeOn(Schedulers.io())
                    .delay(delay, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    /**
                     * Converting List<Game> emission to single Game emissions
                     * */
                    .flatMap(new Function<List<Game>, ObservableSource<Game>>() {
                        @Override
                        public ObservableSource<Game> apply(List<Game> games) throws Exception {
                            return Observable.fromIterable(games);                    }
                    })
                    /**
                     * Fetching Box Score on each Game emission on at a time
                     * */
                    .concatMap(new Function<Game, ObservableSource<Game>>() {
                        @Override
                        public ObservableSource<Game> apply(Game game) throws Exception {
                            /**
                             * Need to wait here for a hot second because there is a
                             * time limit of 1 second before hitting the API again.
                             */
                            Thread.sleep(delay * 1000);
                            return getBoxScoreObservable(game);
                        }
                    })
                    .subscribeWith(new DisposableObserver<Game>() {
                        @Override
                        public void onNext(Game game) {
                            int position = gamesList.indexOf(game);

                            if (position == -1) {
                                // TODO - take action
                                // Game not found in the list
                                // This shouldn't happen
                                return;
                            }

                            gamesList.set(position, game);
                            mGameAdapter.setGames(gamesList);
                        }

                        @Override
                        public void onError(Throwable e) {
                            showError(e);
                        }

                        @Override
                        public void onComplete() {
                            mGameAdapter.notifyDataSetChanged();
                            enableView();
                        }
                    }));

            disposable.add(
                getTeamStandingObservable().
                    subscribe(new Consumer<HashMap<String, String>>() {
                        @Override
                        public void accept(HashMap<String, String> stringStringHashMap)
                                throws Exception {
                            Thread.sleep(1000);
                            gamesObservable.connect();
                        }
                    })
            );
        }
    }

    /**
     * Making Retrofit call to fetch all games for a given day
     */
    private Observable<List<Game>> getGamesObservable() {
        return ApiUtils.getGameService().getGameScheduleByDate(mContext.getString(R.string.language_code),
        year, month, day, mContext.getString(R.string.format),BuildConfig.NBA_DB_API_KEY)
        .toObservable()
        .subscribeOn(Schedulers.io())
        .delay(delay, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .map(new Function<DailySchedule, List<Game>>() {
            @Override
            public List<Game> apply(DailySchedule dailySchedule) throws Exception {
                return dailySchedule.getGames();
            }
        })
        .flatMap(new Function<List<Game>, ObservableSource<Game>>() {
            @Override
            public ObservableSource<Game> apply(List<Game> games) throws Exception {
                return Observable.fromIterable(games);
            }
        })
        .filter(new Predicate<Game>() {
            @Override
            public boolean test(Game game) throws Exception {
                if(filterTeams) {
                    if(game.getAway().getAlias().equals("CHI") ||
                            game.getHome().getAlias().equals("CHI")) {
                        return true;
                    }
                    return false;
                } else {
                    return true;
                }
            }
        })
        .toList()
        .toObservable();
    }

    /**
     * Making Retrofit call to get single Box Score
     * get Box Score HTTP call returns Box Score object, but
     * map() operator is used to change the return type to Game
     */
    private Observable<Game> getBoxScoreObservable(final Game game) {
        return ApiUtils.getGameService()
        .getBoxScore(mContext.getString(R.string.language_code), game.getId(),
                mContext.getString(R.string.format), BuildConfig.NBA_DB_API_KEY)
        .toObservable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Function<BoxScore, Game>() {
            @Override
            public Game apply(BoxScore boxScore) throws Exception {
                game.setGameStatus(boxScore.getClock());
                game.setAwayPoints(boxScore.getAway().getPoints());
                game.setHomePoints(boxScore.getHome().getPoints());
                game.setAwayRecord(recordMap.get(game.getAway().getAlias()));
                game.setHomeRecord(recordMap.get(game.getHome().getAlias()));

                if(boxScore.getStatus().equals("inprogress")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Q");
                    sb.append(boxScore.getQuarter());
                    sb.append(" ");
                    sb.append(boxScore.getClock());
                    game.setStatus("inprogress");
                    game.setTimeOnClock(sb.toString());
                } else if(boxScore.getStatus().equals("halftime")) {
                    game.setStatus("halftime");
                    game.setTimeOnClock("halftime");
                }
                return game;
            }
        });
    }

    /**
     * Gets Team standing
     */
    private Observable<HashMap<String, String>> getTeamStandingObservable() {
        return ApiUtils.getGameService().getStanding(mContext.getString(R.string.language_code), 2018,
                mContext.getString(R.string.season) ,mContext.getString(R.string.format),
                BuildConfig.NBA_DB_API_KEY)
        .toObservable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Function<Standing, HashMap<String, String>>() {
            @Override
            public HashMap<String, String> apply(Standing standing) throws Exception {
                recordMap.clear();
                for(Conference conference : standing.getConferences()) {
                    for(Division division : conference.getDivisions()) {
                        for(Team team : division.getTeams()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("(");
                            sb.append(Integer.toString(team.getWins()));
                            sb.append("-");
                            sb.append(Integer.toString(team.getLosses()));
                            sb.append(")");
                            String key = NBAApplication.teamLookup.get(team.getName());
                            recordMap.put(key, sb.toString());
                        }
                    }
                }
                return recordMap;
            }
        });
    }

    private void disableView() {
        mBackNavImageView.setClickable(false);
        mBackNavImageView.setClickable(false);
        mBackNavImageView.setImageAlpha(50);

        mForwardNavImageView.setEnabled(false);
        mForwardNavImageView.setEnabled(false);
        mForwardNavImageView.setImageAlpha(50);

        linearLayout.setAlpha(Float.parseFloat(
                getContext().getString(R.string.disable_alpha_value)));
        spinner.setVisibility(View.VISIBLE);

        mListener.enableTabs(false);
    }

    private void enableView() {
        mBackNavImageView.setClickable(true);
        mBackNavImageView.setClickable(true);
        mBackNavImageView.setImageAlpha(255);

        mForwardNavImageView.setEnabled(true);
        mForwardNavImageView.setEnabled(true);
        mForwardNavImageView.setImageAlpha(255);

        spinner.setVisibility(View.GONE);
        linearLayout.setAlpha(Float.parseFloat(
                getContext().getString(R.string.enable_alpha_value)));

        mListener.enableTabs(true);
    }

    /**
     * Snackbar shows observer error
     */
    private void showError(Throwable e) {
//        Log.e(TAG, "showError: " + e.getMessage() + " GAME_FRAGMENT");

//        Snackbar snackbar = Snackbar
//                .make(coordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
//        View sbView = snackbar.getView();
//        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.YELLOW);
//        snackbar.show();
    }
}