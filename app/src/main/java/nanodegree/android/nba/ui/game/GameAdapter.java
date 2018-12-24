package nanodegree.android.nba.ui.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.pojo.response.dailySchedule.Game;
import nanodegree.android.nba.persistence.pojo.response.dailySchedule.TeamInfo;
import nanodegree.android.nba.utils.DisplayMetricUtils;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.TimeZone;

import java.util.Locale;


public class GameAdapter
        extends RecyclerView.Adapter<GameAdapter.MasterListGameAdapterViewHolder> {

    private Picasso picassoInstance;

    private List<Game> mGames;
    private int cardWidth;
    private int cardHeight;
    private static HashMap<String, TeamInfo> teamInfoHashMap = new HashMap<>();
    public GameAdapter(Context context,
                         int cardWidth, int cardHeight) {

        createTeamInfoMap();

        picassoInstance =
                new Picasso.Builder(context.getApplicationContext()).loggingEnabled(true)
                        .build();
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
    }

    private static void createTeamInfoMap() {
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


    @NonNull
    @Override
    public MasterListGameAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.game_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        view.setFocusable(true);

        return new MasterListGameAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MasterListGameAdapterViewHolder holder,
                                 int position) {
        final Game game = mGames.get(position);
        setCardViewSize(holder);

        TeamInfo awayTeamInfo = teamInfoHashMap.get(game.getAway().getAlias());
        TeamInfo homeTeamInfo = teamInfoHashMap.get(game.getHome().getAlias());

        holder.mGameStatusTextView.setText(getGameStatus(game));
        holder.mTVNetworkTextView.setText(game.getBroadcasts().get(0).getNetwork());

        picassoInstance
                .load(awayTeamInfo.getLogo())
                .resize(DisplayMetricUtils.convertDpToPixel(24),
                        DisplayMetricUtils.convertDpToPixel(24))
                .centerCrop()
                .into(holder.mAwayTeamLogoImageView);

        holder.mAwayTeamLogoTextView.setText(awayTeamInfo.getName());
        holder.mAwayTeamRecordTextView.setText(R.string.record);
        if(game.getHomePoints() == null) {
            holder.mHomeTeamScoreTextView.setText("");
        } else {
            holder.mAwayTeamScoreTextView.setText(String.valueOf(game.getAwayPoints()));
        }

        picassoInstance
                .load(homeTeamInfo.getLogo())
                .resize(DisplayMetricUtils.convertDpToPixel(24),
                        DisplayMetricUtils.convertDpToPixel(24))
                .centerCrop()
                .into(holder.mHomeTeamLogoImageView);

        holder.mHomeTeamLogoTextView.setText(homeTeamInfo.getName());
        holder.mHomeTeamRecordTextView.setText(R.string.record);
        if(game.getHomePoints() == null) {
            holder.mHomeTeamScoreTextView.setText("");
        } else {
            holder.mHomeTeamScoreTextView.setText(String.valueOf(game.getHomePoints()));
        }
    }

    private String getGameStatus(Game game) {
        if(game.getStatus().equals("closed")) {
            return "Finish";
        } else if(game.getStatus().equals("scheduled")) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH);
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                Date date = dateFormat.parse(game.getScheduled());
                DateFormat d = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
                d.setTimeZone(TimeZone.getTimeZone("EST"));
                return d.format(date)+ " ET";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private void setCardViewSize(MasterListGameAdapterViewHolder holder) {
        ViewGroup.LayoutParams layoutParams =
                holder.cardView.getLayoutParams();
        layoutParams.width = DisplayMetricUtils.convertDpToPixel(cardWidth);
        layoutParams.height = DisplayMetricUtils.convertDpToPixel(cardHeight);
        holder.cardView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        if (null == mGames) return 0;
        return mGames.size();
    }

    public void setGames(List<Game> games) {
        this.mGames = games;
        notifyDataSetChanged();
    }

    public class MasterListGameAdapterViewHolder extends
            RecyclerView.ViewHolder implements View.OnClickListener  {

        final CardView cardView;
        final TextView mGameStatusTextView;
        final TextView mTVNetworkTextView;

        final ImageView mHomeTeamLogoImageView;
        final TextView mHomeTeamLogoTextView;
        final TextView mHomeTeamRecordTextView;
        final TextView mHomeTeamScoreTextView;

        final ImageView mAwayTeamLogoImageView;
        final TextView mAwayTeamLogoTextView;
        final TextView mAwayTeamRecordTextView;
        final TextView mAwayTeamScoreTextView;

        private MasterListGameAdapterViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.cv_game);

            mGameStatusTextView = view.findViewById(R.id.game_status_text_view);
            mTVNetworkTextView = view.findViewById(R.id.tv_network_text_view);

            mHomeTeamLogoImageView = view.findViewById(R.id.home_team_logo_image_view);
            mHomeTeamLogoTextView = view.findViewById(R.id.home_team_logo_text_view);
            mHomeTeamRecordTextView = view.findViewById(R.id.home_team_record_text_view);
            mHomeTeamScoreTextView = view.findViewById(R.id.home_team_score_text_view);

            mAwayTeamLogoImageView = view.findViewById(R.id.away_team_logo_image_view);
            mAwayTeamLogoTextView = view.findViewById(R.id.away_team_logo_text_view);
            mAwayTeamRecordTextView = view.findViewById(R.id.away_team_record_text_view);
            mAwayTeamScoreTextView = view.findViewById(R.id.away_team_score_text_view);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
