package com.onurmert.studentsnotes.View;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onurmert.studentsnotes.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;

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
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        direcitons(view);
    }

    private void direcitons(View view){

        NavDirections direction = SplashFragmentDirections.actionSplashFragmentToCurrentFragment();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigation.findNavController(view).navigate(direction);
            }
        }, 700);
    }
}