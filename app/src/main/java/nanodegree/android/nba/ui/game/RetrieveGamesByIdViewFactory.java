package nanodegree.android.nba.ui.game;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import nanodegree.android.nba.persistence.db.AppDatabase;

public class RetrieveGamesByIdViewFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mAppDatabase;
    private final String mId;

    public RetrieveGamesByIdViewFactory(AppDatabase appDatabase, String id) {
        this.mAppDatabase = appDatabase;
        this.mId = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RetrieveGamesByIdViewModel(mAppDatabase, mId);
    }
}
