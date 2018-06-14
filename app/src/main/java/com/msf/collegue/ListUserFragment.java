package com.msf.collegue;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.msf.collegue.adapter.TeamListAdapter;
import com.msf.collegue.constants.Constants;
import com.msf.collegue.model.UserPojo;

import java.util.ArrayList;
import java.util.List;


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
    List<UserPojo> mTeamList;


    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    TeamListAdapter mTeamListAdapter;


    RecyclerView mTeamListRecyclerView;


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
        mTeamListRecyclerView = view.findViewById(R.id.teamListRecycler);
        toolbar.setTitle("Team");
        // Inflate the layout for this fragment
        mTeamListAdapter = new TeamListAdapter(mTeamList,R.layout.team_list,CurrentEmail);
        mTeamListRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mTeamListRecyclerView.setAdapter(mTeamListAdapter);
        return  view;
    }


    void getTeamList()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference(Constants.userData);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mTeamList = new ArrayList<>();
                mTeamList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UserPojo userPojo = dataSnapshot1.getValue(UserPojo.class);
                    mTeamList.add(userPojo);
                    mTeamListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
