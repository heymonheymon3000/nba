package nanodegree.android.nba.ui.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nanodegree.android.nba.NBAApplication;
import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.entity.FavoriteTeam;
import nanodegree.android.nba.utils.DisplayMetricUtils;
import nanodegree.android.nba.utils.TeamInfo;

public class FavoriteTeamAdaptor
    extends RecyclerView.Adapter<FavoriteTeamAdaptor.FavoriteTeamViewHolder> {

    private Picasso picassoInstance;
    private List<FavoriteTeam> mFavoriteTeams;
    private final OnFavoriteTeamSelectorClickListener mFavoriteTeamSelectorClickListener;
    private Context context;
    private RecyclerView recyclerView;

    public interface OnFavoriteTeamSelectorClickListener {
        void onFavoriteTeamSelector(FavoriteTeam favoriteTeam);
    }

    public FavoriteTeamAdaptor(Context context, RecyclerView recyclerView,
                               OnFavoriteTeamSelectorClickListener favoriteTeamSelectorClickListener) {
        this.mFavoriteTeamSelectorClickListener = favoriteTeamSelectorClickListener;
        picassoInstance =
            new Picasso.Builder(context.getApplicationContext())
                .loggingEnabled(true)
                .build();
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public FavoriteTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.favorite_team_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        view.setFocusable(true);

        return new FavoriteTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTeamViewHolder holder,
                                 int position) {
        final FavoriteTeam favoriteTeam = mFavoriteTeams.get(position);

        TeamInfo teamInfo =
                NBAApplication.teamInfoHashMap.get(favoriteTeam.getAlias());

        picassoInstance
            .load(teamInfo.getLogo())
            .resize(DisplayMetricUtils.convertDpToPixel(60),
                    DisplayMetricUtils.convertDpToPixel(60))
            .centerCrop()
            .into(holder.favoriteTeamLogoImageView);

        holder.favoriteTeamNameTextView.setText(favoriteTeam.getName());
        holder.favoriteTeamCheckBox.setChecked(favoriteTeam.getSelected());
    }

    @Override
    public int getItemCount() {
        if (null == mFavoriteTeams) return 0;
        return mFavoriteTeams.size();
    }

    public void setFavoriteTeams(List<FavoriteTeam> favoriteTeams) {
        this.mFavoriteTeams = favoriteTeams;
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right);
        controller.getAnimation().setDuration(700);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public class FavoriteTeamViewHolder extends RecyclerView.ViewHolder  {

        private ImageView favoriteTeamLogoImageView;
        private TextView favoriteTeamNameTextView;
        private CheckBox favoriteTeamCheckBox;

        public FavoriteTeamViewHolder(View itemView) {
            super(itemView);

            favoriteTeamLogoImageView = itemView.findViewById(R.id.favoriteTeamLogo);
            favoriteTeamNameTextView = itemView.findViewById(R.id.favoriteTeamName);
            favoriteTeamCheckBox = itemView.findViewById(R.id.favoriteTeamSelected);
            favoriteTeamCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedPosition = getAdapterPosition();
                    FavoriteTeam favoriteTeam = mFavoriteTeams.get(selectedPosition);
                    favoriteTeam.setSelected(favoriteTeamCheckBox.isChecked());
                    mFavoriteTeamSelectorClickListener.onFavoriteTeamSelector(favoriteTeam);
                }
            });
        }
    }
}
