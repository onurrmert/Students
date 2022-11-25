package com.onurmert.studentsnotes.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.ViewModel.UpdateViewModel;
import com.onurmert.studentsnotes.databinding.FragmentUpdateBinding;

import java.util.Locale;

public class UpdateFragment extends Fragment {

    private FragmentUpdateBinding binding;
    
    private UpdateViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(UpdateViewModel.class);
        viewModel.getOneStudentUpdate(requireContext(), getID());

        getStudent();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update1(view);
            }
        });
    }

    private void getStudent() {

        viewModel.studentModel.observe(requireActivity(), new Observer<StudentsModel>() {
            @Override
            public void onChanged(StudentsModel studentsModel) {
                try {
                    init(studentsModel);
                }catch (Exception exception){
                    System.out.println(exception.getLocalizedMessage());
                }
            }
        });
    }

    private void init(StudentsModel studentsModel){
        binding.nameEdit.setText(studentsModel.getName());
        binding.classEdit.setText(studentsModel.getStudentClass());
        binding.notesEdit.setText(String.valueOf(studentsModel.getNotes()));
        binding.opinionEdit.setText(studentsModel.getStudentOpinion());
    }

    private void update1(View view){

        if (!binding.nameEdit.getText().toString().trim().equals("")){
            String name = binding.nameEdit.getText().toString().trim();

            if (!binding.classEdit.getText().toString().trim().equals("")){
                String class1 = binding.classEdit.getText().toString().trim();

                if (!binding.opinionEdit.getText().toString().trim().equals("")){
                    String opinion = binding.opinionEdit.getText().toString().trim();

                    if (!binding.notesEdit.getText().toString().trim().equals("")){
                        String note = binding.notesEdit.getText().toString().trim();

                        noteControl(name, class1.toUpperCase(Locale.ROOT), note, opinion, view);

                    }else {
                        binding.notesEdit.setError("Fiil int the blanks");
                    }
                }else {
                    binding.opinionEdit.setError("Fiil int the blanks");
                }
            }else {
                binding.classEdit.setError("Fiil int the blanks");
            }
        }else {
            binding.nameEdit.setError("Fiil int the blanks");
        }
    }

    private void noteControl(String name, String class1, String note, String opinion, View view){

        int note1 = Integer.parseInt(note);

        if (note1<=100 && note1>=0){
            updateMessage(new StudentsModel(getID(), name, class1, Integer.parseInt(note), opinion), view);
        }else {
            Toast.makeText(
                    requireContext(),
                    "Note must be greater than 0 and less than 100",
                    Toast.LENGTH_LONG
            ).show();
        }
    }


    private void updateMessage(StudentsModel studentsModel, View view){

        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setMessage("Do you want to update");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                update(studentsModel, view);
            }
        });
        dialog.show();
    }

    private void update(StudentsModel studentsModel, View view){

        try {
            viewModel.updateStudent(requireContext(), studentsModel);
            Toast.makeText(requireContext(), "Update...", Toast.LENGTH_SHORT).show();
            binding.updateButton.setVisibility(View.GONE);
        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
        }
    }

    private int getID(){
        Bundle bundle = getArguments();
        UpdateFragmentArgs args = UpdateFragmentArgs.fromBundle(bundle);
        return args.getId();
    }
}