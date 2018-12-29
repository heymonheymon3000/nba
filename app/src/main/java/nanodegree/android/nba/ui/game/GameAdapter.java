package nanodegree.android.nba.ui.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
import nanodegree.android.nba.utils.DisplayMetricUtils;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Observer;
import java.util.TimeZone;

import java.util.Locale;


public class GameAdapter
        extends RecyclerView.Adapter<GameAdapter.MasterListGameAdapterViewHolder> {

    private Picasso picassoInstance;

    private List<Game> mGames;
    private int cardWidth;
    private int cardHeight;

    public GameAdapter(Context context,
                         int cardWidth, int cardHeight) {

        picassoInstance =
                new Picasso.Builder(context.getApplicationContext()).loggingEnabled(true)
                        .build();
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
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

        TeamInfo awayTeamInfo = GameActivity.teamInfoHashMap.get(game.getAway().getAlias());
        TeamInfo homeTeamInfo = GameActivity.teamInfoHashMap.get(game.getHome().getAlias());

        holder.mGameStatusTextView.setText(getGameStatus(game));
        holder.mTVNetworkTextView.setText(game.getBroadcasts().get(0).getNetwork());

        picassoInstance
                .load(awayTeamInfo.getLogo())
                .resize(DisplayMetricUtils.convertDpToPixel(24),
                        DisplayMetricUtils.convertDpToPixel(24))
                .centerCrop()
                .into(holder.mAwayTeamLogoImageView);

        holder.mAwayTeamLogoTextView.setText(awayTeamInfo.getName());
        holder.mAwayTeamRecordTextView.setText(game.getAwayRecord());
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
        holder.mHomeTeamRecordTextView.setText(game.getHomeRecord());
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
        } else if(game.getStatus().equals("inprogress")) {
            return game.getTimeOnClock();
        } else if(game.getStatus().equals("halftime")) {
            return "Halftime";
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
