package com.example.notebookapp.ui.create

import android.content.res.ColorStateList

data class NewNotes(
    val title: String? = null,
    val description: String? = null,
    val color: ColorStateList? = null
)

data class NoteTags(
    val text:String?= null
)

data class CreateNote(
    val title:String?= null,
    val description: String? = null,
    val tags: List<String>? = null,
    val updateTimestamp: Long,
)
