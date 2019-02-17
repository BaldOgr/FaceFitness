package kz.baldogre.learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import kz.baldogre.learn.model.LastViewedLesson;

@Dao
public interface LastViewedLessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LastViewedLesson lastViewedLesson);

    @Query("SELECT * FROM LastViewedLesson WHERE id=0")
    LastViewedLesson getLastViewedLesson();
}
