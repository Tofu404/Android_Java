package com.example.shensuo2.meFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shensuo2.R;

public class MeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Intent intent = new Intent();
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        final TextView connectUs = view.findViewById(R.id.connect_us);
        connectUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getContext(),connectUs.class);
                startActivity(intent);
            }
        });

        TextView userServiceAgreement = view.findViewById(R.id.user_service_agreement);
        userServiceAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getContext(),UserServiceAgreement.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
