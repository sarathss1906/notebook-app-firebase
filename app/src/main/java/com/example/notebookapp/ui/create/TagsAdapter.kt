package com.example.notebookapp.ui.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notebookapp.databinding.ListItemNoteTagsBinding

class TagsAdapter(private val tagsList: List<String>) :
    RecyclerView.Adapter<TagsAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: ListItemNoteTagsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindTags(tag: String) {
            itemBinding.chipNoteTag.text = tag
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsAdapter.ViewHolder {
        return ViewHolder(
            ListItemNoteTagsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tagsList.size
    }

    override fun onBindViewHolder(holder: TagsAdapter.ViewHolder, position: Int) {
        val tagItem = tagsList[position]
        holder.bindTags(tagItem)
    }
}