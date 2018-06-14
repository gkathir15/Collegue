package com.msf.collegue.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.msf.collegue.model.UserPojo;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter {

    List<UserPojo> teamList;
    int mLayout;
    String mCurrentUserEmail;

    public TeamListAdapter(List<UserPojo> teamList, int mLayout, String mCurrentUserEmail) {
        this.teamList = teamList;
        this.mLayout = mLayout;
        this.mCurrentUserEmail = mCurrentUserEmail;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {

        public viewHolder(View itemView) {
            super(itemView);
        }
    }
}
