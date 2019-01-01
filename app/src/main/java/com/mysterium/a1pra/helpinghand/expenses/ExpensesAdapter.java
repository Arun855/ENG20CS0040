package com.mysterium.a1pra.helpinghand.expenses;
/*
 * Author: Prabhutva Agrawal
 */
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysterium.a1pra.helpinghand.R;

import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesViewHolder>
    {//subclass of gviewholder.
        List<ExpensesModel> expensesList;

        public ExpensesAdapter(List<ExpensesModel> expensesList) {
            this.expensesList = expensesList;
        }

        @NonNull
        @Override
        public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            // view_g => name of the layout file
            View view = inflater.inflate(R.layout.view_expenses, parent, false);
            ExpensesViewHolder holder = new ExpensesViewHolder(view);
            return holder;
        }//link xml to recycler view

        @Override//means whatever we are extending is changed to put our own stuff
        public void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position) {
            ExpensesModel expenseItem = expensesList.get(position);
            holder.populate(expenseItem,position);
        }

        @Override
        public int getItemCount() {
            return expensesList.size();
        }
    }





