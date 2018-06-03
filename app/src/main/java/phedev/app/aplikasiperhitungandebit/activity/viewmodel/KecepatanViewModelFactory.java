package phedev.app.aplikasiperhitungandebit.activity.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import phedev.app.aplikasiperhitungandebit.database.AppDatabase;

/**
 * Created by phedev in 2018.
 */

public class KecepatanViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final AppDatabase appDatabase;
    private final int projectId;

    public KecepatanViewModelFactory(AppDatabase database, int pId){
        appDatabase = database;
        projectId = pId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new KecepatanViewModel(appDatabase, projectId) ;
    }
}
