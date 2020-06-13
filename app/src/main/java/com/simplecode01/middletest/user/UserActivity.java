package com.simplecode01.middletest.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.simplecode01.middletest.Function.Preferences;
import com.simplecode01.middletest.R;

public class    UserActivity extends AppCompatActivity {

    TextView TV_User ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TV_User = (TextView)findViewById(R.id.tv_user);
        TV_User.setText("Welcome! " + Preferences.getLoggedInUser(getBaseContext()));
        String usernameget = Preferences.getLoggedInUser(getBaseContext());

        Log.d("LOG", "USERNAME " + usernameget);
        getDataUserLogged(usernameget);
    }
}

