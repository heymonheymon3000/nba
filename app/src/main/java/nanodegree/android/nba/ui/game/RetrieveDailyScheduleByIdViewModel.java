package nanodegree.android.nba.ui.game;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import nanodegree.android.nba.persistence.db.AppDatabase;
import nanodegree.android.nba.persistence.entity.DailyScheduleAgg;

public class RetrieveDailyScheduleByIdViewModel extends ViewModel {
    private LiveData<DailyScheduleAgg> dailyScheduleAgg;

    public RetrieveDailyScheduleByIdViewModel(AppDatabase appDatabase, String id) {
        dailyScheduleAgg = appDatabase.dailyScheduleAggDao().getDailyScheduleAggById1(id);
    }

    public LiveData<DailyScheduleAgg> getDailyScheduleAgg() {
        return dailyScheduleAgg;
    }
}
