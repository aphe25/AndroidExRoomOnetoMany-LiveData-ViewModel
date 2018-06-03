package phedev.app.aplikasiperhitungandebit.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by phedev in 2018.
 */

@Dao
public interface DataDao {

    @Query("SELECT * FROM data ORDER BY id")
    List<DataEntry> loadAllData();

    @Query("SELECT * FROM data WHERE id_data= :id_data")
    LiveData<List<DataEntry>> loadDataByProject(final  int id_data);

    @Query("SELECT * FROM projects ORDER BY id")
    LiveData<List<ProjectEntry>> loadAllProjectData();

    @Query("SELECT * FROM projects WHERE id = :idProject")
    LiveData<ProjectEntry> loadProjectById(int idProject);

    @Insert
    void insertData(DataEntry dataEntry);

    @Insert
    void insertProjectData (ProjectEntry projectEntry);

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    void updateProjectData(ProjectEntry projectEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateData(DataEntry dataEntry);

    @Delete
    void deleteProjectData(ProjectEntry projectEntry);

    @Delete
    void deleteData(DataEntry dataEntry);
}
