package com.cleancode.calcurator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cleancode.calcurator.dao.HistoryDao
import com.cleancode.calcurator.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}