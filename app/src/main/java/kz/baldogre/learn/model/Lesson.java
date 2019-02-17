package kz.baldogre.learn.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Lesson {
    @PrimaryKey
    private int id;
    private String description;
    private String link;
    private int currentMillis = 0;
    private boolean passed = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getCurrentMillis() {
        return currentMillis;
    }

    public void setCurrentMillis(int currentMillis) {
        this.currentMillis = currentMillis;
    }
}
