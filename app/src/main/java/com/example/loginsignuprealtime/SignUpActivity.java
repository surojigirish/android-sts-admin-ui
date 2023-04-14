package com.example.loginsignuprealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class SignUpActivity extends AppCompatActivity {

    EditText signupName,signupEmail, signupPassword,signupUsername;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signupName=findViewById(R.id.signup_name);
        signupEmail=findViewById(R.id.signup_email);
        signupPassword=findViewById(R.id.signup_Password);
        signupUsername=findViewById(R.id.signup_Username);
        loginRedirectText=findViewById(R.id.loginRedirectText);
        signupButton=findViewById(R.id.signup_button);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String name= signupName.getText().toString();
                String email= signupEmail.getText().toString();
                String username= signupUsername.getText().toString();
                String password= signupPassword.getText().toString();


                HelperClass helperClass=new HelperClass(name,email,password,username);
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignUpActivity.this, "You Have SignUp Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}