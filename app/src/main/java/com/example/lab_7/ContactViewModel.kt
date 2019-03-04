package com.example.lab_7

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: ContactRepository =
        ContactRepository(application)
    private var allContact: LiveData<List<Contact>> = repository.getAllContacts()

    fun insert(note: Contact) {
        repository.insert(note)
    }

    fun update(note: Contact) {
        repository.update(note)
    }

    fun delete(note: Contact) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllContacts()
    }

    fun getAllNotes(): LiveData<List<Contact>> {
        return allContact
    }
}