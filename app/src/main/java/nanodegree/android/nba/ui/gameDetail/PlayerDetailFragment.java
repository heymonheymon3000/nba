package nanodegree.android.nba.ui.gameDetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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



        return rootView;
    }
}
