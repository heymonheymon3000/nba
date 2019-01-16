package nanodegree.android.nba.ui.gameDetail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.entity.GameAgg;
import nanodegree.android.nba.ui.game.TabAdapter;
import nanodegree.android.nba.utils.DisplayMetricUtils;
import nanodegree.android.nba.utils.TeamInfo;
import nanodegree.android.nba.utils.Utils;

public class GameDetailFragment extends Fragment {
    public final static String GAME_AGG_KEY = "gameAgg";
    private GameAgg gameAgg;
    private ImageView awayImageView;
    private ImageView homeImageView;
    private Picasso picassoInstance;
    private TextView awayTeamScore;
    private TextView homeTeamScore;
    private TextView mGameStatusTextView;
    private TextView mHomeTeamName;
    private TextView mAwayTeamName;
    private TabAdapter adapter;
    private TabLayout tabLayout;

    public GameDetailFragment() {}

    public static GameDetailFragment newInstance(GameAgg gameAgg) {
        GameDetailFragment fragment = new GameDetailFragment();
        fragment.setRetainInstance(true);
        Bundle args = new Bundle();
        args.putParcelable(GAME_AGG_KEY, gameAgg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gameAgg = getArguments().getParcelable(GAME_AGG_KEY);
        }

        picassoInstance =
                new Picasso.Builder(getContext())
                        .loggingEnabled(true)
                        .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        Toolbar mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        TeamInfo awayTeamInfo = NBAApplication.teamInfoHashMap.get(gameAgg.getAwayAlias());
        TeamInfo homeTeamInfo = NBAApplication.teamInfoHashMap.get(gameAgg.getHomeAlias());

        awayImageView = rootView.findViewById(R.id.away_team_logo_image_view);
        homeImageView = rootView.findViewById(R.id.home_team_logo_image_view);
        mGameStatusTextView = rootView.findViewById(R.id.game_status_text_view);
        mHomeTeamName = rootView.findViewById(R.id.home_team_logo_text_view);
        mAwayTeamName = rootView.findViewById(R.id.away_team_logo_text_view);

        picassoInstance
                .load(awayTeamInfo.getLogo())
                .resize(DisplayMetricUtils.convertDpToPixel(60),
                        DisplayMetricUtils.convertDpToPixel(60))
                .centerCrop()
                .into(awayImageView);

        picassoInstance
                .load(homeTeamInfo.getLogo())
                .resize(DisplayMetricUtils.convertDpToPixel(60),
                        DisplayMetricUtils.convertDpToPixel(60))
                .centerCrop()
                .into(homeImageView);

        if(gameAgg.getAwayPoints() != null && gameAgg.getHomePoints() != null) {
            awayTeamScore = rootView.findViewById(R.id.away_team_score_text_view);
            awayTeamScore.setText(gameAgg.getAwayPoints());

            homeTeamScore = rootView.findViewById(R.id.home_team_score_text_view);
            homeTeamScore.setText(gameAgg.getHomePoints());
        }

        mGameStatusTextView.setText(gameAgg.getTimeOnClock());

        mHomeTeamName.setText(Utils.getShortName(gameAgg.getHomeName()));
        mAwayTeamName.setText(Utils.getShortName(gameAgg.getAwayName()));

        ViewPager viewPager = rootView.findViewById(R.id.viewPager);
        tabLayout = rootView.findViewById(R.id.tabLayout);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        adapter = new TabAdapter(fragmentManager);

        if(gameAgg.getStatus().equals(getContext().getString(R.string.closed)) ||
                gameAgg.getStatus().equals(getContext().getString(R.string.inprogress))) {
            adapter.addFragment(PlayerDetailFragment.newInstance(
                    gameAgg.getHomeLeaderRebounds(),
                    gameAgg.getHomeLeaderAssists(),
                    gameAgg.getHomeLeaderPoints()),
                    Utils.getShortName(gameAgg.getHomeName()));

            adapter.addFragment(PlayerDetailFragment.newInstance(
                    gameAgg.getAwayLeaderRebounds(),
                    gameAgg.getAwayLeaderAssists(),
                    gameAgg.getAwayLeaderPoints()),
                    Utils.getShortName(gameAgg.getAwayName()));
        } else {
            adapter.addFragment(new PlayerDetailFragment(),
                    Utils.getShortName(gameAgg.getHomeName()));

            adapter.addFragment(new PlayerDetailFragment(),
                    Utils.getShortName(gameAgg.getAwayName()));
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }
}
