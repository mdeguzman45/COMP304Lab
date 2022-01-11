package com.example.marcdeguzman_comp304sec002_lab1_ex2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TopFragment extends Fragment {

    public TopFragment() {
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
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        // show toast
        Toast.makeText(getActivity(), "Top Fragment OnCreateView executed", Toast.LENGTH_SHORT).show();

        String[] listViewItems = {"AIActivity", "VRActivity"};

        ListView listview = (ListView) view.findViewById(R.id.topFragmentListView);

        // need array adapter to set items in listview
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                listViewItems
        );
        listview.setAdapter(listViewAdapter);

        // add a click listener
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // AIActivity
                    startAIActivity();
                } else if (position == 1) {
                    // VRActivity
                    startVRActivity();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "Top Fragment OnStart executed", Toast.LENGTH_SHORT).show();
    }

    // launch AIActivity
    private void startAIActivity() {
        Intent intent = new Intent(getActivity(), AIActivity.class);

        // moving to the AI activity
        startActivity(intent);
    }

    // launch VRActivity
    private void startVRActivity() {

        Intent intent = new Intent(getActivity(), VRActivity.class);

        // moving to the VR activity
        startActivity(intent);
    }

}