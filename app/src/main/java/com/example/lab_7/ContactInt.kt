package com.example.lab_7

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactInt {

    @Insert
    fun insert(note: Contact)

    @Update
    fun update(note: Contact)

    @Delete
    fun delete(note: Contact)

    @Query("DELETE FROM contact_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM contact_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Contact>>

}