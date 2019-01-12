package nanodegree.android.nba.ui.gameDetail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.entity.GameAgg;

public class GameDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        GameAgg gameAgg = getIntent().getParcelableExtra(GameDetailFragment.GAME_AGG_KEY);
        if (gameAgg != null) {
            GameDetailFragment gameDetailFragment = GameDetailFragment.newInstance(gameAgg);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.game_detail_fragment_container, gameDetailFragment)
                    .commit();
        }
    }
}
