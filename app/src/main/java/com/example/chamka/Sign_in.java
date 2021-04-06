package com.example.chamka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_in extends AppCompatActivity {

    EditText mail,phone,name;
    EditText password;
    FirebaseAuth auth;
    TextView sigin,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mail=findViewById(R.id.email);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        name=findViewById(R.id.name);
        sigin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        auth=FirebaseAuth.getInstance();
    }

    public void signin(View view) {
        Toast.makeText(this, mail.getText().toString(), Toast.LENGTH_SHORT).show();
        auth.signInWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void show(View view) {

    }

    public void hide(View view) {
    }
}