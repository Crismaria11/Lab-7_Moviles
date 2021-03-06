/*
Cristina Bautista
Esta es la clase es la que contiene la base de datos de todos los contactos
con su respectiva informacion
 */
package com.example.lab_7

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactInt(): ContactInt


    companion object {
        private var instance: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase? {
            if (instance == null) {
                synchronized(ContactDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "contact_database"
                    )
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: ContactDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val contactInt = db?.contactInt()

        override fun doInBackground(vararg p0: Unit?) {
            contactInt?.insert(Contact("Nombre1", "nombre1@gmail.com", 12345678, 1))
            contactInt?.insert(Contact("Nombre2", "nombre2@gmail.com", 87654321, 2))
            contactInt?.insert(Contact("Nombre3", "nombre3@gmail.com", 13578642, 3))
        }
    }

}