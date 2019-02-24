package kz.baldogre.learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kz.baldogre.learn.model.Lesson;

@Dao
public interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Lesson> lessons);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Lesson lesson);

    @Delete
    void delete(Lesson lesson);

    @Query("SELECT * FROM Lesson")
    List<Lesson> getAllLessons();

    @Query("SELECT * FROM Lesson WHERE courseId = :courseId")
    List<Lesson> getLessonsByCourseId(int courseId);
}
