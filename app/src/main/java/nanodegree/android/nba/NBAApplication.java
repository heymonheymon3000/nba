package nanodegree.android.nba;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.HashMap;

import nanodegree.android.nba.utils.TeamInfo;

public class NBAApplication extends Application {
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    public static HashMap<String, TeamInfo> teamInfoHashMap = new HashMap<>();
    public static HashMap<String, String> teamLookup = new HashMap<>();

    public void onCreate() {
        final Context context = this;

        createTeamInfoMap();

        Stetho.initialize(
                Stetho.newInitializerBuilder(context)
                        .enableDumpapp(new SampleBumperPluginsProvider(context))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                        .build());

        super.onCreate();

        sAnalytics = GoogleAnalytics.getInstance(this);
    }

    private static class SampleBumperPluginsProvider implements DumperPluginsProvider {
        private final Context mContext;

        private SampleBumperPluginsProvider(Context context) {this.mContext = context; }

        @Override
        public Iterable<DumperPlugin> get() {
            ArrayList<DumperPlugin> plugins = new ArrayList<>();
            for(DumperPlugin dumperPlugin : Stetho.defaultDumperPluginsProvider(mContext).get()){
                plugins.add(dumperPlugin);
            }
            return plugins;
        }
    }

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
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

