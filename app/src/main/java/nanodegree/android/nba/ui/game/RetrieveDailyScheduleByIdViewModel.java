package nanodegree.android.nba.ui.game;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import nanodegree.android.nba.persistence.db.AppDatabase;
import nanodegree.android.nba.persistence.entity.DailyScheduleAgg;
import nanodegree.android.nba.persistence.entity.GameAgg;

public class RetrieveDailyScheduleByIdViewModel extends ViewModel {
    private LiveData<DailyScheduleAgg> dailyScheduleAgg;
    private LiveData<List<GameAgg>> gameAgg;

    public RetrieveDailyScheduleByIdViewModel(AppDatabase appDatabase, String id) {
        dailyScheduleAgg = appDatabase.dailyScheduleAggDao().getDailyScheduleAggById(id);
        gameAgg = appDatabase.gameAggDao().getGameAggByDailyScheduleId(id);
    }

    public LiveData<DailyScheduleAgg> getDailyScheduleAgg() {
        return dailyScheduleAgg;
    }

    public void setDailyScheduleAgg(LiveData<DailyScheduleAgg> dailyScheduleAgg) {
        this.dailyScheduleAgg = dailyScheduleAgg;
    }

    public LiveData<List<GameAgg>> getGameAgg() {
        return gameAgg;
    }

    public void setGameAgg(LiveData<List<GameAgg>> gameAgg) {
        this.gameAgg = gameAgg;
    }
}
