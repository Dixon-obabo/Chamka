package com.example.chamka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sign_in extends AppCompatActivity {

    EditText mail,phone,name;
    EditText password;
    FirebaseAuth auth;
    FirebaseDatabase db;
    FirebaseUser currentuser;
    TextView sigin,signup;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mail=findViewById(R.id.email);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        login=findViewById(R.id.login);

        name=findViewById(R.id.name);
        sigin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
    }

    public void signin(View view) {
        if(login.getText().toString()=="Log-In"){

            auth.signInWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (login.getText().toString()=="Sign-Up"){
            auth.createUserWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    SimpleDateFormat sdf= new SimpleDateFormat("DD/MM/YYYY 'at' HH:MM:SS");
                    String date= sdf.format(new Date());
                    String uid=authResult.getUser().getUid();
                    user me = new user(name.getText().toString(),mail.getText().toString(),phone.getText().toString(),uid,date);
                    db.getReference("Users").child(uid).setValue(me);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    public void show(View view) {
        phone.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);
        login.setText("Sign-Up");
        sigin.setVisibility(View.VISIBLE);
        signup.setVisibility(View.GONE);
    }

    public void hide(View view) {
        phone.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        login.setText("Log-In");
        sigin.setVisibility(View.GONE);
        signup.setVisibility(View.VISIBLE);

    }
}