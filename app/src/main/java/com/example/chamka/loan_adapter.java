package com.example.chamka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class loan_adapter extends FirebaseRecyclerAdapter <loan,loan_adapter.myviewholder> {


    public loan_adapter(@NonNull FirebaseRecyclerOptions<loan> options) {
        super(options);
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView id;
        TextView amount;
        TextView email;
        TextView description;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            this.id =itemView.findViewById(R.id.);
            this.amount = amount;
            this.email = email;
            this.description = description;
        }


    }



}
