package com.example.notebookapp.ui.create

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notebookapp.R
import com.example.notebookapp.databinding.FragmentCreateNotesBinding
import com.example.notebookapp.ui.create.viewmodel.CreateNoteViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNotesFragment : Fragment() {
    private lateinit var binding: FragmentCreateNotesBinding
    private lateinit var database:DatabaseReference
    private lateinit var tagAdapter: TagsAdapter
    private val viewModel: CreateNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false)
        binding.edtNewNotesTitle.background = null
        binding.edtNewNotesDescription.background = null
        binding.edtNewNotesTag.background = null

        tagAdapter = TagsAdapter(viewModel.tagList)
        binding.rvNoteTags.adapter = tagAdapter

        setupEditText()

        binding.edtNewNotesTitle.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // When the EditText gains focus, clear the hint
                binding.edtNewNotesTitle.hint = null
            } else {
                if (binding.edtNewNotesTitle.text.isNullOrEmpty()) {
                    binding.edtNewNotesTitle.hint = getString(R.string.enter_title)
                }
            }
        }
        binding.edtNewNotesDescription.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // When the EditText gains focus, clear the hint
                binding.edtNewNotesDescription.hint = null
            } else {
                // When the EditText loses focus, set the hint back
                if (binding.edtNewNotesDescription.text.isNullOrEmpty()) {
                    binding.edtNewNotesDescription.hint = getString(R.string.create_notes_sub_text)
                }
            }
        }
        binding.edtNewNotesTag.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // When the EditText gains focus, clear the hint
                binding.edtNewNotesTag.hint = null
            } else {
                // When the EditText loses focus, set the hint back
                if (binding.edtNewNotesTag.text.isNullOrEmpty()) {
                    binding.edtNewNotesTag.hint = getString(R.string.add_tags)
                }
            }
        }
        binding.btnBackCreateNewNotes.setOnClickListener {
            saveNote()
            findNavController().navigateUp()
        }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveNote()
                    findNavController().navigateUp()
                }
            })
        return binding.root
    }

    private fun setupEditText() {
        binding.edtNewNotesTag.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addTag(binding.edtNewNotesTag.text.toString())
                binding.edtNewNotesTag.text = null
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addTag(tagText: String) {
        viewModel.tagList.add(tagText)
        tagAdapter.notifyDataSetChanged()
    }

    private fun saveNote(){


        val title = binding.edtNewNotesTitle.text.toString()
        val description = binding.edtNewNotesDescription.text.toString()
        val tags = viewModel.tagList
        val updateTime = System.currentTimeMillis()

        if (title.trim().isNotEmpty()){

            database = FirebaseDatabase.getInstance().getReference("notes")

            val notes = CreateNote(title = title,description = description, tags = tags, updateTimestamp = updateTime)

            database.child(title).setValue(notes).addOnSuccessListener {
                binding.edtNewNotesTitle.text.clear()
                binding.edtNewNotesDescription.text.clear()
                binding.edtNewNotesTag.text.clear()
                viewModel.tagList.clear()
            }
        }
    }
}