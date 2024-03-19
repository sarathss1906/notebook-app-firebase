package com.example.notebookapp.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notebookapp.R
import com.example.notebookapp.colorList
import com.example.notebookapp.databinding.FragmentCreateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateBinding.inflate(inflater,container,false)


        val dataItems = arrayListOf(
            NewNotes(title = "Interesting idea", description = "Use free text area, feel free to write it all", color = context?.colorList(R.color.default_app_colour)),
            NewNotes(title="Buying Something", description = "Use checklist, so you won't miss anything", color = context?.colorList(R.color.green)),
            NewNotes(title = "Goals", description = "Near/future goals, notes and keep focus", color = context?.colorList(R.color.yellow)),
            NewNotes(title = "Guidance", description = "Create guidance for routine activities", color = context?.colorList(R.color.red)),
            NewNotes(title = "Routine Tasks", description = "Checklist with sub-checklist",color = context?.colorList(R.color.yellow_dark))
        )

        val createNotesAdapter = NewNotesAdapter(dataItems)
        binding.rvNewNotes.adapter = createNotesAdapter

        createNotesAdapter.setOnItemClickListener(object : NewNotesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val navController = findNavController()
               when(position){
                   0 -> navController.navigate(R.id.createNotesFragment)
                   else -> {
                        //no op
                   }
               }
            }
        })

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
}