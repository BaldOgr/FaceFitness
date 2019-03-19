package kz.baldogre.learn;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import kz.baldogre.learn.common.Const;
import kz.baldogre.learn.model.Badge;
import kz.baldogre.learn.model.Course;
import kz.baldogre.learn.model.LastOpenApp;
import kz.baldogre.learn.model.LastViewedLesson;
import kz.baldogre.learn.model.Lessons;
import kz.baldogre.learn.model.db.AppDatabase;

public class App extends Application {
    AppDatabase appDatabase;
    private static int DB_VERSION = Const.CURRENT_DB_VERSION;

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lessons_database")
                .fallbackToDestructiveMigration()
                .build();

        SharedPreferences sharedPreferences = getSharedPreferences("Lesson", MODE_PRIVATE);

        if (!sharedPreferences.contains("badges")) {
            addFirstBadge();
            sharedPreferences.edit()
                    .putBoolean("badges", true)
                    .apply();
        }

        if (sharedPreferences.getInt("DB_VERSION", -1) != DB_VERSION) {
            new Thread(() -> {
                List<Course> allLessons = null;
                try {
                    allLessons = Lessons.getAllLessons(this);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                appDatabase.getCourseDao().insertAll(allLessons);
                appDatabase.getLastViewedDao().insert(new LastViewedLesson());
                appDatabase.getLastOpenAppDao().insert(new LastOpenApp(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
                for (Course allLesson : allLessons) {
                    appDatabase.getLessonDao().insertAll(allLesson.getLessons());
                }
                sharedPreferences.edit()
                        .putInt("DB_VERSION", DB_VERSION)
                        .apply();
            }).start();
        } else {
            appDatabase.getLastOpenAppDao().getLastOpenApp().subscribeOn(Schedulers.io())
                    .subscribe(lastOpenApp -> {
                        if (lastOpenApp == null) {
                            addBadge();
                            return;
                        }

                        int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                        LastOpenApp todayOpen = new LastOpenApp();
                        todayOpen.setDayOfMonth(dayOfMonth);

                        if (todayOpen.getDayOfMonth() - lastOpenApp.getDayOfMonth() == 1) {
                            addBadge();
                        } else if (todayOpen.getDayOfMonth() == 1 && lastOpenApp.getDayOfMonth() >= 28 && lastOpenApp.getDayOfMonth() <= 31) {
                            addBadge();
                        } else if (todayOpen.getDayOfMonth() - lastOpenApp.getDayOfMonth() > 1) {
                            removeBadge();
                        }
                        appDatabase.getLastOpenAppDao().insert(todayOpen);
                    });
        }
    }

    private void addFirstBadge() {
        Badge badge = new Badge(1);
        new Thread(() -> appDatabase.getBadgeDao().insert(badge)).start();
    }

    @SuppressLint("CheckResult")
    private void removeBadge() {
        Completable.fromAction(() -> appDatabase.getBadgeDao().insert(new Badge(1)))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void addBadge() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Badge badge = appDatabase.getBadgeDao().getBadge();
                badge.setCount(badge.getCount() + 1);
                appDatabase.getBadgeDao().insert(badge);
            }
        }).start();

    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
