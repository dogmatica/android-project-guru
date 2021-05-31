package com.example.projectguru.data;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table WHERE user_id = 1")
    User getUser();

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Insert
    void insertUser(User user);
}
