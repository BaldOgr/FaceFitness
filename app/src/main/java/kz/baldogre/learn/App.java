package kz.baldogre.learn;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.SharedPreferences;

import java.util.List;

import kz.baldogre.learn.common.Const;
import kz.baldogre.learn.model.Course;
import kz.baldogre.learn.model.LastViewedLesson;
import kz.baldogre.learn.model.Lessons;
import kz.baldogre.learn.model.db.AppDatabase;

public class App extends Application {
    AppDatabase appDatabase;
    private static int DB_VERSION = Const.CURRENT_APP_VERSION;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lessons_database")
                .fallbackToDestructiveMigration()
                .build();

        SharedPreferences sharedPreferences = getSharedPreferences("Lesson", MODE_PRIVATE);

        if (!sharedPreferences.contains("added_to_database")) {
            new Thread(() -> {
                appDatabase.getLastViewedDao().insert(new LastViewedLesson());
                sharedPreferences.edit()
                        .putBoolean("added_to_database", true)
                        .apply();
            }).start();

        }

        if (sharedPreferences.getInt("DB_VERSION", -1) != DB_VERSION) {
            new Thread(() -> {
                List<Course> allLessons = Lessons.getAllLessons();
                appDatabase.getCourseDao().insertAll(allLessons);
                for (Course allLesson : allLessons) {
                    appDatabase.getLessonDao().insertAll(allLesson.getLessons());
                }
                sharedPreferences.edit()
                        .putInt("DB_VERSION", DB_VERSION)
                        .apply();
            }).start();
        }
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
