package com.example.lab_7

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(

    var name: String,

    var email: String,

    var phoneNumber: Int,

    var priority: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}