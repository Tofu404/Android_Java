package com.example.shensuo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment_layout, container, false);
        addLisener(view);
        return view;
    }

    private void addLisener(View view){
        view.findViewById(R.id.connect_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectUs.startConnectUsActivity(getContext());
            }
        });
    }
}
