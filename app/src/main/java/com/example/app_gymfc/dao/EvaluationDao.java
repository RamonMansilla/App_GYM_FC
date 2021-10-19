package com.example.app_gymfc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.app_gymfc.models.EvaluationEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface EvaluationDao {
    @Query("SELECT id,date,weight,user_id FROM evaluations WHERE user_id=:userId")
    List<EvaluationEntity> findAll(long userId);

    @Query("SELECT id,date,weight,user_id FROM evaluations WHERE user_id=:userId AND date BETWEEN :from AND :to")
    List<EvaluationEntity> findByRange(Date from, Date to, long userId);

    @Insert
    long insert(EvaluationEntity evaluationEntity);

    @Query("DELETE FROM evaluations WHERE id =:id")
    void delete(long id);


}
