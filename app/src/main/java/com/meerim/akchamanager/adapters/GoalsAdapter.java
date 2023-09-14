package com.meerim.akchamanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meerim.akchamanager.R;
import com.meerim.akchamanager.models.GoalsModel;

import java.util.List;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder> {

    private List<GoalsModel> incomeList;
    private Context context;
    private int i;

    public GoalsAdapter(List<GoalsModel> incomeList, Context context, int i) {
        this.incomeList = incomeList;
        this.context = context;
        this.i = i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_income, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoalsModel incomeModel = incomeList.get(position);
        holder.descriptionTextView.setText(incomeModel.getName());


        if (i > incomeModel.getAmount())
            holder.amountTextView.setText(("100%"));
        else {
            holder.amountTextView.setText(String.valueOf(incomeModel.getAmount()/i).concat("%"));
        }

        holder.dateTextView.setText("KGS "+String.valueOf(incomeModel.getAmount()));
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView;
        TextView amountTextView;
        TextView dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
