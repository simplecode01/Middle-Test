package com.simplecode01.middletest.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simplecode01.middletest.Function.Preferences;
import com.simplecode01.middletest.R;
import com.simplecode01.middletest.model.ResponseLogin;
import com.simplecode01.middletest.networking.ApiServices;
import com.simplecode01.middletest.networking.RetrofitClient;
import com.simplecode01.middletest.user.UserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signin extends AppCompatActivity {

    EditText getUsrName, getPwdUser, getCodeUser;
    Button btnSignIn;
    boolean cancel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUsrName = (EditText)findViewById(R.id.edt_user_login);
        getPwdUser = (EditText)findViewById(R.id.edt_pass_login);
        getCodeUser = (EditText)findViewById(R.id.edt_code_login);

        btnSignIn = (Button)findViewById(R.id.btn_Login);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upsetUsername = getUsrName.getText().toString();
                String upsetPassword = getPwdUser.getText().toString();
                String upsetcode = getCodeUser.getText().toString();

                moduleConfirmInput(upsetUsername, upsetPassword, upsetcode);
            }
        });


    }

    public void GoToRegister(View view) {
        Intent abc = new Intent(signin.this, signup_activity.class);
        startActivity(abc);
        finish();
    }

    private void moduleConfirmInput(String upsetUsername, String upsetPassword,String upsetcode) {
        if (TextUtils.isEmpty(upsetUsername)) {
            getUsrName.setError("This field username must be fill!.");
            getUsrName.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(upsetPassword)) {
            getPwdUser.setError("This field password must be fill!.");
            getPwdUser.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(upsetcode)) {
            getPwdUser.setError("This field code must be fill!.");
            getPwdUser.requestFocus();
            cancel = true;
        } else {
            // TODO A.3: Add Preferences Here
            Preferences.setRegisteredUser(getBaseContext(), upsetUsername, upsetcode);
            Preferences.setRegisteredPass(getBaseContext(), upsetPassword, upsetcode);


            moduleLoginUser(upsetUsername, upsetPassword, upsetcode);
        }
    }

    private void moduleLoginUser(String upsetUsername, String upsetPassword, String upsetcode) {
        final ProgressDialog dialog = ProgressDialog.show(signin.this, "Please wait...", "Prosess login on going.");

        ApiServices apiServices = RetrofitClient.getInstanceRetrofit();

        Call<ResponseLogin> responseLoginCall = apiServices.loginUser(upsetUsername, upsetPassword, upsetcode);

        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();

                    String message = response.body().getMessage();
                    int messageCode = response.body().getCode();

                    if (messageCode == 200) {
                        Toast.makeText(signin.this, "Congrate! " + message, Toast.LENGTH_SHORT).show();
                        String dataUserLogin = String.valueOf(response.body().getInformasiUser());
                        Log.d("LOG", "USERLOGIN " + dataUserLogin);

                        String status = "Login successfully.";
                        if (message.equals(status)) {
                            moduleSigninPreference  ();
                        }
                    } else {
                        Toast.makeText(signin.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(signin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        private void moduleSigninPreference() {
            Preferences.setLoggedInUser(getBaseContext(), Preferences.getRegisteredUser(getBaseContext()));
            Preferences.setLoggedInUserStatus(getBaseContext(), true);
            startActivity(new Intent(getBaseContext(), UserActivity.class));
            finish();
        }

    }

}
