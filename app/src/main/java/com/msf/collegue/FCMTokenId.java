package com.msf.collegue;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMTokenId extends FirebaseInstanceIdService {

    String tokenID;

    public FCMTokenId() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        tokenID = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM token  ",tokenID);
    }
}
