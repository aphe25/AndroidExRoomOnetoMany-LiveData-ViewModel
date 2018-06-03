package phedev.app.aplikasiperhitungandebit.activity.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import phedev.app.aplikasiperhitungandebit.database.AppDatabase;
import phedev.app.aplikasiperhitungandebit.database.DataEntry;
import phedev.app.aplikasiperhitungandebit.database.ProjectEntry;

/**
 * Created by phedev in 2018.
 */

public class KecepatanViewModel extends ViewModel{
    private LiveData<ProjectEntry> entryLiveData;
    private LiveData<List<DataEntry>> entryData;

    public KecepatanViewModel(AppDatabase appDatabase, int projectId) {
        entryLiveData = appDatabase.dataDao().loadProjectById(projectId);
        entryData = appDatabase.dataDao().loadDataByProject(projectId);
    }

    public LiveData<ProjectEntry> getProjectEntry(){
        return entryLiveData;
    }

    public LiveData<List<DataEntry>> getEntryData(){
        return entryData;
    }
}
