package kz.baldogre.learn.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kz.baldogre.learn.model.LastViewedLesson;
import kz.baldogre.learn.model.Lesson;
import kz.baldogre.learn.model.dao.LastViewedLessonDao;
import kz.baldogre.learn.model.dao.LessonDao;

@Database(entities = {Lesson.class, LastViewedLesson.class},
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LessonDao getLessonDao();

    public abstract LastViewedLessonDao getLastViewedDao();
}