package com.example.chamka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loan, deposit, mkdepo,rqloan;
    boolean clicked=false;
    EditText dpamount, rsn, lnamount;
    Dialog dialog;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loan=findViewById(R.id.loan);
        deposit=findViewById(R.id.Deposit);
        dialog= new Dialog(this);

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
                Toast.makeText(MainActivity.this, dpamount.getText().toString(), Toast.LENGTH_SHORT).show();
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
}