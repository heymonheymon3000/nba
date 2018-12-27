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
import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.pojo.response.boxScore.BoxScore;
import nanodegree.android.nba.persistence.pojo.response.dailySchedule.DailySchedule;
import nanodegree.android.nba.persistence.pojo.response.dailySchedule.Game;
import nanodegree.android.nba.persistence.pojo.response.standing.Conference;
import nanodegree.android.nba.persistence.pojo.response.standing.Division;
import nanodegree.android.nba.persistence.pojo.response.standing.Standing;
import nanodegree.android.nba.persistence.pojo.response.standing.Team;
import nanodegree.android.nba.rest.ApiUtils;
import nanodegree.android.nba.rest.GameService;
import nanodegree.android.nba.utils.DisplayDateUtils;
import nanodegree.android.nba.utils.DisplayMetricUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GameFragment extends Fragment {

    private String TAG = GameFragment.class.getSimpleName();

    private GameAdapter mGameAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private TextView mGameDateTextView;
    private ProgressBar spinner;
    private ImageView mBackNavImageView;
    private ImageView mForwardNavImageView;
    private LinearLayout linearLayout;
    public final static String YEAR = "YEAR";
    public final static String MONTH = "MONTH";
    public final static String DAY = "DAY";

    public Integer year;
    public Integer month;
    public Integer day;

    private final static Integer delay = 1;

    private OnFragmentInteractionListener mListener;

    private CompositeDisposable disposable = new CompositeDisposable();
    private ArrayList<Game> gamesList = new ArrayList<>();

    private HashMap<String, String> recordMap = new HashMap<String, String>();

    public static GameFragment newInstance(Calendar cal) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putInt(YEAR, cal.get(Calendar.YEAR));
        args.putInt(MONTH, cal.get(Calendar.MONTH));
        args.putInt(DAY, cal.get(Calendar.DATE));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() == null) {
            Calendar cal = DisplayDateUtils.getCurrentDate(DisplayDateUtils.GAME);
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH)+1;
            day = cal.get(Calendar.DATE);
        } else {
            year = getArguments().getInt(YEAR);
            month = getArguments().getInt(MONTH)+1;
            day = getArguments().getInt(DAY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.fragment_game, container, false);
        spinner = rootView.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        mRecyclerView = rootView.findViewById(R.id.rv_game_card);
        mGameDateTextView = rootView.findViewById(R.id.game_date);

        mGameDateTextView.setText(DisplayDateUtils.getTodayDate(DisplayDateUtils.GAME));

        mBackNavImageView = rootView.findViewById(R.id.back_image_view);
        mBackNavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameDateTextView.setText(DisplayDateUtils.getYesterdayDate(DisplayDateUtils.GAME));
                if (mListener != null) {
                    mListener.updateFragment(0);
                }
            }
        });

        mForwardNavImageView = rootView.findViewById(R.id.forward_image_view);
        mForwardNavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameDateTextView.setText(DisplayDateUtils.getTomorrowDate(DisplayDateUtils.GAME));
                if (mListener != null) {
                    mListener.updateFragment(0);
                }
            }
        });

        linearLayout = rootView.findViewById(R.id.linearLayout);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    @SuppressLint("CheckResult")
    private void setupRecyclerView() {
        int marginInDp = 8;
        int marginInPixel = DisplayMetricUtils.convertDpToPixel(8);
        int deviceWidthInDp = DisplayMetricUtils.convertPixelsToDp(
                DisplayMetricUtils.getDeviceWidth(getActivity()));

        int column = deviceWidthInDp / 300;
        int totalMarginInDp = marginInDp * (column + 1);
        int cardWidthInDp = (deviceWidthInDp - totalMarginInDp) / column;
        int cardHeightInDp = 100;//(int) (2.0f / 3.0f * cardWidthInDp);

        RecyclerViewMarginDecoration decoration =
                new RecyclerViewMarginDecoration(RecyclerViewMarginDecoration.ORIENTATION_VERTICAL,
                        marginInPixel, column);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, column,
                LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(decoration);
        mGameAdapter =
                new GameAdapter(Objects.requireNonNull(mContext), cardWidthInDp,
                        cardHeightInDp);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGameAdapter);

        ConnectableObservable<List<Game>> gamesObservable = getGamesObservable().replay();

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

                        mGameAdapter.setGames(gamesList);
                        mGameAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );

        /**
         * Fetching individual team box score
         * First FlatMap converts single List<Game> to multiple emissions
         * Second FlatMap makes HTTP call on each Game emission
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
                        mGameAdapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onComplete() {
                        enableView();
                    }
                }));

        disableView();

        disposable.add(
            getTeamStandingObservable().
                subscribe(new Consumer<HashMap<String, String>>() {
                    @Override
                    public void accept(HashMap<String, String> stringStringHashMap) throws Exception {
                        Thread.sleep(1000);
                        gamesObservable.connect();
                    }
                })
        );
    }

    /**
     * Making Retrofit call to fetch all games for a given day
     */
    private Observable<List<Game>> getGamesObservable() {
        return ApiUtils.getGameService().getGameScheduleByDate("en",
            year, month, day, ".json",BuildConfig.NBA_DB_API_KEY)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .delay(delay, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .map(new Function<DailySchedule, List<Game>>() {
                @Override
                public List<Game> apply(DailySchedule dailySchedule) throws Exception {
                    return dailySchedule.getGames();
                }
            });
    }

    /**
     * Making Retrofit call to get single Box Score
     * get Box Score HTTP call returns Box Score object, but
     * map() operator is used to change the return type to Game
     */
    private Observable<Game> getBoxScoreObservable(final Game game) {
        return ApiUtils.getGameService()
            .getBoxScore("en", game.getId(), ".json", BuildConfig.NBA_DB_API_KEY)
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
                    return game;
                }
            });
    }

    /**
     * Gets Team standing
     */
    private Observable<HashMap<String, String>> getTeamStandingObservable() {

        Log.i("CALLED", "getTeamStandingObservable was called");
        return ApiUtils.getGameService().getStanding("en", 2018,
                "REG",".json", BuildConfig.NBA_DB_API_KEY)
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
                                    String key = GameActivity.teamLookup.get(team.getName());
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

    /**
     * Snackbar shows observer error
     */
    private void showError(Throwable e) {
        Log.e(TAG, "showError: " + e.getMessage() + " GAME_FRAGMENT");

//        Snackbar snackbar = Snackbar
//                .make(coordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
//        View sbView = snackbar.getView();
//        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.YELLOW);
//        snackbar.show();
    }
}