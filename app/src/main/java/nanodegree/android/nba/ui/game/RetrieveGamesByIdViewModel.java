package nanodegree.android.nba.ui.game;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import nanodegree.android.nba.persistence.db.AppDatabase;
import nanodegree.android.nba.persistence.entity.GameAgg;

public class RetrieveGamesByIdViewModel extends ViewModel {
    private LiveData<List<GameAgg>> gameAgg;

    public RetrieveGamesByIdViewModel(AppDatabase appDatabase, String id) {
        gameAgg = appDatabase.gameAggDao().getGameAggByDailyScheduleId1(id);
    }

    public LiveData<List<GameAgg>> getGameAgg() {
        return gameAgg;
    }
}
