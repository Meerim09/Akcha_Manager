package com.meerim.akchamanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meerim.akchamanager.R;
import com.meerim.akchamanager.activities.ArticleActivity;
import com.meerim.akchamanager.models.ArticleModel;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<ArticleModel> incomeList;
    private Context context;

    public ArticleAdapter(List<ArticleModel> incomeList, Context context) {
        this.incomeList = (ArrayList<ArticleModel>) incomeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_income, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleModel incomeModel = incomeList.get(position);
        holder.descriptionTextView.setText(incomeModel.getDescription());

        holder.itemView.setOnClickListener(v->{
            holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), ArticleActivity.class).putExtra("ctx",String.valueOf(position)));

        });
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
