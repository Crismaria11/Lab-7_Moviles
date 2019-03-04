/*
Cristina Bautista
Esta es la clase que donde se maneja la funciones de la base de datos de ContactDatabase
 */
package com.example.lab_7

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ContactRepository(application: Application) {

    private var contactInt: ContactInt

    private var allContacts: LiveData<List<Contact>>

    init {
        val database: ContactDatabase = ContactDatabase.getInstance(
            application.applicationContext
        )!!
        contactInt = database.contactInt()
        allContacts = contactInt.getAllContacts()
    }

    fun insert(contact: Contact) {
        val insertContactAsyncTask = InsertContactAsyncTask(contactInt).execute(contact)
    }

    fun update(contact: Contact) {
        val updateContactAsyncTask = UpdateContactAsyncTask(contactInt).execute(contact)
    }


    fun delete(contact: Contact) {
        val deleteContactAsyncTask = DeleteContactAsyncTask(contactInt).execute(contact)
    }

    fun deleteAllContacts() {
        val deleteAllContactsAsyncTask = DeleteAllContactsAsyncTask(
            contactInt
        ).execute()
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return allContacts
    }

    companion object {
        private class InsertContactAsyncTask(contactInt: ContactInt) : AsyncTask<Contact, Unit, Unit>() {
            val contactInt = contactInt

            override fun doInBackground(vararg p0: Contact?) {
                contactInt.insert(p0[0]!!)
            }
        }

        private class UpdateContactAsyncTask(contactInt: ContactInt) : AsyncTask<Contact, Unit, Unit>() {
            val contactInt = contactInt

            override fun doInBackground(vararg p0: Contact?) {
                contactInt.update(p0[0]!!)
            }
        }

        private class DeleteContactAsyncTask(contactInt: ContactInt) : AsyncTask<Contact, Unit, Unit>() {
            val contactInt = contactInt

            override fun doInBackground(vararg p0: Contact?) {
                contactInt.delete(p0[0]!!)
            }
        }

        private class DeleteAllContactsAsyncTask(contactInt: ContactInt) : AsyncTask<Unit, Unit, Unit>() {
            val contactInt = contactInt

            override fun doInBackground(vararg p0: Unit?) {
                contactInt.deleteAllContacts()
            }
        }
    }
}