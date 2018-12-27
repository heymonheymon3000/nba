package nanodegree.android.nba.ui.game;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import nanodegree.android.nba.BuildConfig;
import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.pojo.response.dailySchedule.Game;
import nanodegree.android.nba.persistence.pojo.response.dailySchedule.TeamInfo;
import nanodegree.android.nba.persistence.pojo.response.standing.Conference;
import nanodegree.android.nba.persistence.pojo.response.standing.Division;
import nanodegree.android.nba.persistence.pojo.response.standing.Standing;
import nanodegree.android.nba.persistence.pojo.response.standing.Team;
import nanodegree.android.nba.rest.ApiUtils;
import nanodegree.android.nba.utils.DisplayDateUtils;

public class GameActivity extends AppCompatActivity
        implements OnFragmentInteractionListener {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentManager fm;
    private MyGameFragment myGameFragment;

    public static HashMap<String, TeamInfo> teamInfoHashMap = new HashMap<>();
    public static HashMap<String, String> teamLookup = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        fm = getSupportFragmentManager();
        adapter = new TabAdapter(getSupportFragmentManager());

        createTeamInfoMap();

        adapter.addFragment(new GameFragment(), getBaseContext().getString(R.string.all_games));
        myGameFragment = new MyGameFragment();
        adapter.addFragment(new MyGameFragment(), getBaseContext().getString(R.string.my_games));

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

    private static void createTeamInfoMap() {
        teamLookup.put("Pistons", "DET");
        teamLookup.put("Bucks", "MIL");
        teamLookup.put("Knicks", "NYK");
        teamLookup.put("Suns", "PHX");
        teamLookup.put("Thunder", "OKC");
        teamLookup.put("Bulls", "CHI");
        teamLookup.put("Timberwolves", "MIN");
        teamLookup.put("Kings", "SAC");
        teamLookup.put("Rockets", "HOU");
        teamLookup.put("Jazz", "UTA");
        teamLookup.put("Spurs", "SAS");
        teamLookup.put("76ers", "PHI");
        teamLookup.put("Clippers", "LAC");
        teamLookup.put("Blazers", "POR");
        teamLookup.put("Warriors", "GSW");
        teamLookup.put("Grizzlies", "MEM");
        teamLookup.put("Pacers", "IND");
        teamLookup.put("Cavaliers", "CLE");
        teamLookup.put("Nets", "BKN");
        teamLookup.put("Lakers", "LAL");
        teamLookup.put("Hawks", "ATL");
        teamLookup.put("Wizards","WAS");
        teamLookup.put("Nuggets", "DEN");
        teamLookup.put("Mavericks", "DAL");
        teamLookup.put("Magic", "ORL");
        teamLookup.put("Hornets", "CHA");
        teamLookup.put("Raptors", "TOR");
        teamLookup.put("Celtics", "BOS");
        teamLookup.put("Pelicans", "NOP");
        teamLookup.put("Heat", "MIA");

        teamInfoHashMap.put("DET", new TeamInfo("Detroit Pistons", R.drawable.detroit_pistons_vector));
        teamInfoHashMap.put("MIL", new TeamInfo("Milwaukee Bucks", R.drawable.milwaukee_bucks_vector));
        teamInfoHashMap.put("NYK", new TeamInfo("New York Knicks", R.drawable.new_york_knicks_vector));
        teamInfoHashMap.put("PHX", new TeamInfo("Phoenix Suns", R.drawable.phoenix_suns_vector));
        teamInfoHashMap.put("OKC", new TeamInfo("Oklahoma City Thunder", R.drawable.oklahoma_city_thunder_vector));
        teamInfoHashMap.put("CHI", new TeamInfo("Chicago Bulls", R.drawable.chicago_bulls_vector));
        teamInfoHashMap.put("MIN", new TeamInfo("Minnesota Timberwolves", R.drawable.minnesota_timberwolves_vector));
        teamInfoHashMap.put("SAC", new TeamInfo("Sacramento Kings", R.drawable.sacramento_kings_vector));
        teamInfoHashMap.put("HOU", new TeamInfo("Houston Rockets", R.drawable.houston_rockets_vector));
        teamInfoHashMap.put("UTA", new TeamInfo("Utah Jazz", R.drawable.utah_jazz_vector));
        teamInfoHashMap.put("SAS", new TeamInfo("San Antonio Spurs", R.drawable.san_antonio_spurs_vector));
        teamInfoHashMap.put("PHI", new TeamInfo("Philadelphia 76ers", R.drawable.philadelphia_76ers_vector));
        teamInfoHashMap.put("LAC", new TeamInfo("Los Angeles Clippers", R.drawable.los_angeles_clippers_vector));
        teamInfoHashMap.put("POR", new TeamInfo("Portland Trail Blazers", R.drawable.portland_trail_blazers_vector));
        teamInfoHashMap.put("GSW", new TeamInfo("Golden State Warriors", R.drawable.golden_state_warriors_vector));
        teamInfoHashMap.put("MEM", new TeamInfo("Memphis Grizzlies", R.drawable.memphis_grizzlies_vector));
        teamInfoHashMap.put("IND", new TeamInfo("Indiana Pacers", R.drawable.indiana_pacers_vector));
        teamInfoHashMap.put("CLE", new TeamInfo("Cleveland Cavaliers", R.drawable.cleveland_cavaliers_vector));
        teamInfoHashMap.put("BKN", new TeamInfo("Brooklyn Nets", R.drawable.brooklyn_nets_vector));
        teamInfoHashMap.put("LAL", new TeamInfo("Los Angeles Lakers", R.drawable.los_angeles_lakers_vector));
        teamInfoHashMap.put("ATL", new TeamInfo("Atlanta Hawks", R.drawable.atlanta_hawks_vector));
        teamInfoHashMap.put("WAS", new TeamInfo("Washington Wizards", R.drawable.washington_wizards_vector));
        teamInfoHashMap.put("DEN", new TeamInfo("Denver Nuggets", R.drawable.denver_nuggets_vector));
        teamInfoHashMap.put("DAL", new TeamInfo("Dallas Mavericks", R.drawable.dallas_mavericks_vector));
        teamInfoHashMap.put("ORL", new TeamInfo("Orlando Magic", R.drawable.orlando_magic_vector));
        teamInfoHashMap.put("CHA", new TeamInfo("Charlotte Hornets", R.drawable.charlotte_hornets_vector));
        teamInfoHashMap.put("TOR", new TeamInfo("Toronto Raptors", R.drawable.toronto_raptors_vector));
        teamInfoHashMap.put("BOS", new TeamInfo("Boston Celtics", R.drawable.boston_celtics_vector));
        teamInfoHashMap.put("NOP", new TeamInfo("New Orleans Pelicans", R.drawable.new_orleans_pelicans_vector));
        teamInfoHashMap.put("MIA", new TeamInfo("Miami Heat", R.drawable.miami_heat_vector));
    }
}