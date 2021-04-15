package com.example.chamka;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class transaction_adapter extends FirestoreRecyclerAdapter<transaction, transaction_adapter.transactionholder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public transaction_adapter(@NonNull FirestoreRecyclerOptions<transaction> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull transactionholder holder, int position, @NonNull transaction model) {
        holder.status.setText(model.getStatus());
        holder.amount.setText(model.getAmount());
        holder.timestamp.setText(model.getTimestamp());
        holder.type.setText(model.getType());
        if(model.getStatus()=="Failed"){
            holder.status.setBackgroundColor(R.color.teal_700);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), model.getType(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public transactionholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_transaction,parent,false);
        return new transactionholder(v);
    }

    class  transactionholder extends RecyclerView.ViewHolder{
        TextView type;
        TextView timestamp;
        TextView amount;
        TextView status;

        public transactionholder(View itemview){
            super(itemview);
            type=itemview.findViewById(R.id.transaction_type);
            timestamp=itemview.findViewById(R.id.transction_date);
            amount=itemview.findViewById(R.id.transction_amount);
            status=itemview.findViewById(R.id.transction_status);
        }
    }
}
