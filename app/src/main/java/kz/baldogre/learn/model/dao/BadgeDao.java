package kz.baldogre.learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import kz.baldogre.learn.model.Badge;

@Dao
public interface BadgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Badge badge);

    @Query("SELECT * FROM Badge WHERE id = 0")
    Badge getBadge();
}
