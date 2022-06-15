package com.cleancode.calcurator.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cleancode.calcurator.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM history")
    fun deleteAll()


    // 특정 히스토리만 삭제할 수 있음
    @Delete
    fun deleteHistory(history: History)

    // 특정 조건에 부합하는 히스토리를 가져옴.
    @Query("SELECT * FROM history WHERE result LIKE :result")
    fun findByResult(result: String): List<History>
}