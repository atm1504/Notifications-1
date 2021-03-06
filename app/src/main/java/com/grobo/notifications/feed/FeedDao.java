package com.grobo.notifications.feed;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FeedDao {

    @Query("select * from feed ORDER BY eventDate DESC")
    LiveData<List<FeedItem>> loadAllFeed();

    @Query("select * from feed where eventId = :eventId")
    FeedItem loadFeedByEventId(long eventId);

    @Query("select * from feed where id = :id")
    FeedItem loadFeedById(int id);

    @Query("SELECT * FROM feed WHERE interested = 1 ORDER BY id DESC")
    LiveData<List<FeedItem>> loadGoingEvents();

    @Insert(onConflict = REPLACE)
    void insertFeed(FeedItem feedItem);

    @Insert(onConflict = REPLACE)
    void insertOrReplaceFeed(FeedItem... feedList);

    @Query("DELETE FROM feed WHERE eventDate < :time and interested = 0")
    void deleteOldFeed(long time);

    @Query("delete from feed where eventId like :eventId")
    void deleteFeedByEventId(long eventId);

    @Query("SELECT COUNT(*) FROM feed where eventId = :eventId")
    int feedCount(long eventId);

    @Query("SELECT MAX(id) FROM feed;")
    int getMaxId();
    
}
