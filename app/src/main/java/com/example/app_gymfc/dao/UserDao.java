package com.example.app_gymfc.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.app_gymfc.models.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT id, firstName, lastName, email, password, dateBirth,height FROM users WHERE email = :email LIMIT 1")
    UserEntity findByEmail(String email);

    @Insert
    long insert(UserEntity user);
}
