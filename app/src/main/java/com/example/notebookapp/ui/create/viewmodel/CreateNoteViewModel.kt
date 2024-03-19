package com.example.notebookapp.ui.create.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(): ViewModel() {

    val tagList = mutableListOf<String>()

}