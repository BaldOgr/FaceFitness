package kz.baldogre.learn.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;
import io.reactivex.Single;
import kz.baldogre.learn.model.LastOpenApp;

@Dao
public interface LastOpenAppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LastOpenApp lastOpenApp);

    @Query("SELECT * FROM LastOpenApp WHERE id = 0")
    Single<LastOpenApp> getLastOpenApp();
}
