package kz.baldogre.learn;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;

import kz.baldogre.learn.model.LastViewedLesson;
import kz.baldogre.learn.model.Lessons;
import kz.baldogre.learn.model.db.AppDatabase;

public class App extends Application {
    AppDatabase appDatabase;
    private static int DB_VERSION = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lessons_database").build();

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
                appDatabase.getLessonDao().insertAll(Lessons.getAllLessons());
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
