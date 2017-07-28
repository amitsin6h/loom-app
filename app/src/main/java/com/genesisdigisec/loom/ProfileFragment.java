package com.genesisdigisec.loom;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView tvuser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        tvuser = (TextView) rootView.findViewById(R.id.tvuser);

        SharedPreferences sharedPref = getActivity().getSharedPreferences(LoginDetail.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String user_email = sharedPref.getString(LoginDetail.EMAIL_SHARED_PREF,"");
        tvuser.setText("Hello "+user_email);




        return rootView;
    }






}