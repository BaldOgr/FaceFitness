package kz.baldogre.learn.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;
import io.reactivex.Single;
import kz.baldogre.learn.model.Badge;

@Dao
public interface BadgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Badge badge);

    @Query("SELECT * FROM Badge WHERE id = 0")
    Single<Badge> getBadge();
}
