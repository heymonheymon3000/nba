package nanodegree.android.nba.ui.gameDetail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.entity.GameAgg;

public class GameDetailActivity extends AppCompatActivity {
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        // Obtain the shared Tracker instance.
        NBAApplication application = (NBAApplication) getApplication();
        mTracker = application.getDefaultTracker();

        GameAgg gameAgg = getIntent().getParcelableExtra(GameDetailFragment.GAME_AGG_KEY);
        if (gameAgg != null) {

            StringBuilder sb = new StringBuilder();
            sb.append(gameAgg.getHomeName());
            sb.append(" vs ");
            sb.append(gameAgg.getAwayName());

            // Determine how many time users were interested in a particular game details
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Game")
                    .setAction("Game Details")
                    .setLabel(sb.toString())
                    .setValue(1)
                    .build());

            GameDetailFragment gameDetailFragment = GameDetailFragment.newInstance(gameAgg);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.game_detail_fragment_container, gameDetailFragment)
                    .commit();
        }
    }
}
