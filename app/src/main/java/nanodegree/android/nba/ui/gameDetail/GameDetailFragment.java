package nanodegree.android.nba.ui.gameDetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.entity.GameAgg;

public class GameDetailFragment extends Fragment {
    public final static String GAME_AGG_KEY = "gameAgg";
    private GameAgg gameAgg;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_detail, container, false);


        return rootView;
    }
}
