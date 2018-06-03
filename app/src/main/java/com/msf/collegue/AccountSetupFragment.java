package com.msf.collegue;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSetupFragment extends Fragment {

    String mName;
    String mEmail;
    String mPhoneNo;
    String mDpImageURL;
    String mDesignation;

    ImageView mImageViewIv;
    EditText mNameEt;
    EditText mPhNoEt;
    EditText mDesignationEt;
    EditText mEmailEt;
    FloatingActionButton mProceedNextButton;


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);


        args.getString("name");
        args.getString("email");
        args.getString("phone");
        args.getString("DPurl");
        args.getString("designation");



    }

    public AccountSetupFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_setup, container, false);

        mNameEt = view.findViewById(R.id.Username);
        mEmailEt = view.findViewById(R.id.emailId);
        mDesignationEt = view.findViewById(R.id.Designation);
        mImageViewIv = view.findViewById(R.id.dp);
        mPhNoEt = view.findViewById(R.id.phoneNo);
        mProceedNextButton = view.findViewById(R.id.done);


        mNameEt.setText(mName);
        mEmailEt.setText(mEmail);
        mPhNoEt.setText(mPhoneNo);
        mDesignationEt.setText(mDesignation);
        Picasso.get().load(mDpImageURL).transform(new CropCircleTransformation()).into(mImageViewIv);


        mImageViewIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageview Selection
            }
        });



        return view;
    }

}
