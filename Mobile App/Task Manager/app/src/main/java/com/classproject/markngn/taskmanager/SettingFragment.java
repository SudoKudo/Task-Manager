package com.classproject.markngn.taskmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SettingFragment extends Fragment {

   TextView changeUserName, changePass;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);

        changeUserName = view.findViewById(R.id.tv_changeUsername);
        changePass = view.findViewById(R.id.tv_changePass);

        changeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new ChangeUserName());
                fragmentTransaction.commit();
            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction passchange = getFragmentManager().beginTransaction();
                passchange.replace(R.id.fragment_container, new ChangePassword());
                passchange.commit();
            }
        });

        return view;
    }
}
