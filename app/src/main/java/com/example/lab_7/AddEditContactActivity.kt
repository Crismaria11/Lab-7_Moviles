/*
Cristina Bautista
Esta es la clase de la actividad para editar los contactos
 */
package com.example.lab_7

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_edit_contact.*

class AddEditContactActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.lab_7.EXTRA_ID"
        const val EXTRA_NAME = "com.example.lab_7.EXTRA_NAME"
        const val EXTRA_EMAIL = "com.example.lab_7.EXTRA_EMAIL"
        const val EXTRA_PRIORITY = "com.example.lab_7.EXTRA_PRIORITY"
        const val EXTRA_PHONENUMBER = "com.example.lab_7.EXTRA_PHONENUMBER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_contact)

        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Contact"
            edit_text_name.setText(intent.getStringExtra(EXTRA_NAME))
            edit_text_email.setText(intent.getStringExtra(EXTRA_EMAIL))
            edit_text_phoneNumber.setText(intent.getStringExtra(EXTRA_PHONENUMBER))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
        } else {
            title = "Add Contact"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_contact -> {
                saveContact()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveContact() {
        if (edit_text_name.text.toString().trim().isBlank() || edit_text_email.text.toString().trim().isBlank() || edit_text_phoneNumber.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert empty contact!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_NAME, edit_text_name.text.toString())
            putExtra(EXTRA_EMAIL, edit_text_email.text.toString())
            putExtra(EXTRA_PHONENUMBER, edit_text_phoneNumber.text.toString())
            putExtra(EXTRA_PRIORITY, number_picker_priority.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}