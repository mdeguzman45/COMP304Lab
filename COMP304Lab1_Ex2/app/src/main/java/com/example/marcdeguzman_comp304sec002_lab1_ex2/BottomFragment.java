package com.example.marcdeguzman_comp304sec002_lab1_ex2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BottomFragment extends Fragment {

    public BottomFragment() {
        // Required empty public constructor
    }
    /*

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Bottom Fragment OnCreateView executed", Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // show toast
        Toast.makeText(getActivity(), "Bottom Fragment OnStart executed", Toast.LENGTH_SHORT).show();
    }

    // public void putArguments(Bundle args) {
    public void putArguments(String msg) {
        TextView bottomTextView = (TextView) getActivity().findViewById(R.id.textViewBottom1);
        bottomTextView.setText(msg);
    }
}