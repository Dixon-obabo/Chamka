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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button loan, deposit, mkdepo,rqloan;
    boolean clicked=false;
    EditText dpamount, rsn, lnamount;
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
        recyclerView=findViewById(R.id.recycler);
        deposit=findViewById(R.id.Deposit);
        dialog= new Dialog(this);
        auth=FirebaseAuth.getInstance();
        currentuser=auth.getCurrentUser();
        getdata();

        //check_login(currentuser);

    }

    public void openrequest(View view) {
        if(clicked==true){
            loan.setVisibility(View.GONE);
            deposit.setVisibility(View.GONE);
            clicked=false;
        }else {
            loan.setVisibility(View.VISIBLE);
            deposit.setVisibility(View.VISIBLE);
            clicked=true;
        }
    }


    public void depositdialog(View view) {
        dialog.setContentView(R.layout.depositdialog);
        mkdepo=dialog.findViewById(R.id.mkdeposit);
        dpamount=dialog.findViewById(R.id.depositamount);
        mkdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=database.getReference().push().getKey();
                loan myloan=new loan("12","100","cheche.oloo@gmail.com","cool stuff","100:100");
                database.getReference("Att_Depo").child(key).setValue(myloan).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "data has been added", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, rsn.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    public void makedeposit(View view) {

    }


    public void check_login(FirebaseUser user){
        if (user==null){
            Intent intent= new Intent(getApplicationContext(),Sign_in.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }
    }


    public  void getdata(){
        opt=new FirebaseRecyclerOptions.Builder<loan>().setQuery(FirebaseDatabase.getInstance().getReference("Att_depo"),loan.class).build();
        ladap= new loan_adapter(opt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ladap);

    }



}
