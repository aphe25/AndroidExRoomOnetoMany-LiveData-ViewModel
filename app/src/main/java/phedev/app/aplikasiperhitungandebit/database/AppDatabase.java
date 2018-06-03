package phedev.app.aplikasiperhitungandebit.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created by phedev in 2018.
 */
@Database(entities = {DataEntry.class, ProjectEntry.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "datadebit";
    private static AppDatabase sInstance;

    public static AppDatabase getsInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new Database Instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        //queries should be done in a separate thread to avoid locking UI
                        //this is just for temporary method
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "getting database instance");
        return sInstance;
    }

    public abstract DataDao dataDao();

}
