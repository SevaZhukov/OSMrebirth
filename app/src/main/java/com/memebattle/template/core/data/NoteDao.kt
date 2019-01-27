package com.memebattle.template.core.data

import androidx.room.*
import com.memebattle.template.core.domain.model.Note
import io.reactivex.Single

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Long): Note

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()

    @Update
    fun updateNote(note: Note)

    //statistic
    @Query("SELECT * FROM notes")
    fun getInterval(): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE afterSleep = :afterSleep")
    fun getIntervalFilterMoment(afterSleep: Boolean): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod")
    fun getIntervalFilterPeriod(beginPeriod: Long): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod AND afterSleep = :afterSleep")
    fun getIntervalFilterAll(beginPeriod: Long, afterSleep: Boolean): Single<List<Note>>

    //range
    @Query("SELECT * FROM notes LIMIT :startPosition, :loadSize")
    fun getPage(startPosition: Int, loadSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE afterSleep = :afterSleep LIMIT :startPosition, :loadSize")
    fun getPageFilterMoment(afterSleep: Boolean, startPosition: Int, loadSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod LIMIT :startPosition, :loadSize")
    fun getPageFilterPeriod(beginPeriod: Long, startPosition: Int, loadSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod AND afterSleep = :afterSleep LIMIT :startPosition, :loadSize")
    fun getPageFilterAll(beginPeriod: Long, afterSleep: Boolean, startPosition: Int, loadSize: Int): Single<List<Note>>

    //first page
    @Query("SELECT * FROM notes LIMIT 1")
    fun checkOnEmpty(): Single<List<Note>>

    @Query("SELECT * FROM notes LIMIT :pageSize")
    fun getFirstPage(pageSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE afterSleep = :afterSleep LIMIT :pageSize")
    fun getFirstPageFilterMoment(afterSleep: Boolean, pageSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod LIMIT :pageSize")
    fun getFirstPageFilterPeriod(beginPeriod: Long, pageSize: Int): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE date >= :beginPeriod AND afterSleep = :afterSleep LIMIT :pageSize")
    fun getFirstPageFilterAll(beginPeriod: Long, afterSleep: Boolean, pageSize: Int): Single<List<Note>>
}