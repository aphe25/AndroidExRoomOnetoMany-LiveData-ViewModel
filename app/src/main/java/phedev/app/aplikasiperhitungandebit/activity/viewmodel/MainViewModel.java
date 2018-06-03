package phedev.app.aplikasiperhitungandebit.activity.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import phedev.app.aplikasiperhitungandebit.database.AppDatabase;
import phedev.app.aplikasiperhitungandebit.database.ProjectEntry;

/**
 * Created by phedev in 2018.
 */

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<ProjectEntry>> projectEntries;
    private static final String LOG_TAG = MainViewModel.class.getSimpleName();

    public MainViewModel(@NonNull Application application) {
        super(application);
        Log.d(LOG_TAG, "Actively retrieving project data from database");
        AppDatabase appDatabase = AppDatabase.getsInstance(this.getApplication());
        projectEntries = appDatabase.dataDao().loadAllProjectData();
    }

    public LiveData<List<ProjectEntry>> getProjectEntries() {
        return projectEntries;
    }

}
