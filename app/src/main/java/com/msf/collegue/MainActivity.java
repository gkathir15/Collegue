package com.msf.collegue;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.msf.collegue.constants.Constants;


public class MainActivity extends FragmentActivity implements
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
    ListUserFragment listUserFragment;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    SharedPreferences sharedPreferences;

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
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);


         fragmentManager = getSupportFragmentManager();
         fragmentTransaction = fragmentManager.beginTransaction();

         if (user  == null)
         {
       mLoginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.root_layout, mLoginFragment).commit();}
        else {
             if (!sharedPreferences.getBoolean(Constants.IS_ACCOUNT_SETUP_DONE,false)) {
                 // FirebaseUser user = firebaseAuth.getCurrentUser();
                 mAccountSetupFragment = new AccountSetupFragment();
                 Bundle b = new Bundle();
                 b.putString("name", user.getDisplayName());
                 b.putString("email", user.getEmail());
                 b.putString("DPurl", String.valueOf(user.getPhotoUrl()));

                 mAccountSetupFragment.setArguments(b);
                 //fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.add(R.id.root_layout, mAccountSetupFragment).commit();
             }
             else {
                    listUserFragment = new ListUserFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("Email",user.getEmail());
                    listUserFragment.setArguments(bundle);
               //  fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.add(R.id.root_layout, listUserFragment).commit();


             }
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
