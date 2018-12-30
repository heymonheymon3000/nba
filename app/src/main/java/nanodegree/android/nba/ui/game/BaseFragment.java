package nanodegree.android.nba.ui.game;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
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
import nanodegree.android.nba.utils.DisplayDateUtils;
import nanodegree.android.nba.utils.DisplayMetricUtils;

public abstract class BaseFragment extends Fragment {
    protected final static Integer delay = 1;

    protected final static String YEAR = "YEAR";
    protected final static String MONTH = "MONTH";
    protected final static String DAY = "DAY";

    protected Integer year;
    protected Integer month;
    protected Integer day;

    protected RecyclerView mRecyclerView;
    protected Context mContext;
    protected TextView mGameDateTextView;
    protected ProgressBar spinner;
    protected ImageView mBackNavImageView;
    protected ImageView mForwardNavImageView;
    protected LinearLayout linearLayout;

    protected OnFragmentInteractionListener mListener;

    protected CompositeDisposable disposable =
            new CompositeDisposable();
    protected ArrayList<Game> gamesList =
            new ArrayList<>();

    protected HashMap<String, String> recordMap =
            new HashMap<String, String>();

    protected int cardWidthInDp;
    protected int cardHeightInDp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        spinner = rootView.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        mRecyclerView = rootView.findViewById(R.id.rv_game_card);
        mGameDateTextView = rootView.findViewById(R.id.game_date);
        mBackNavImageView = rootView.findViewById(R.id.back_image_view);
        mForwardNavImageView = rootView.findViewById(R.id.forward_image_view);
        linearLayout = rootView.findViewById(R.id.linearLayout);

        return rootView;
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

    protected void setupRecyclerView() {
        int marginInDp = 8;
        int marginInPixel = DisplayMetricUtils.convertDpToPixel(8);
        int deviceWidthInDp = DisplayMetricUtils.convertPixelsToDp(
                DisplayMetricUtils.getDeviceWidth(getActivity()));

        int column = deviceWidthInDp / 300;
        int totalMarginInDp = marginInDp * (column + 1);
        cardWidthInDp = (deviceWidthInDp - totalMarginInDp) / column;
        cardHeightInDp = 100;//(int) (2.0f / 3.0f * cardWidthInDp);

        RecyclerViewMarginDecoration decoration =
                new RecyclerViewMarginDecoration(RecyclerViewMarginDecoration.ORIENTATION_VERTICAL,
                        marginInPixel, column);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, column,
                LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setHasFixedSize(true);
    }

    /**
     * Making Retrofit call to fetch all games for a given day
     */
    protected Observable<List<Game>> getGamesObservable() {
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
    protected Observable<Game> getBoxScoreObservable(final Game game) {
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
    protected Observable<HashMap<String, String>> getTeamStandingObservable() {
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

    protected void disableView() {
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

    protected void enableView() {
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
    protected void showError(Throwable e) {
//        Log.e(TAG, "showError: " + e.getMessage() + " GAME_FRAGMENT");

//        Snackbar snackbar = Snackbar
//                .make(coordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
//        View sbView = snackbar.getView();
//        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.YELLOW);
//        snackbar.show();
    }
}
