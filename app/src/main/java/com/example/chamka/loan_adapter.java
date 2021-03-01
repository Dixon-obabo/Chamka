package com.example.chamka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class loan_adapter extends FirebaseRecyclerAdapter <loan,loan_adapter.myviewholder> {


    public loan_adapter(@NonNull FirebaseRecyclerOptions<loan> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull loan model) {

        holder.amount.setText(model.getAmount());
        holder.description.setText(model.getDescription());
        holder.email.setText(model.getEmail());
        holder.id.setText(model.getUserid());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), model.userid, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_transactions,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView id;
        TextView amount;
        TextView email;
        TextView description;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.user_id);
            amount = itemView.findViewById(R.id.Loan_amount);
            email = itemView.findViewById(R.id.user_email);
            description = itemView.findViewById(R.id.loan_description);
        }


    }



}
