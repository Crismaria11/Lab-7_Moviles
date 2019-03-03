package com.example.lab_7

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ContactRepository(application: Application) {

    private var contactInt: ContactInt

    private var allNotes: LiveData<List<Contact>>

    init {
        val database: ContactDatabase = ContactDatabase.getInstance(
            application.applicationContext
        )!!
        contactInt = database.contactInt()
        allNotes = contactInt.getAllNotes()
    }

    fun insert(note: Contact) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(contactInt).execute(note)
    }

    fun update(note: Contact) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(contactInt).execute(note)
    }


    fun delete(note: Contact) {
        val deleteNoteAsyncTask = DeleteNoteAsyncTask(contactInt).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            contactInt
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Contact>> {
        return allNotes
    }

    companion object {
        private class InsertNoteAsyncTask(noteDao: ContactInt) : AsyncTask<Contact, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Contact?) {
                noteDao.insert(p0[0]!!)
            }
        }

        private class UpdateNoteAsyncTask(noteDao: ContactInt) : AsyncTask<Contact, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Contact?) {
                noteDao.update(p0[0]!!)
            }
        }

        private class DeleteNoteAsyncTask(noteDao: ContactInt) : AsyncTask<Contact, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Contact?) {
                noteDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllNotesAsyncTask(noteDao: ContactInt) : AsyncTask<Unit, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Unit?) {
                noteDao.deleteAllNotes()
            }
        }
    }
}