package com.example.chamka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button loan, deposit, mkdepo,rqloan;
    boolean clicked=false;
    EditText dpamount, rsn, lnamount;
    LinearLayout holder;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    Dialog dialog;
    loan_adapter ladap;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseUser currentuser;
    FirebaseRecyclerOptions<loan> opt;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loan=findViewById(R.id.loan);
        holder.findViewById(R.id.holder);
        recyclerView=findViewById(R.id.recycler);
        deposit=findViewById(R.id.Deposit);
        dialog= new Dialog(this);
        auth=FirebaseAuth.getInstance();
        currentuser=auth.getCurrentUser();
        getdata();

        check_login();

    }

    public void openrequest(View view) {
        if(holder.getVisibility()==View.GONE){
            holder.setVisibility(View.VISIBLE);
        }else {
            holder.setVisibility(View.GONE);
        }
    }


    public void depositdialog(View view) {
        dialog.setContentView(R.layout.depositdialog);
        mkdepo=dialog.findViewById(R.id.mkdeposit);
        dpamount=dialog.findViewById(R.id.depositamount);
        mkdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf= new SimpleDateFormat("YYYY/MM/DD 'at' HH:MM:SS");
                String date= sdf.format(new Date());
                deposit mydeposit= new deposit(currentuser.getUid(),dpamount.getText().toString(),date,"0796142444");
                database.getReference("Att_Depo").child(database.getReference().push().getKey()).setValue(mydeposit).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Attempting A Deposit Request", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.show();

    }

    public void loandialog(View view) {
        dialog.setContentView(R.layout.loandialog);
        loan=dialog.findViewById(R.id.rqloan);
        rsn=dialog.findViewById(R.id.reason);
        lnamount=dialog.findViewById(R.id.loanamount);

        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf= new SimpleDateFormat("YYYY/MM/DD 'at' HH:MM:SS");
                String date= sdf.format(new Date());
                loan myloan = new loan(currentuser.getUid(),lnamount.getText().toString(),currentuser.getEmail(),rsn.getText().toString(),date,"0796142444");
                database.getReference("Att_Loan").child(database.getReference().push().getKey()).setValue(myloan).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Your loan request has been received", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        dialog.show();
    }

    public void makedeposit(View view) {

    }


    public void check_login(){
        if (currentuser==null){
            Intent intent= new Intent(getApplicationContext(),Sign_in.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }
    }


    public  void getdata(){
        opt=new FirebaseRecyclerOptions.Builder<loan>().setQuery(FirebaseDatabase.getInstance().getReference("Att_Depo"),loan.class).build();
        ladap= new loan_adapter(opt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ladap);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ladap.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ladap.stopListening();
    }

    public void signout(View view) {
        auth.signOut();
        Intent intent = new Intent(getApplicationContext(),Sign_in.class);
        startActivity(intent);
    }
}
