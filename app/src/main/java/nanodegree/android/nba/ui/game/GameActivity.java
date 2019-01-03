package nanodegree.android.nba.ui.game;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import java.util.HashMap;

import nanodegree.android.nba.R;
import nanodegree.android.nba.utils.TeamInfo;
import nanodegree.android.nba.utils.DisplayDateUtils;

public class GameActivity extends AppCompatActivity
        implements OnFragmentInteractionListener {

    private TabAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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
    public void updateFragment(Integer index) {
        switch (index) {
            case 0:
                GameFragment gameFragment =
                    GameFragment.newInstance(DisplayDateUtils.GAME, 0,
                        DisplayDateUtils.getCurrentDate(DisplayDateUtils.GAME),
                        true,
                        false);
                adapter.replaceFragment(0, gameFragment);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                GameFragment myGameFragment =
                    GameFragment.newInstance(DisplayDateUtils.MY_GAME, 1,
                        DisplayDateUtils.getCurrentDate(DisplayDateUtils.MY_GAME),
                        true,
                        true);
                adapter.replaceFragment(1, myGameFragment);
                adapter.notifyDataSetChanged();
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
}