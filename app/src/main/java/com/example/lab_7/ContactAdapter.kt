/*
Cristina Bautista
Esta clase es para que todos los contactos esten bien organizados y si se tocan vayan a editarse
 */
package com.example.lab_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter : ListAdapter<Contact, ContactAdapter.ContactHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.name == newItem.name
                        && oldItem.email == newItem.email
                        && oldItem.phoneNumber == newItem.phoneNumber
                        && oldItem.priority == newItem.priority
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val currentContact: Contact = getItem(position)

        holder.textViewName.text = currentContact.name
        holder.textViewPriority.text = currentContact.priority.toString()
        holder.textViewEmail.text = currentContact.email
        holder.textViewPhoneNumber.text = currentContact.phoneNumber.toString()
    }

    fun getContactAt(position: Int): Contact {
        return getItem(position)
    }

    inner class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var textViewName: TextView = itemView.text_view_name
        var textViewPriority: TextView = itemView.text_view_priority
        var textViewEmail: TextView = itemView.text_view_email
        var textViewPhoneNumber: TextView = itemView.text_view_phoneNumber
    }

    interface OnItemClickListener {
        fun onItemClick(contact: Contact)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
