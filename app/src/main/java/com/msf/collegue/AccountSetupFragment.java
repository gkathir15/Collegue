package com.msf.collegue;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;
import com.msf.collegue.constants.Constants;
import com.msf.collegue.model.UserPojo;
import com.squareup.picasso.Picasso;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
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
    String mTeam;

    ImageView mImageViewIv;
    EditText mNameEt;
    EditText mPhNoEt;
    EditText mDesignationEt;
    EditText mEmailEt;
    EditText mTeamEt;
    Toolbar toolbar;
    FloatingActionButton mProceedNextButton;
    UserPojo user;
    ProgressBar progressBar;
    Uri localImageUri;
    SharedPreferences sharedPreferences;

    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    Context context;
    ListUserFragment listUserFragment;
    MainActivity activity;




    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);


       mName= args.getString("name");
        mEmail=args.getString("email");
        mPhoneNo=args.getString("phone");
        mDpImageURL=args.getString("DPurl");
        mDesignation=args.getString("designation");



    }

    public AccountSetupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_setup, container, false);
        context = getActivity();
        toolbar = view.findViewById(R.id.toolbar);
        mNameEt = view.findViewById(R.id.Username);
        mEmailEt = view.findViewById(R.id.emailId);
        mDesignationEt = view.findViewById(R.id.Designation);
        mImageViewIv = view.findViewById(R.id.dp);
        mPhNoEt = view.findViewById(R.id.phoneNo);
        mTeamEt = view.findViewById(R.id.Team);
        mProceedNextButton = view.findViewById(R.id.done);
        progressBar= view.findViewById(R.id.progress);

        toolbar.setTitle("Profile");

        System.out.println(mName);
        storageReference = FirebaseStorage.getInstance().getReference();


        mNameEt.setText(mName);
        mEmailEt.setText(mEmail);
        mPhNoEt.setText(mPhoneNo);
        mDesignationEt.setText(mDesignation);
        Picasso.get().load(mDpImageURL).transform(new CropCircleTransformation()).into(mImageViewIv);

        mEmailEt.setEnabled(false);


        mImageViewIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });

        mProceedNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDesignation = mDesignationEt.getText().toString();
                mName = mNameEt.getText().toString();
                mPhoneNo = mPhNoEt.getText().toString().trim();
                mTeam = mTeamEt.getText().toString();
                user = new UserPojo(mName,mEmail,mPhoneNo,mDesignation,mDpImageURL,activity.getmHomeuser().getUid(),mTeam);

                WriteDataToDb();
                sharedPreferences = context.getSharedPreferences(Constants.sharedPerfName,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Constants.IS_ACCOUNT_SETUP_DONE,true);
                editor.apply();
                listUserFragment = new ListUserFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Email",mEmail);
                listUserFragment.setArguments(bundle);
                // getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.root_layout,listUserFragment).commit();
                activity.fragmentManager.popBackStack();
                Intent i = new Intent(activity,HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                activity.finish();

            }
        });



        return view;
    }


    public boolean getImage()
    {
        RxImagePicker.with(context).requestImage(Sources.GALLERY).subscribe(new Consumer<Uri>() {
            @Override
            public void accept(@NonNull Uri uri) throws Exception {


                progressBar.setIndeterminate(true);
                progressBar.setVisibility(View.VISIBLE);
                mProceedNextButton.setActivated(false);
                mProceedNextButton.setEnabled(false);

                Log.d("selected image",uri.toString());
                localImageUri = uri;
                Picasso.get().load(uri).transform(new CropCircleTransformation()).into(mImageViewIv);
                //Get image by uri using one of image loading libraries. I use Glide in sample app.
                // Create a storage reference from our app
                 storageReference = storageReference.child(Constants.profileImagePath +mEmail+".jpg");
                 storageReference.putFile(localImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                         Toast.makeText(context,"Profile image updated",Toast.LENGTH_LONG).show();
                        // mDpImageURL = storageReference.getDownloadUrl().toString();
                         //System.out.println( storageReference.getParent().getDownloadUrl().getResult());
                         storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                             @Override
                             public void onSuccess(Uri uri) {
                                 mDpImageURL= uri.toString();
                                 System.out.println( mDpImageURL);

                             }
                         });

                         progressBar.setVisibility(View.GONE);
                         mProceedNextButton.setActivated(true);
                         mProceedNextButton.setEnabled(true);

                     }
                 });






            }
        });


        return true;
    }

    public void WriteDataToDb(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference(Constants.userData);
        mDatabase.child(user.getId()).setValue(user);
        //mDatabase.setValue(user);
    }




}
