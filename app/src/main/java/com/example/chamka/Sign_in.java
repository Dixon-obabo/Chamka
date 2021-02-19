package com.example.chamka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_in extends AppCompatActivity {

    EditText mail;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mail=findViewById(R.id.email);
        password=findViewById(R.id.password);
    }

    public void signin(View view) {
        Toast.makeText(this, mail.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}