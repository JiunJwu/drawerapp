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

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String username, password;
               username = String.valueOf(textInputEditTextUsername.getText());
               password = String.valueOf(textInputEditTextPassword.getText());

               if (!username.equals("") && !password.equals("")) {
                   Handler handler = new Handler();
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           progressBar.setVisibility(View.VISIBLE);
                           String[] field = new String[2];
                           field[0] = "username";
                           field[1] = "password";
                           //Creating array for data
                           String[] data = new String[2];
                           data[0] = username;
                           data[1] = password;
                           String usname=new String();
                           String usemail=new String();
                           String loginState=new String();
                           PutData putData = new PutData("http://192.168.1.100/LoginRegister/login.php", "POST", field, data);

                           if (putData.startPut()) {
                               if (putData.onComplete()) {
                                   progressBar.setVisibility(View.GONE);
                                   String result = putData.getResult();
                                   try{
                                       JSONObject jsonObject = new JSONObject(result);
                                       usname = jsonObject.getString("fullname");
                                       usemail = jsonObject.getString("email");
                                       loginState=jsonObject.getString("Loginstate");
                                   }
                                   catch(JSONException e) {
                                       e.printStackTrace();
                                   }
                                   if (loginState.equals("Login Success")) {
                                       Toast.makeText(getApplicationContext(), loginState, Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                       User user =new User(usname,usemail);
                                       intent.putExtra("user",user);
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
               } else {
                   Toast.makeText(getApplicationContext(), "ALL field required", Toast.LENGTH_SHORT).show();
               }
               //End Write and Read data with URL

           }
        }


    );


    }
}