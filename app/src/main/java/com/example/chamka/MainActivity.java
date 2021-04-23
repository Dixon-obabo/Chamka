package com.example.chamka;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button  loan,  mkdepo, logout, button1, button2, button3, button4;
    FloatingActionButton  btn,deposit;
    ExtendedFloatingActionButton btm;
    EditText dpamount, rsn, lnamount, acc_num, buss_num;
    ScrollView holder;
    FirebaseFirestore datastore=FirebaseFirestore.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    Dialog dialog;
    String uid,phone,name,email;
    loan_adapter ladap;
    transaction_adapter tadap;
    TextView nm,em,phn,title, title2;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseUser currentuser;
    FirebaseRecyclerOptions<loan> opt;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.btn1);
        button2=findViewById(R.id.btn2);
        loan=findViewById(R.id.rqloan);
        title=findViewById(R.id.title);
        title2=findViewById(R.id.txtviw);
        holder=findViewById(R.id.holder);
        recyclerView=findViewById(R.id.recycler);
        deposit=findViewById(R.id.Deposit);
        dialog= new Dialog(this);
        btn=findViewById(R.id.request);
        btm=findViewById(R.id.uname);
        auth=FirebaseAuth.getInstance();
        currentuser=auth.getCurrentUser();
        //getdata();
        gettransaction();
        check_login();

    }

    public void openrequest(View view) {

        if(holder.getVisibility()==View.GONE){
            holder.setVisibility(View.VISIBLE);

            btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24));

        }else {
            holder.setVisibility(View.GONE);
            btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24));

        }
    }


    public void depositdialog(View view) {
    }

    public void loandialog(View view) {

    }

    public void makedeposit(View view) {

    }


    public void check_login(){
        if (currentuser==null){
            Intent intent= new Intent(getApplicationContext(),Sign_in.class);
            startActivity(intent);
        }else {
            uid=currentuser.getUid();

            database.getReference("Users").child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String data[]=snapshot.getValue().toString().split(",");
                    phone=data[0].replace("{phone=","");
                    name=data[1].replace("name=","");
                    email=data[2].replace("email=","");
                    btm.setText(name);

                    Toast.makeText(MainActivity.this, email, Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


    public  void getdata(){
        opt=new FirebaseRecyclerOptions.Builder<loan>().setQuery(FirebaseDatabase.getInstance().getReference("Att_Loan"),loan.class).build();
        ladap= new loan_adapter(opt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ladap);

    }

    public void gettransaction(){
        Query query=datastore.collection("Transactions").whereEqualTo("userid",currentuser.getUid());

        FirestoreRecyclerOptions<transaction> options= new FirestoreRecyclerOptions.Builder<transaction>().setQuery(query,transaction.class).build();
        tadap= new transaction_adapter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tadap);

    }
    @Override
    protected void onStart() {
        super.onStart();

        tadap.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        
        tadap.stopListening();
    }

    public void signout(View view) {
        auth.signOut();
        Intent intent = new Intent(getApplicationContext(),Sign_in.class);
        startActivity(intent);
    }

    public void User_dialog(View view) {
        dialog.setContentView(R.layout.user_dialog);
        nm=dialog.findViewById(R.id.name);
        phn=dialog.findViewById(R.id.phone);
        em=dialog.findViewById(R.id.email);
        logout=dialog.findViewById(R.id.logout);
        nm.setText(name);
        phn.setText(phone);
        em.setText(email);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent= new Intent(getApplicationContext(),Sign_in.class);
                startActivity(intent);
            }
        });

    dialog.show();

    }

    public void open_chama(View view) {

        dialog.setContentView(R.layout.loandialog);
        loan=dialog.findViewById(R.id.rqloan);
        title=dialog.findViewById(R.id.title);

        rsn=dialog.findViewById(R.id.reason);
        lnamount=dialog.findViewById(R.id.loanamount);
        button2=dialog.findViewById(R.id.btn2);
        button1=dialog.findViewById(R.id.btn1);
        loan.setText("Request");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsn.setVisibility(View.GONE);
                loan.setText("Deposit");
                title.setText("Make a deposit");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsn.setVisibility(View.VISIBLE);
                loan.setText("Request");
                title.setText("Request a loan");
            }
        });

        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loan.getText()=="Deposit"){

                    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/YYYY 'at' hh:mm");
                    String date= sdf.format(new Date());
                    deposit mydeposit= new deposit(currentuser.getUid(),lnamount.getText().toString(),date,phone);
                    database.getReference("Att_Depo").child(database.getReference().push().getKey()).setValue(mydeposit).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Please provide Mpesa with your pin", Toast.LENGTH_LONG).show();
                        }
                    });
                }else if(loan.getText()=="Request"){
                    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/YYYY 'at' hh:mm");
                    String date= sdf.format(new Date());
                    loan myloan = new loan(currentuser.getUid(),lnamount.getText().toString(),currentuser.getEmail(),rsn.getText().toString(),date,phone);
                    database.getReference("Att_Loan").child(database.getReference().push().getKey()).setValue(myloan).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Your loan is being processed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
        dialog.show();
    }


    public void open_lnmp(View view) {
        dialog.setContentView(R.layout.depositdialog);
        mkdepo=dialog.findViewById(R.id.mkdeposit);
        dpamount=dialog.findViewById(R.id.depositamount);
        button3=dialog.findViewById(R.id.btn3);
        button4=dialog.findViewById(R.id.btn4);
        title2=dialog.findViewById(R.id.txtviw);
        buss_num=dialog.findViewById(R.id.bussiness_num);
        acc_num=dialog.findViewById(R.id.account_num);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title2.setText("Lipa na Mpesa");
                acc_num.setVisibility(View.GONE);
                mkdepo.setText("Pay");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title2.setText("Pay your bill");
                mkdepo.setText("Pay Bill");
                acc_num.setVisibility(View.VISIBLE);
            }
        });
        mkdepo.setText("Pay");
        mkdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mkdepo.getText()=="Pay"){


                }else if(mkdepo.getText()=="Pay Bill"){

                }
                SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/YYYY 'at' hh:mm:ss");
                String date= sdf.format(new Date());
                deposit mydeposit= new deposit(currentuser.getUid(),dpamount.getText().toString(),date,phone);
                database.getReference("Att_Depo").child(database.getReference().push().getKey()).setValue(mydeposit).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Deposit attempt processing", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.show();

    }
}
