package com.onurmert.studentsnotes.View;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onurmert.studentsnotes.Adapter.StudentAdapter;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.ViewModel.StudentViewModel;
import com.onurmert.studentsnotes.databinding.FragmentStudentsBinding;
import java.util.ArrayList;
import java.util.List;

public class StudentsFragment extends Fragment {

    private FragmentStudentsBinding binding;

    private StudentViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
        viewModel.getStudent1(requireContext(), getStudentClass());
    }

    @Override
    public void onResume() {
        super.onResume();

        getAllStudent();
    }

    private void getAllStudent(){
        viewModel.studentsList1.observe(this, new Observer<List<StudentsModel>>() {
            @Override
            public void onChanged(List<StudentsModel> studentsModels) {
                createRecycler(studentsModels);
            }
        });
    }

    private void createRecycler(List<StudentsModel> studentsModels){

        final ArrayList<StudentsModel> studentsModels1 = new ArrayList<>(studentsModels);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(new StudentAdapter(studentsModels1));
    }

    private String getStudentClass(){
        Bundle bundle = getArguments();
        StudentsFragmentArgs args = StudentsFragmentArgs.fromBundle(bundle);
        return args.getStudentClass();
    }
}