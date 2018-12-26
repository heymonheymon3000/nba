package nanodegree.android.nba.ui.game;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import nanodegree.android.nba.R;
import nanodegree.android.nba.utils.DisplayDateUtils;

public class GameActivity extends AppCompatActivity
        implements OnFragmentInteractionListener {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentManager fm;
    private MyGameFragment myGameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        fm = getSupportFragmentManager();
        adapter = new TabAdapter(getSupportFragmentManager());

        adapter.addFragment(new GameFragment(), "All Games");
        myGameFragment = new MyGameFragment();
        adapter.addFragment(new MyGameFragment(), "My Games");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals("My Games")) {
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
                GameFragment gameFragment = GameFragment.newInstance(DisplayDateUtils.getCurrentDate(DisplayDateUtils.GAME));
                adapter.replaceFragment(0, gameFragment);
                break;
            case 1:
                MyGameFragment myGameFragment = MyGameFragment.newInstance(DisplayDateUtils.getCurrentDate(DisplayDateUtils.MY_GAME));
                adapter.replaceFragment(1, myGameFragment);
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