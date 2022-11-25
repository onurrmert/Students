package com.onurmert.studentsnotes.View;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.ViewModel.InsertViewModel;
import com.onurmert.studentsnotes.databinding.FragmentInsertBinding;

import java.util.Locale;

public class InsertFragment extends Fragment {

    private FragmentInsertBinding binding;

    private InsertViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInsertBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(InsertViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStudentModel(view);
            }
        });
    }

    private void insert(StudentsModel studentsModel, View view){
        viewModel.insert(requireContext(), studentsModel);
        Toast.makeText(requireContext(), "Saved...", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).popBackStack();
    }

    private void getStudentModel(View view){

        if (!binding.nameEdit.getText().toString().trim().equals("")){
            String name = binding.nameEdit.getText().toString().trim();

            if (!binding.classEdit.getText().toString().trim().equals("")){
                String class1 = binding.classEdit.getText().toString().trim();

                if (!binding.opinionEdit.getText().toString().trim().equals("")){
                    String opinion = binding.opinionEdit.getText().toString().trim();

                    if (!binding.notesEdit.getText().toString().trim().equals("")){
                        String note = binding.notesEdit.getText().toString().trim();

                        insert(name, class1.toUpperCase(Locale.ROOT), note, opinion, view);

                    }else {
                        binding.notesEdit.setError("Fill int the blanks");
                    }
                }else {
                    binding.opinionEdit.setError("Fill int the blanks");
                }
            }else {
                binding.classEdit.setError("Fill int the blanks");
            }
        }else {
            binding.nameEdit.setError("Fill int the blanks");
        }
    }

    private void insert(String name, String class1, String note, String opinion, View view){

        int note1 = Integer.parseInt(note);

        if (note1<=100 && note1>=0){
            insert(new StudentsModel(name, class1, note1, opinion), view);
        }else {
            Toast.makeText(
                    requireContext(),
                    "Note must be greater than 0 and less than 100",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}