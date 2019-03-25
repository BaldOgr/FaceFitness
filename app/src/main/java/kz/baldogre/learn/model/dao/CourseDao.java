package kz.baldogre.learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kz.baldogre.learn.model.Course;

@Dao
public interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Course> courseList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);

    @Query("SELECT * FROM Course")
    List<Course> getAllCourses();

    @Query("SELECT * FROM Course WHERE id = :id")
    Course getCourseById(int id);


}
