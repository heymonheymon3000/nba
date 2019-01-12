package nanodegree.android.nba.ui.gameDetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.db.NBAContract;
import nanodegree.android.nba.persistence.entity.LeaderBoxScore;
import nanodegree.android.nba.ui.game.OnFragmentInteractionListener;

public class PlayerDetailFragment extends Fragment {
    private LeaderBoxScore rebounds;
    private LeaderBoxScore assists;
    private LeaderBoxScore points;

    public PlayerDetailFragment() {}

    public static PlayerDetailFragment newInstance(LeaderBoxScore rebounds,
                                                   LeaderBoxScore assists,
                                                   LeaderBoxScore points) {
        PlayerDetailFragment fragment = new PlayerDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(NBAContract.REBOUNDS, rebounds);
        args.putParcelable(NBAContract.ASSISTS, assists);
        args.putParcelable(NBAContract.POINTS, points);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rebounds = getArguments().getParcelable(NBAContract.REBOUNDS);
            assists = getArguments().getParcelable(NBAContract.ASSISTS);
            points = getArguments().getParcelable(NBAContract.POINTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_detail, container, false);
        if (getArguments() != null) {
            TextView playerPointsNameTV = rootView.findViewById(R.id.player_points_name_tv);
            playerPointsNameTV.setText(points.getFullName());

            TextView playerPointsMinTV = rootView.findViewById(R.id.player_points_min_tv);
            playerPointsMinTV.setText(points.getMinutes());

            TextView playerPointsPtTV = rootView.findViewById(R.id.player_points_pt_tv);
            playerPointsPtTV.setText(points.getPoints());

            TextView playerPointsRebTV = rootView.findViewById(R.id.player_points_reb_tv);
            playerPointsRebTV.setText(points.getRebounds());

            TextView playerPointsAssistTV = rootView.findViewById(R.id.player_points_assist_tv);
            playerPointsAssistTV.setText(points.getAssists());



            TextView playerReboundsNameTV = rootView.findViewById(R.id.player_rebounds_name_tv);
            playerReboundsNameTV.setText(rebounds.getFullName());

            TextView playerReboundsMinTV = rootView.findViewById(R.id.player_rebounds_min_tv);
            playerReboundsMinTV.setText(rebounds.getMinutes());

            TextView playerReboundsPtTV = rootView.findViewById(R.id.player_rebounds_pt_tv);
            playerReboundsPtTV.setText(rebounds.getPoints());

            TextView playerReboundsRebTV = rootView.findViewById(R.id.player_rebounds_reb_tv);
            playerReboundsRebTV.setText(rebounds.getRebounds());

            TextView playerReboundsAssistTV = rootView.findViewById(R.id.player_rebounds_assist_tv);
            playerReboundsAssistTV.setText(rebounds.getAssists());



            TextView playerAssistsNameTV = rootView.findViewById(R.id.player_assists_name_tv);
            playerAssistsNameTV.setText(assists.getFullName());

            TextView playerAssistsMinTV = rootView.findViewById(R.id.player_assists_min_tv);
            playerAssistsMinTV.setText(assists.getMinutes());

            TextView playerAssistsPtTV = rootView.findViewById(R.id.player_assists_pt_tv);
            playerAssistsPtTV.setText(assists.getPoints());

            TextView playerAssistsRebTV = rootView.findViewById(R.id.player_assists_reb_tv);
            playerAssistsRebTV.setText(assists.getRebounds());

            TextView playerAssistsAssistTV = rootView.findViewById(R.id.player_assists_assist_tv);
            playerAssistsAssistTV.setText(assists.getAssists());
        }

        return rootView;
    }
}
