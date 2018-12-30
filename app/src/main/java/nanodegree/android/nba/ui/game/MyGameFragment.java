package nanodegree.android.nba.ui.game;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
import nanodegree.android.nba.utils.DisplayDateUtils;
import nanodegree.android.nba.utils.DisplayMetricUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyGameFragment extends BaseFragment {
    private String TAG = MyGameFragment.class.getSimpleName();

    private MyGameAdapter mGameAdapter;
    public Boolean loadData;

    public static MyGameFragment newInstance(Calendar cal) {
        MyGameFragment fragment = new MyGameFragment();
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

        if (getArguments() == null) {
            Calendar cal = DisplayDateUtils.getCurrentDate(DisplayDateUtils.MY_GAME);
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
            day = cal.get(Calendar.DATE);
            loadData = false;
        } else {
            year = getArguments().getInt(YEAR);
            month = getArguments().getInt(MONTH) + 1;
            day = getArguments().getInt(DAY);
            loadData = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupClickListeners();
        setupRecyclerView();
        fetchData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Making Retrofit call to fetch all games for a given day
     */
    protected Observable<List<Game>> getGamesObservable() {
        return ApiUtils.getGameService().getGameScheduleByDate("en",
            year, month, day, ".json", BuildConfig.NBA_DB_API_KEY)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
                    if(game.getAway().getAlias().equals("CHI") ||
                            game.getHome().getAlias().equals("CHI")) {
                        return true;
                    }
                    return false;
                }
            })
            .toList()
            .toObservable();
    }

    private void setupClickListeners() {
        mGameDateTextView.setText(DisplayDateUtils.getTodayDate(DisplayDateUtils.MY_GAME));
        mBackNavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameDateTextView.setText(DisplayDateUtils.getYesterdayDate(DisplayDateUtils.MY_GAME));
                if (mListener != null) {
                    mListener.updateFragment(1);
                }
            }
        });

        mForwardNavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameDateTextView.setText(DisplayDateUtils.getTomorrowDate(DisplayDateUtils.MY_GAME));
                if (mListener != null) {
                    mListener.updateFragment(1);
                }
            }
        });
    }

    private void fetchData() {
        mGameAdapter =
                new MyGameAdapter(Objects.requireNonNull(mContext), cardWidthInDp,
                        cardHeightInDp);
        mRecyclerView.setAdapter(mGameAdapter);

        if(loadData) {
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
                        }

                        @Override
                        public void onError(Throwable e) {
                            showError(e);
                        }

                        @Override
                        public void onComplete() {}
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
                        Thread.sleep(delay * 1500);
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
    }
}
