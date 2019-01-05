package nanodegree.android.nba.ui.game;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import nanodegree.android.nba.R;
import nanodegree.android.nba.persistence.dao.FavoriteTeamDao;
import nanodegree.android.nba.persistence.db.AppDatabase;
import nanodegree.android.nba.persistence.entity.FavoriteTeam;

public class MyGameActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<List<FavoriteTeam>>,
        FavoriteTeamAdaptor.OnFavoriteTeamSelectorClickListener {

    private static final int FAVORITE_TEAM_LOADER = 23;

    private RecyclerView mRecyclerView;
    private FavoriteTeamDao favoriteTeamDao;
    private FavoriteTeamAdaptor favoriteTeamAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);

        favoriteTeamDao = AppDatabase.getInstance(
                getApplicationContext()).gameFavoriteTeamDao();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.favorite_teams));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_fav_teams);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        favoriteTeamAdaptor = new FavoriteTeamAdaptor(this, this);
        mRecyclerView.setAdapter(favoriteTeamAdaptor);

        getAllFavoriteTeams();
    }

    private void getAllFavoriteTeams() {
        Bundle allFavoriteTeamsBundle = new Bundle();
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<List<FavoriteTeam>> loader = loaderManager.getLoader(FAVORITE_TEAM_LOADER);
        if (loader == null) {
            loaderManager.initLoader(FAVORITE_TEAM_LOADER, allFavoriteTeamsBundle, this);
        } else {
            loaderManager.restartLoader(FAVORITE_TEAM_LOADER, allFavoriteTeamsBundle, this);
        }
    }

    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader<List<FavoriteTeam>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<List<FavoriteTeam>>(this) {
            List<FavoriteTeam> resultFromFavoriteTeam;

            @Nullable
            @Override
            public List<FavoriteTeam> loadInBackground() {
                List<FavoriteTeam> favoriteTeams = favoriteTeamDao.getAllFavoriteTeams();
                Log.i("FavoriteTeams", "SIZE =>  = " + String.valueOf(favoriteTeams.size()));
                return favoriteTeams;
            }

            @Override
            protected void onStartLoading() {
                if(resultFromFavoriteTeam != null) {
                    // To skip loadInBackground call
                    deliverResult(resultFromFavoriteTeam);
                } else {
                    forceLoad();
                }
            }

            @Override
            public void deliverResult(@Nullable List<FavoriteTeam> data) {
                resultFromFavoriteTeam = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<FavoriteTeam>> loader, List<FavoriteTeam> data) {
        if (data != null) {
            favoriteTeamAdaptor.setFavoriteTeams(data);
        } else {
            Toast.makeText(getApplicationContext(),
                    getApplicationContext().getString(R.string.no_connection),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<FavoriteTeam>> loader) {}

    @Override
    public void onFavoriteTeamSelector(FavoriteTeam favoriteTeam) {
        (new Thread() { public void run() {
            favoriteTeamDao.update(favoriteTeam);
        }}).start();
    }
}
