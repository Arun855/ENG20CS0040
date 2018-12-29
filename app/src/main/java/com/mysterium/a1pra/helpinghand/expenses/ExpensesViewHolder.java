package com.mysterium.a1pra.helpinghand.expenses;

import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mysterium.a1pra.helpinghand.R;

public class ExpensesViewHolder extends RecyclerView.ViewHolder{

    private TextView itemNameTv, dateTv, priceTv, remarksTv;
    private Button editB,deleteB;
    private String name, date, price, remarks;

    public ExpensesViewHolder(View itemView)
    {
        super(itemView);
        itemNameTv=itemView.findViewById(R.id.expenses_name_tv);
        dateTv=itemView.findViewById(R.id.expenses_date_tv);
        priceTv=itemView.findViewById(R.id.expenses_price_tv);
        remarksTv=itemView.findViewById(R.id.expenses_remarks_tv);
        editB=itemView.findViewById(R.id.expenses_edit_b);
        deleteB=itemView.findViewById(R.id.expenses_delete_b);

    }

    public void populate(ExpensesModel expensesItem)
    {
        name=expensesItem.getItemName();
        date=expensesItem.getDateAdded();
        price=expensesItem.getPrice();
        remarks=expensesItem.getRemarks();

        itemNameTv.setText(name);
        dateTv.setText(date);;
        remarksTv.setText(remarks);
        priceTv.setText(price);
    }

}
