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
import com.simplecode01.middletest.model.ResponseRegister;
import com.simplecode01.middletest.networking.ApiServices;
import com.simplecode01.middletest.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup_activity extends AppCompatActivity {

    EditText edtnama, edtjeniskelamin, edtusername, edtemail, edtpassword, edtcodeuser, edtphonenumber ;
    Button btnSignUpNow ;
    Bundle adapterBundle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);

        edtnama = (EditText)findViewById(R.id.edt_nama);
        edtjeniskelamin = (EditText)findViewById(R.id.edt_kelamin);
        edtusername = (EditText)findViewById(R.id.edt_user);
        edtemail = (EditText)findViewById(R.id.edt_email);
        edtpassword = (EditText)findViewById(R.id.edt_pass);
        edtcodeuser = (EditText)findViewById(R.id.edt_phonenumber);
        edtphonenumber = (EditText)findViewById(R.id.edt_phonenumber);

        btnSignUpNow =(Button)findViewById(R.id.btnSignUp);




        btnSignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String getNama, getkelamin, getUser, getEmail, getPass, getphone;
                final int getcode;
                getNama = edtnama.getText().toString();
                getkelamin = edtjeniskelamin.getText().toString();
                getUser = edtusername.getText().toString();
                getEmail = edtemail.getText().toString();
                getPass = edtpassword.getText().toString();
                getphone = edtphonenumber.getText().toString();
                getcode = edtcodeuser.getText().hashCode();

                // Todo : Error Part
                if (getNama.isEmpty()){
                    edtnama.setError("nama wajib diisi");
                    edtnama.requestFocus();
                }else if(getkelamin.isEmpty()){
                    edtjeniskelamin.setError("jenis Kelamin wajib diisi");
                    edtjeniskelamin.requestFocus();
                }else if(getUser.isEmpty()){
                    edtusername.setError("Username Wajib Diisi");
                    edtusername.requestFocus();
                }else if(getEmail.isEmpty()){
                    edtemail.setError("Email Wajib Diisi");
                    edtemail.requestFocus();
                }else if(getPass.isEmpty()){
                    edtpassword.setError("masukan Password anda");
                    edtpassword.requestFocus();
                }else if (getphone.isEmpty()){
                    edtphonenumber.setError("Masukan No Tlpn Anda");
                    edtphonenumber.requestFocus();
                }else {
                    Preferences.setRegisteredUser(getBaseContext(), getUser);
                    Preferences.setRegisteredPass(getBaseContext(), getPass);

                    moduleRegisterUser( getcode, getNama, getkelamin, getUser, getEmail, getPass, getphone);
                }

            }
        });

    }

    public void moduleRegisterUser(int kd_user, String nm_user, String jk_user, final String usr_user, String eml_user, final String pwd_user, String ph_user){
        final ProgressDialog dialog = ProgressDialog.show(signup_activity.this, "Please wait...", " Progrees Register Complete");

        ApiServices apiServices = RetrofitClient.getInstanceRetrofit();
        Call<ResponseRegister> responseRegisterCall = apiServices.registerUser(kd_user, nm_user, jk_user, usr_user,eml_user, pwd_user, ph_user);

        responseRegisterCall.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();

                    String message = response.body().getMessage();
                    Boolean status = response.body().isStatus();

                    if (status.equals(true)){
                        Toast.makeText(signup_activity.this, "Success" , Toast.LENGTH_SHORT).show();
                        String body = String.valueOf(response.body().getDataUser());

                        Log.d("LOG", "REGISTER" + body);

                        Intent toSignIn =  new Intent(signup_activity.this, signin.class);
                        startActivity(toSignIn);
                        finish();
                    }else{
                        Toast.makeText(signup_activity.this,"Error" + message, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(signup_activity.this,"Check Connetion" , Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }


}
