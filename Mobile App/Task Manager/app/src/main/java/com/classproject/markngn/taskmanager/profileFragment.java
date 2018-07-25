package com.classproject.markngn.taskmanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class profileFragment extends Fragment {

    private TextView  Name, UserN, EMail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container,false);

        Name = view.findViewById(R.id.tv_name);
        UserN = view.findViewById(R.id.tv_username);
        EMail = view.findViewById(R.id.tv_email);


        Name.setText(LoginActivity.firstname + " " + LoginActivity.lastname);
        UserN.setText(LoginActivity.uname);
        EMail.setText(LoginActivity.email);

        return view;
    }
}
