package com.onurmert.studentsnotes.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.ViewModel.StudentDetailViewModel;
import com.onurmert.studentsnotes.databinding.FragmentStudentDetailBinding;
import java.util.Locale;

public class StudentDetailFragment extends BottomSheetDialogFragment {

    private StudentDetailViewModel viewModel;

    private FragmentStudentDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(StudentDetailViewModel.class);
        viewModel.getOneStudent(requireContext(), getID());
    }

    @Override
    public void onResume() {
        super.onResume();

        getOneStudent();
    }

    @SuppressLint("SetTextI18n")
    private void init(StudentsModel studentsModel){

        binding.textViewName.setText(studentsModel.getName().toUpperCase(Locale.ROOT));
        binding.textViewClass.setText("Class: " + studentsModel.getStudentClass());
        binding.textViewNote.setText("Note: " + String.valueOf(studentsModel.getNotes()));

        if (!studentsModel.getStudentOpinion().equals("")){
            binding.textViewOpinion.setText(studentsModel.getStudentOpinion());
        }else {
            binding.textViewOpinion.setText("");
        }
    }

    private void getOneStudent(){
        viewModel.studentModel.observe(requireActivity(), new Observer<StudentsModel>() {
            @Override
            public void onChanged(StudentsModel studentsModel) {
                init(studentsModel);
            }
        });
    }

    private int getID(){
        Bundle bundle = getArguments();
        StudentDetailFragmentArgs args = StudentDetailFragmentArgs.fromBundle(bundle);
        return args.getId();
    }
}