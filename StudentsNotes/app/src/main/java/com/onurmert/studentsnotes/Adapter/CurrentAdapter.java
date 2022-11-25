package com.onurmert.studentsnotes.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.onurmert.studentsnotes.R;
import com.onurmert.studentsnotes.View.CurrentFragmentDirections;
import com.onurmert.studentsnotes.databinding.GridRecyclerRowBinding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder>{

    private Set<String> classList = new HashSet<>();

    public CurrentAdapter(Set<String> studentList){
        this.classList = studentList;
    }

    public static class CurrentViewHolder extends RecyclerView.ViewHolder {

        GridRecyclerRowBinding binding = GridRecyclerRowBinding.bind(itemView);

        public CurrentViewHolder(View itemView){
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    @NonNull
    @Override
    public CurrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.grid_recycler_row, parent, false);

        return new CurrentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ArrayList<String> arrayList =  new ArrayList<>(classList);
        holder.binding.gridText.setText(arrayList.get(position));

        holder.binding.gridLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                directions(view, arrayList.get(position));
            }
        });
    }

    private void directions(View view, String class1){
        NavDirections direction = CurrentFragmentDirections.actionCurrentFragmentToStudentsFragment(class1);
        Navigation.findNavController(view).navigate(direction);
    }
}
