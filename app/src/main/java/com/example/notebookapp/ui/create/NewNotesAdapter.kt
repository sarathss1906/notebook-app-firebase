package com.example.notebookapp.ui.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notebookapp.databinding.ListItemNewNotesBinding

class NewNotesAdapter(private val notes: ArrayList<NewNotes>) :
    RecyclerView.Adapter<NewNotesAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null
    inner class ViewHolder(val itemBinding: ListItemNewNotesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(item: NewNotes) {
            itemBinding.tvNewNotesCardTitlte.text = item.title
            itemBinding.tvNewNotesCardDescription.text = item.description
            itemBinding.cvNewNotes.setCardBackgroundColor(item.color)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemNewNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = notes[position]
        holder.itemBinding.clNotes.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
        holder.bindItem(items)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}
