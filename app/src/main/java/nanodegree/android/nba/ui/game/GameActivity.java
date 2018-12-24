package nanodegree.android.nba.ui.game;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nanodegree.android.nba.R;
import nanodegree.android.nba.utils.DisplayDateUtils;

public class GameActivity extends AppCompatActivity
        implements
        GameFragment.OnFragmentInteractionListener,
        MyGameFragment.OnFragmentInteractionListener {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  GameFragment gameFragment;
    private  MyGameFragment myGameFragment;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        fm = getSupportFragmentManager();
        adapter = new TabAdapter(getSupportFragmentManager());

        gameFragment = new GameFragment();
        adapter.addFragment(gameFragment, "All Games");

        myGameFragment = new MyGameFragment();
        adapter.addFragment(myGameFragment, "My Games");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals("My Games")) {
                    fm.beginTransaction().detach(myGameFragment).attach(myGameFragment).commit();
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
    public void onFragmentInteraction() {
        GameFragment  newFragment = GameFragment.newInstance(DisplayDateUtils.getCurrentDate(DisplayDateUtils.GAME));
        adapter.replaceFragment(gameFragment, newFragment);
        gameFragment = newFragment;
    }

    @Override
    public void onFragmentInteraction2() {
        MyGameFragment newFragment = MyGameFragment.newInstance(DisplayDateUtils.getCurrentDate(DisplayDateUtils.MY_GAME));
        adapter.replaceFragment(myGameFragment, newFragment);
        myGameFragment = newFragment;
    }
}
