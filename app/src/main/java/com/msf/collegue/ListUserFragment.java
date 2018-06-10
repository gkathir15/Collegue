package com.msf.collegue;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListUserFragment extends Fragment {

    Toolbar toolbar;
    String CurrentEmail;
    FirebaseUser CurrentUser;
    Context context;
    MainActivity mainActivity;
    View view;

    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;


    public ListUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        mainActivity = (MainActivity) getActivity();
        CurrentUser = mainActivity.getmHomeuser();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list_user, container, false);
        toolbar = view.findViewById(R.id.teamToolbar);

        toolbar.setTitle("Team");
        // Inflate the layout for this fragment
        return  view;
    }

}
