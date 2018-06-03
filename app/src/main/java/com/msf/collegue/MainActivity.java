package com.msf.collegue;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements
        FirebaseAuth.AuthStateListener {


    //@BindView(R.id.button2)
    Button getFcmToken;
    private FirebaseAnalytics mFirebaseAnalytics;
    LoginFragment mLoginFragment;
    FirebaseUser mHomeuser ;
    FirebaseAuth firebaseAuth;
    AccountSetupFragment mAccountSetupFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        mHomeuser = firebaseAuth.getCurrentUser();
       // System.out.println(mHomeuser.getDisplayName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
       // ButterKnife.bind(this);


         fragmentManager = getSupportFragmentManager();
         fragmentTransaction = fragmentManager.beginTransaction();

         if (firebaseAuth  == null)
         {
       mLoginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.root_layout, mLoginFragment).commitNow();}
        else {
             FirebaseUser user = firebaseAuth.getCurrentUser();
             mAccountSetupFragment = new AccountSetupFragment();
             Bundle b = new Bundle();
             b.putString("name",user.getDisplayName());
             b.putString("email",user.getEmail());
             b.putString("DPurl", String.valueOf(user.getPhotoUrl()));

             mAccountSetupFragment.setArguments(b);
             fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.add(R.id.root_layout,mAccountSetupFragment).addToBackStack(null).commitNow();
         }



    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

        System.out.println("auth state change listener");

        if (firebaseAuth.getCurrentUser()!= null)
        {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            System.out.println("auth state change listener  user not null");
            mAccountSetupFragment = new AccountSetupFragment();
            Bundle b = new Bundle();
            b.putString("name",user.getDisplayName());
            b.putString("email",user.getEmail());
            b.putString("DPurl", String.valueOf(user.getPhotoUrl()));

            mAccountSetupFragment.setArguments(b);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.root_layout,mAccountSetupFragment).addToBackStack(null).commit();



        }

    }
//    @OnClick(R.id.button2)
//    public void getToken()
//    {
//        new FCMTokenId().onTokenRefresh();
//    }
}
