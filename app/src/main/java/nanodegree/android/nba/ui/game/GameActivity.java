package nanodegree.android.nba.ui.game;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Calendar;

import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.R;
import nanodegree.android.nba.utils.DisplayDateUtils;

public class GameActivity extends AppCompatActivity
        implements OnFragmentInteractionListener {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private Boolean reloadMyGameFragment = false;
    private Tracker mTracker;
    private InterstitialAd mInterstitialAd;

    private final static String TAG = GameActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getApplicationContext()
                .getString(R.string.TEST_ADMOB_UNIT_ID));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });


        // Obtain the shared Tracker instance.
        NBAApplication application = (NBAApplication) getApplication();
        mTracker = application.getDefaultTracker();

        ViewPager viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();

        adapter = new TabAdapter(fragmentManager);
        adapter.addFragment(GameFragment.newInstance(DisplayDateUtils.GAME, 0,
                DisplayDateUtils.getCurrentDate(DisplayDateUtils.GAME),
                true,
                false), getBaseContext().getString(R.string.all_games));

        adapter.addFragment(GameFragment.newInstance(DisplayDateUtils.MY_GAME, 1,
                DisplayDateUtils.getCurrentDate(DisplayDateUtils.MY_GAME),
                false,
                true), getBaseContext().getString(R.string.my_games));
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals(getBaseContext().getString(R.string.my_games))) {
                    updateFragment(1);
                    tabLayout.removeOnTabSelectedListener(this);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tabLayout.getTabAt(1).isSelected() && reloadMyGameFragment) {
            reloadMyGameFragment = false;
            updateFragment(1);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(tabLayout.getTabAt(1).isSelected()) {
            reloadMyGameFragment = true;
        }
    }

    @Override
    public void updateFragment(Integer index) {
        Calendar cal;
        switch (index) {
            case 0:
                cal = DisplayDateUtils.getCurrentDate(DisplayDateUtils.GAME);
                GameFragment gameFragment =
                    GameFragment.newInstance(DisplayDateUtils.GAME, 0,
                        cal,
                        true,
                        false);
                adapter.replaceFragment(0, gameFragment);
                adapter.notifyDataSetChanged();

                // User getting all games for this date
                mTracker.setScreenName("Tab " + getCurrentTabTitle(index) + " for " + DisplayDateUtils.convertDate(cal));
                mTracker.send(new HitBuilders.ScreenViewBuilder().build());
                break;
            case 1:
                cal = DisplayDateUtils.getCurrentDate(DisplayDateUtils.MY_GAME);
                GameFragment myGameFragment =
                    GameFragment.newInstance(DisplayDateUtils.MY_GAME, 1,
                        cal,
                        true,
                        true);
                adapter.replaceFragment(1, myGameFragment);
                adapter.notifyDataSetChanged();

                // User getting his/her favorite games for this date
                mTracker.setScreenName("Tab " + getCurrentTabTitle(index) + " for " + DisplayDateUtils.convertDate(cal));
                mTracker.send(new HitBuilders.ScreenViewBuilder().build());
                break;
        }
    }

    @Override
    public void enableTabs(Boolean tabsEnabled) {
        if(tabsEnabled) {
            LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
            tabStrip.setEnabled(true);
            for(int i = 0; i < tabStrip.getChildCount(); i++) {
                tabStrip.getChildAt(i).setClickable(true);
            }
            tabLayout.setAlpha(Float.parseFloat(
                    getApplicationContext().getString(R.string.enable_alpha_value)));
        } else {
            LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
            tabStrip.setEnabled(false);
            for(int i = 0; i < tabStrip.getChildCount(); i++) {
                tabStrip.getChildAt(i).setClickable(false);
            }
            tabLayout.setAlpha(Float.parseFloat(
                    getApplicationContext().getString(R.string.disable_alpha_value)));
        }
    }

    @Override
    public void showAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d(TAG, "The interstitial wasn't loaded yet.");
        }
    }

    private String getCurrentTabTitle(int index) {
        return tabLayout.getTabAt(index).getText().toString();
    }
}