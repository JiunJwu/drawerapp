package com.example.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextfullname,textInputEditTextUsername,textInputEditTextPassword,textInputEditTextEmail;
    Button buttonSignup;
    TextView textViewLogin;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textInputEditTextfullname=findViewById(R.id.fullname);
        textInputEditTextUsername=findViewById(R.id.username);
        textInputEditTextPassword=findViewById(R.id.password);
        textInputEditTextEmail=findViewById(R.id.email);
        buttonSignup=findViewById(R.id.buttonSignUp);
        textViewLogin=findViewById(R.id.loginText);
        progressBar=findViewById(R.id.progress);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), com.example.drawer.Login.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname,username,password,email;
                fullname=String.valueOf(textInputEditTextfullname.getText());
                username=String.valueOf(textInputEditTextUsername.getText());
                password=String.valueOf(textInputEditTextPassword.getText());
                email=String.valueOf(textInputEditTextEmail.getText());

                if(!fullname.equals("")&&!username.equals("")&&!password.equals("")&&!email.equals("")){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];

                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("http://192.168.1.101/LoginRegister/signup.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), com.example.drawer.Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"ALL field required",Toast.LENGTH_SHORT).show();
                }
                //End Write and Read data with URL
            }
        });

    }
}