package nanodegree.android.nba.ui.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import nanodegree.android.nba.BuildConfig;
import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.dao.DailyScheduleAggDao;
import nanodegree.android.nba.persistence.dao.FavoriteTeamDao;
import nanodegree.android.nba.persistence.dao.GameAggDao;
import nanodegree.android.nba.persistence.db.AppDatabase;
import nanodegree.android.nba.persistence.entity.DailyScheduleAgg;
import nanodegree.android.nba.persistence.entity.FavoriteTeam;
import nanodegree.android.nba.persistence.entity.GameAgg;
import nanodegree.android.nba.rest.ApiUtils;
import nanodegree.android.nba.rest.response.boxScore.BoxScore;
import nanodegree.android.nba.rest.response.dailySchedule.DailySchedule;
import nanodegree.android.nba.rest.response.dailySchedule.Game;
import nanodegree.android.nba.rest.response.standing.Conference;
import nanodegree.android.nba.rest.response.standing.Division;
import nanodegree.android.nba.rest.response.standing.Standing;
import nanodegree.android.nba.rest.response.standing.Team;
import nanodegree.android.nba.utils.DisplayDateUtils;
import nanodegree.android.nba.utils.DisplayMetricUtils;


public class GameFragment extends Fragment
    implements LoaderManager.LoaderCallbacks<DailyScheduleAgg> {

    private String TAG = GameFragment.class.getSimpleName();
    public static HashMap<String, String> recordMap = new HashMap<String, String>();

    private static final int GAME_FRAGMENT_LOADER = 22;
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
    private FloatingActionButton fab;
    private OnFragmentInteractionListener mListener;

    private int cardWidthInDp;
    private int cardHeightInDp;
    private GameAdapter mGameAdapter;

    private DailyScheduleAggDao dailyScheduleAggDao;
    private GameAggDao gameAggDao;
    private FavoriteTeamDao favoriteTeamDao;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        year = getArguments().getInt(YEAR);
        month = getArguments().getInt(MONTH) + 1;
        day = getArguments().getInt(DAY);
        tabName = getArguments().getString(TAB_NAME);
        tabIndex = getArguments().getInt(TAB_INDEX);
        loadData = getArguments().getBoolean(LOAD_DATA);
        filterTeams = getArguments().getBoolean(FILTER_TEAMS);

        dailyScheduleAggDao = AppDatabase.getInstance(mContext).dailyScheduleAggDao();
        gameAggDao = AppDatabase.getInstance(mContext).gameAggDao();
        favoriteTeamDao = AppDatabase.getInstance(mContext).gameFavoriteTeamDao();
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
        fab = rootView.findViewById(R.id.fab);

        setupClickListeners(tabName, tabIndex);
        setupRecyclerView();

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupRecyclerView() {
        int marginInDp = 8;
        int marginInPixel = DisplayMetricUtils.convertDpToPixel(8);
        int deviceWidthInDp = DisplayMetricUtils.convertPixelsToDp(
                DisplayMetricUtils.getDeviceWidth(getActivity()));

        int column = deviceWidthInDp / 300;
        int totalMarginInDp = marginInDp * (column + 1);
        cardWidthInDp = (deviceWidthInDp - totalMarginInDp) / column;
        cardHeightInDp = 120;

        RecyclerViewMarginDecoration decoration =
                new RecyclerViewMarginDecoration(RecyclerViewMarginDecoration.ORIENTATION_VERTICAL,
                        marginInPixel, column);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, column,
                LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setHasFixedSize(true);
        mGameAdapter =
                new GameAdapter(Objects.requireNonNull(mContext),
                        cardWidthInDp,
                        cardHeightInDp);
        mRecyclerView.setAdapter(mGameAdapter);

        getAllGames();
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

        switch (tabIndex) {
            case 0:
                fab.setVisibility(View.GONE);
                break;
            case 1:
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), MyGameActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
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

    private void getAllGames() {
        if(loadData) {
            Bundle allGamesBundle = new Bundle();
            LoaderManager loaderManager = getLoaderManager();
            Loader<DailyScheduleAgg> loader = loaderManager.getLoader(GAME_FRAGMENT_LOADER);
            if (loader == null) {
                loaderManager.initLoader(GAME_FRAGMENT_LOADER, allGamesBundle, this);
            } else {
                loaderManager.restartLoader(GAME_FRAGMENT_LOADER, allGamesBundle, this);
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<DailyScheduleAgg> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<DailyScheduleAgg>(this.getContext()) {
            DailyScheduleAgg resultFromDailyScheduleAgg;

            @Override
            public DailyScheduleAgg loadInBackground() {
                try {
                    DailyScheduleAgg dailyScheduleAgg = null;

                    Calendar todayCal = Calendar.getInstance();

                    Calendar requestedDateCal = Calendar.getInstance();
                    requestedDateCal.set(year, month-1, day);

                    if (requestedDateCal.before(todayCal)) {
                        dailyScheduleAgg = getDailyScheduleAggFromDb();
                    } else {
                        dailyScheduleAgg = getDailyScheduleAggFromNetwork();
                    }

                    if(filterTeams) {
                        dailyScheduleAgg = filterMyTeams(dailyScheduleAgg);
                    }

                    return dailyScheduleAgg;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onStartLoading() {
                disableView();
                if(resultFromDailyScheduleAgg != null) {
                    // To skip loadInBackground call
                    deliverResult(resultFromDailyScheduleAgg);
                } else {
                    forceLoad();
                }
            }

            @Override
            public void deliverResult(DailyScheduleAgg data) {
                resultFromDailyScheduleAgg = data;
                super.deliverResult(data);
            }

            private DailyScheduleAgg filterMyTeams(DailyScheduleAgg dailyScheduleAgg) {
                ArrayList<GameAgg> gameAggList = new ArrayList<GameAgg>();
                List<FavoriteTeam> favoriteTeams = favoriteTeamDao.getAllSelectedFavoriteTeams(true);
                ArrayList<GameAgg> games = dailyScheduleAgg.getGames();

                for(GameAgg gameAgg : games) {
                  for(FavoriteTeam favoriteTeam : favoriteTeams) {
                      if(favoriteTeam.getAlias().equals(gameAgg.getAwayAlias()) ||
                              favoriteTeam.getAlias().equals(gameAgg.getHomeAlias())) {
                          gameAggList.add(gameAgg);
                      }
                  }
                }

                dailyScheduleAgg.setGames(gameAggList);
                return dailyScheduleAgg;
            }

            private DailyScheduleAgg getDailyScheduleAggFromDb()
                    throws ParseException, InterruptedException {
                String id = createDailyScheduleAggId();

                DailyScheduleAgg dailyScheduleAgg =
                        dailyScheduleAggDao.getDailyScheduleAggById(id);

                if(dailyScheduleAgg == null) {
                    // cache DailyScheduleAgg into db
                    insertDailyScheduleAggIntoDb();

                    // build dailyScheduleAgg object from db
                    dailyScheduleAgg = dailyScheduleAggDao.getDailyScheduleAggById(id);
                    dailyScheduleAgg = addGameAgg(dailyScheduleAgg, id);

                    return dailyScheduleAgg;
                } else {
                    // build dailyScheduleAgg object from db
                    dailyScheduleAgg = addGameAgg(dailyScheduleAgg, id);
                    return dailyScheduleAgg;
                }
            }

            private DailyScheduleAgg addGameAgg(DailyScheduleAgg dailyScheduleAgg, String id) {
                List<GameAgg> gameAgg = gameAggDao.getGameAggByDailyScheduleId(id);
                dailyScheduleAgg.setGames((ArrayList<GameAgg>)gameAgg);
                return dailyScheduleAgg;
            }

            private void insertDailyScheduleAggIntoDb() throws ParseException, InterruptedException {
                DailyScheduleAgg dailyScheduleAgg = getDailyScheduleAggFromNetwork();
                dailyScheduleAggDao.insert(dailyScheduleAgg);
                for(GameAgg gameAgg : dailyScheduleAgg.getGames()) {
                    gameAggDao.insert(gameAgg);
                }
            }

            private DailyScheduleAgg getDailyScheduleAggFromNetwork()
                    throws ParseException, InterruptedException {
                try {
                    DailyScheduleAgg dailyScheduleAgg = new DailyScheduleAgg();
                    ArrayList<GameAgg> gameAggs = new ArrayList<GameAgg>();

                    Thread.sleep(delay * 1000);

                    DailySchedule dailySchedule = ApiUtils.getGameService()
                            .getGameScheduleByDate(mContext.getString(R.string.language_code),
                                    year, month, day, mContext.getString(R.string.format),
                                    BuildConfig.NBA_DB_API_KEY).blockingGet();

                    dailyScheduleAgg.setId(dailySchedule.getDate());

                    List<Game> games = dailySchedule.getGames();
                    for(Game game : games) {
                        gameAggs.add(createGameAgg(game, dailyScheduleAgg.getId()));
                    }

                    dailyScheduleAgg.setGames(gameAggs);

                    return dailyScheduleAgg;
                } catch(Exception e) {
                    throw e;
                }
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<DailyScheduleAgg> loader, DailyScheduleAgg data) {
        if (data != null) {
            if(data.getGames().isEmpty()) {
                mRecyclerView.setVisibility(View.GONE);
                noGames.setVisibility(View.VISIBLE);
            } else {
                noGames.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
            mGameAdapter.setGames(data.getGames());
        } else {
            Toast.makeText(this.getContext(),
                    mContext.getString(R.string.no_connection),
                    Toast.LENGTH_LONG).show();
        }
        enableView();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<DailyScheduleAgg> loader) {}

    private GameAgg createGameAgg(Game game, String date)
            throws InterruptedException, ParseException {
        GameAgg gameAgg = new GameAgg();
        gameAgg.setId(game.getId());
        gameAgg.setDailyScheduleDate(date);
        gameAgg.setStatus(game.getStatus());
        gameAgg.setScheduled(game.getScheduled());
        gameAgg.setBroadcast(game.getBroadcasts().get(0).getNetwork());
        gameAgg.setAwayAlias(game.getAway().getAlias());
        gameAgg.setAwayName(game.getAway().getName());
        gameAgg.setHomeAlias(game.getHome().getAlias());
        gameAgg.setHomeName(game.getHome().getName());

        Thread.sleep(delay * 1000);

        BoxScore boxScore = ApiUtils.getGameService()
                .getBoxScore("en", gameAgg.getId(),
                        mContext.getString(R.string.format),
                        BuildConfig.NBA_DB_API_KEY).blockingGet();

        if(gameAgg.getStatus().equals(mContext.getString(R.string.scheduled))) {
            gameAgg.setTimeOnClock(getGameStartTime(game));
        } else if(gameAgg.getStatus().equals(mContext.getString(R.string.inprogress))) {
            gameAgg.setTimeOnClock(getClockTime(boxScore));
            gameAgg.setAwayPoints(String.valueOf(boxScore.getAway().getPoints()));
            gameAgg.setHomePoints(String.valueOf(boxScore.getHome().getPoints()));
        } else if(gameAgg.getStatus().equals(mContext.getString(R.string.closed))) {
            gameAgg.setTimeOnClock(mContext.getString(R.string.finish));
            gameAgg.setAwayPoints(String.valueOf(boxScore.getAway().getPoints()));
            gameAgg.setHomePoints(String.valueOf(boxScore.getHome().getPoints()));
        }

        if(recordMap.isEmpty()) {
            Thread.sleep(delay * 1000);

            Standing standing =
                    ApiUtils.getGameService().getStanding(mContext.getString(R.string.language_code),
                    2018,
                    mContext.getString(R.string.season) ,mContext.getString(R.string.format),
                    BuildConfig.NBA_DB_API_KEY).blockingGet();

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
        }

        return gameAgg;
    }

    private String getGameStartTime(Game game) throws ParseException {
        DateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX",
                        Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone(mContext.getString(R.string.gmt)));
        Date date = dateFormat.parse(game.getScheduled());
        DateFormat d =
                new SimpleDateFormat(mContext.getString(
                        R.string.display_date_pattern), Locale.ENGLISH);
        d.setTimeZone(TimeZone.getTimeZone(mContext.getString(R.string.est)));
        return d.format(date) + " " + mContext.getString(R.string.est);
    }

    private String getClockTime(BoxScore boxScore) {
        StringBuilder sb = new StringBuilder();
        sb.append("Q");
        sb.append(boxScore.getQuarter());
        sb.append(" ");
        sb.append(boxScore.getClock());
        return sb.toString();
    }

    private String createDailyScheduleAggId() {
        StringBuilder id = new StringBuilder();
        id.append(year);
        id.append("-");
        id.append(String.format(Locale.ENGLISH, "%02d", month));
        id.append("-");
        id.append(String.format(Locale.ENGLISH, "%02d", day));
        return id.toString();
    }
}