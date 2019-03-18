package kz.baldogre.learn.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class LastOpenApp {
    @PrimaryKey
    private int id = 0;
    private int dayOfMonth;

    public LastOpenApp(int id, int dayOfMonth) {
        this.id = id;
        this.dayOfMonth = dayOfMonth;
    }

    @Ignore
    public LastOpenApp() {
    }

    @Ignore
    public LastOpenApp(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
