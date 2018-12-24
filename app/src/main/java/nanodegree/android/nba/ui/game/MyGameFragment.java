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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import nanodegree.android.nba.BuildConfig;
import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.pojo.response.boxScore.BoxScore;
import nanodegree.android.nba.persistence.pojo.response.dailySchedule.DailySchedule;
import nanodegree.android.nba.persistence.pojo.response.dailySchedule.Game;
import nanodegree.android.nba.rest.ApiUtils;
import nanodegree.android.nba.rest.GameService;
import nanodegree.android.nba.utils.DisplayDateUtils;
import nanodegree.android.nba.utils.DisplayMetricUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyGameFragment extends Fragment {
    private String TAG = MyGameFragment.class.getSimpleName();

    private MyGameAdapter mGameAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private TextView mGameDateTextView;
    private ProgressBar spinner;
    private ImageView mBackNavImageView;
    private ImageView mForwardNavImageView;
    private GameService gameService;

    public final static String YEAR = "YEAR";
    public final static String MONTH = "MONTH";
    public final static String DAY = "DAY";



    public Integer year;
    public Integer month;
    public Integer day;

    private final static Integer delay = 2000;

    private OnFragmentInteractionListener mListener;





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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameService = ApiUtils.getGameService();

        if(getArguments() == null) {
            Calendar cal = DisplayDateUtils.getCurrentDate(DisplayDateUtils.MY_GAME);
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH)+1;
            day = cal.get(Calendar.DATE);
        } else {
            year = getArguments().getInt(YEAR);
            month = getArguments().getInt(MONTH)+1;
            day = getArguments().getInt(DAY);
        }

//        Log.i("NUMBERS", "YEAR=> " + year + " MONTH=> " + month + " DAY=> " + day);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.fragment_my_game, container, false);
        spinner = rootView.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        mRecyclerView = rootView.findViewById(R.id.rv_game_card);
        mGameDateTextView = rootView.findViewById(R.id.game_date);

        mGameDateTextView.setText(DisplayDateUtils.getTodayDate(DisplayDateUtils.MY_GAME));

        mBackNavImageView = rootView.findViewById(R.id.back_image_view);
        mBackNavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameDateTextView.setText(DisplayDateUtils.getYesterdayDate(DisplayDateUtils.MY_GAME));
                if (mListener != null) {
                    mListener.onFragmentInteraction2();
                }
            }
        });

        mForwardNavImageView = rootView.findViewById(R.id.forward_image_view);
        mForwardNavImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameDateTextView.setText(DisplayDateUtils.getTomorrowDate(DisplayDateUtils.MY_GAME));
                if (mListener != null) {
                    mListener.onFragmentInteraction2();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupModelView();
    }



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
                new MyGameAdapter(Objects.requireNonNull(mContext), cardWidthInDp,
                        cardHeightInDp);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mGameAdapter);
    }

    private void getBoxScore(final List<Game> games) {
//        Handler handler = new Handler();
//        for (int i = 1; i <= games.size(); i++) {
//            final Game game = games.get(i-1);
//            // The REST API that we are using only allows request every 1 second; otherwise the result will be unsuccessfully.
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    gameService.getBoxScore("en", game.getId(), ".json", BuildConfig.NBA_DB_API_KEY).enqueue(new Callback<BoxScore>() {
//                        @Override
//                        public void onResponse(Call<BoxScore> call, Response<BoxScore> boxScoreResponse) {
//                            BoxScore boxScore = boxScoreResponse.body();
//
//                            game.setGameStatus(boxScore.getClock());
//                            game.setAwayPoints(boxScore.getAway().getPoints());
//                            game.setHomePoints(boxScore.getHome().getPoints());
//
//                            mGameAdapter.setGames(games);
//                        }
//
//                        @Override
//                        public void onFailure(Call<BoxScore> call, Throwable t) {
//                            Log.i(TAG, "FAILED " + t.getMessage());
//                        }
//                    });
//                }
//            }, delay * i);
//        }
//
//        Handler h = new Handler();
//        h.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                enableView();
//            }
//        }, (delay * (games.size()+1)));
    }

    private void setupModelView() {
        disableView();

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                gameService.getDailySchedule("en", year,
//                        month, day, ".json", BuildConfig.NBA_DB_API_KEY).enqueue(new Callback<DailySchedule>() {
//                    @Override
//                    public void onResponse(@NonNull Call<DailySchedule> call, @NonNull Response<DailySchedule> response) {
//                        if(response.isSuccessful()) {
//                            getBoxScore(response.body().getGames());
//                        } else {
//                            enableView();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<DailySchedule> call,@NonNull Throwable t) {
//                        Toast.makeText(mContext,
//                                "Something went wrong, please check your internet connection and try again! " + t.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                        enableView();
//                    }
//                });
//            }
//        }, delay);
    }

    private void disableView() {
        mBackNavImageView.setClickable(false);
        mBackNavImageView.setClickable(false);
        mBackNavImageView.setImageAlpha(50);

        mForwardNavImageView.setEnabled(false);
        mForwardNavImageView.setEnabled(false);
        mForwardNavImageView.setImageAlpha(50);

        mRecyclerView.setAlpha(Float.valueOf("0.5"));
        spinner.setVisibility(View.VISIBLE);
    }



    private void enableView() {
        mBackNavImageView.setClickable(true);
        mBackNavImageView.setClickable(true);
        mBackNavImageView.setImageAlpha(255);

        mForwardNavImageView.setEnabled(true);
        mForwardNavImageView.setEnabled(true);
        mForwardNavImageView.setImageAlpha(255);

        spinner.setVisibility(View.GONE);
        mRecyclerView.setAlpha(1);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction2();
    }
}
