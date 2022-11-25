package com.onurmert.studentsnotes.View;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onurmert.studentsnotes.Adapter.CurrentAdapter;
import com.onurmert.studentsnotes.ViewModel.CurrentViewModel;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.databinding.FragmentCurrentBinding;

import java.util.HashSet;
import java.util.List;

public class CurrentFragment extends Fragment {

    private FragmentCurrentBinding binding;

    private CurrentViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCurrentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(CurrentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getAllStudent(requireContext());

        getAll();

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections directions = CurrentFragmentDirections.actionCurrentFragmentToInsertFragment();
                Navigation.findNavController(view).navigate(directions);
            }
        });
    }

    private void getAll(){

        viewModel.studentList.observe(this, new Observer<List<StudentsModel>>() {
            @Override
            public void onChanged(List<StudentsModel> studentsModels) {
                createRecycler(studentsModels);
            }
        });
    }

    private void createRecycler(List<StudentsModel> studentsList){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2, LinearLayoutManager.VERTICAL,false);
        binding.recyclerViewCurrent.setAdapter(new CurrentAdapter(getSet1(studentsList)));
        binding.recyclerViewCurrent.setLayoutManager(gridLayoutManager);
    }

    private HashSet<String> getSet1(List<StudentsModel> studentsList){
        HashSet<String> stringSet = new HashSet<>();
        studentsList.forEach(
                item->{
                    stringSet.add(item.getStudentClass());
                }
        );
        return stringSet;
    }
}