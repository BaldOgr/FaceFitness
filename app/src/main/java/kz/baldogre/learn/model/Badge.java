package kz.baldogre.learn.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Badge {
    @PrimaryKey
    private int id = 0;
    private int count;

    public Badge(int id, int count) {
        this.id = id;
        this.count = count;
    }

    @Ignore
    public Badge(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
