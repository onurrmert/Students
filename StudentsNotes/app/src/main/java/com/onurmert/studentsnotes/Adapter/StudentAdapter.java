package com.onurmert.studentsnotes.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.onurmert.studentsnotes.Model.StudentsModel;
import com.onurmert.studentsnotes.R;
import com.onurmert.studentsnotes.Room.StudentDatabase;
import com.onurmert.studentsnotes.View.StudentsFragmentDirections;
import com.onurmert.studentsnotes.databinding.StudentRecyclerRowBinding;
import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private final ArrayList<StudentsModel> studentsList;

    public StudentAdapter(ArrayList<StudentsModel> studentsList){
        this.studentsList = studentsList;
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        StudentRecyclerRowBinding binding =  StudentRecyclerRowBinding.bind(itemView);

        public StudentViewHolder(View itemView){
            super(itemView);
        }
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.student_recycler_row, parent, false);

        return new  StudentViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.recyclerTextView.setText((position +1) +") " + studentsList.get(position).getName());

        holder.binding.studentRowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections directions = StudentsFragmentDirections
                        .actionStudentsFragmentToStudentDetailFragment(studentsList.get(position).getId());
                Navigation.findNavController(view).navigate(directions);
            }
        });

        holder.binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup(holder.itemView.getContext(), view, position, studentsList.get(position).getId());
            }
        });
    }
    private void popup(Context context, View view, int position, int id){

        PopupMenu popupMenu =  new PopupMenu(context, view);
        popupMenu.inflate(R.menu.pop_up_menu);
        popupMenu.setOnMenuItemClickListener( item->{
            if (item.getItemId() == R.id.delete){
                deleteStudent(view, context, position, id);
            }else if (item.getItemId() == R.id.update){
                NavDirections directions = StudentsFragmentDirections.actionStudentsFragmentToUpdateFragment(id);
                Navigation.findNavController(view).navigate(directions);
            }
            return true;
        });
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    @SuppressLint({"NotifyDataSetChanged", "ResourceAsColor"})
    private void deleteStudent(View view, Context context, int position, int id){
        StudentDatabase studentDatabase = StudentDatabase.getDatabase(context);
        Snackbar.make(view, "Do you want delete?", Snackbar.LENGTH_LONG)
                .setActionTextColor(R.color.white)
            .setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    studentsList.remove(position);
                    notifyDataSetChanged();
                    StudentDatabase.databaseWriteExecutor.execute(()->{
                        studentDatabase.studentDao().delete(id);
                    });
                }
            }).show();
    }
}
