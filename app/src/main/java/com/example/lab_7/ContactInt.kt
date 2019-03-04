/*
Cristina Bautista
Esta es la interfaz que contiene todas las acciones para los contactos
 */
package com.example.lab_7

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactInt {

    @Insert
    fun insert(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("DELETE FROM contact_table")
    fun deleteAllContacts()

    @Query("SELECT * FROM contact_table ORDER BY priority DESC")
    fun getAllContacts(): LiveData<List<Contact>>

}