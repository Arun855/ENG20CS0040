package com.mysterium.a1pra.helpinghand.expenses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mysterium.a1pra.helpinghand.R;

import java.util.ArrayList;

public class ExpensesViewHolder extends RecyclerView.ViewHolder{

    SharedPreferences sharedPreferences;
    private TextView itemNameTv, dateTv, priceTv, remarksTv;
    private EditText itemNameEt, dateEt, priceEt, remarksEt;
    private Button editB,deleteB,saveB;
    private String name, date, price, remarks;
    Context applicationContext = ExpensesActivity.getContextOfApplication();
    /*This is function which uses the SharedPreferences
     * to get database from the sharedpreferences and run
     * a loop to store data in the arraylist
     * category is used as a key so that we dont need to
     * create multiple functions.*/
    public ArrayList<String> getDB(ArrayList<String> arrayList, int length, String category)
    {
        sharedPreferences = applicationContext.getSharedPreferences("myPref", applicationContext.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i=0; i<length; i++)
        {
            String key=category+i;
            String listItem=sharedPreferences.getString(key,null);
            arrayList.add(listItem);
        }
        editor.commit();
        return arrayList;

    }

    /* This is function which uses the SharedPreferences
     * to break the arraylist into Strings and use it to
     * update the shared preferences database.
     * this also updates the length of the list so that
     * new arrays can be initialized later.
     */
    public void updateDB(ArrayList<String> arrayList, String category)
    {
        sharedPreferences = applicationContext.getSharedPreferences("myPref", applicationContext.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] array=new String[arrayList.size()];
        arrayList.toArray(array);
        for(int i=0;i<array.length;i++)
        {
            String key=category+i;
            editor.putString(key, array[i]);
            editor.commit();
        }
        //length updator function is missing here
        editor.commit();
    }

    public ExpensesViewHolder(View itemView)
    {
        super(itemView);
        itemNameTv=itemView.findViewById(R.id.expenses_name_tv);
        dateTv=itemView.findViewById(R.id.expenses_date_tv);
        priceTv=itemView.findViewById(R.id.expenses_price_tv);
        remarksTv=itemView.findViewById(R.id.expenses_remarks_tv);
        itemNameEt=itemView.findViewById(R.id.expenses_name_et);
        dateEt=itemView.findViewById(R.id.expenses_date_et);
        priceEt=itemView.findViewById(R.id.expenses_price_et);
        remarksEt=itemView.findViewById(R.id.expenses_remarks_et);
        editB=itemView.findViewById(R.id.expenses_edit_b);
        deleteB=itemView.findViewById(R.id.expenses_delete_b);
        saveB=itemView.findViewById(R.id.expenses_save_b);

    }

    public void populate(ExpensesModel expensesItem, final int position)
    {
        sharedPreferences = applicationContext.getSharedPreferences("myPref", applicationContext.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        name=expensesItem.getItemName();
        date=expensesItem.getDateAdded();
        price=expensesItem.getPrice();
        remarks=expensesItem.getRemarks();

        itemNameTv.setText(name);
        dateTv.setText(date);
        remarksTv.setText(remarks);
        priceTv.setText(price);
        itemNameEt.setHint(name);
        dateEt.setHint(date);
        remarksEt.setHint(remarks);
        priceEt.setHint(price);

        editB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                priceTv.setVisibility(View.GONE);
                priceEt.setVisibility(View.VISIBLE);
                itemNameTv.setVisibility(View.GONE);
                itemNameEt.setVisibility(View.VISIBLE);
                dateTv.setVisibility(View.GONE);
                dateEt.setVisibility(View.VISIBLE);
                remarksTv.setVisibility(View.GONE);
                remarksEt.setVisibility(View.VISIBLE);
                editB.setVisibility(View.GONE);
                saveB.setVisibility(View.VISIBLE);

                saveB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String> editList=new ArrayList<>();
                        int length=sharedPreferences.getInt("Length",0);

                        editList=getDB(editList,length,"item");
                        if(itemNameEt.getText().toString().isEmpty())
                        {
                            editList.set(position, itemNameEt.getHint().toString());
                        }
                        else {editList.set(position, itemNameEt.getText().toString());}
                        updateDB(editList,"item");

                        editList.clear();
                        editList=getDB(editList,length,"price");
                        if(priceEt.getText().toString().isEmpty())
                        {
                            editList.set(position, priceEt.getHint().toString());
                        }
                        else {editList.set(position, priceEt.getText().toString());}
                        updateDB(editList,"price");

                        editList.clear();
                        editList=getDB(editList,length,"date");
                        if(dateEt.getText().toString().isEmpty())
                        {
                            editList.set(position, dateEt.getHint().toString());
                        }
                        else {editList.set(position, dateEt.getText().toString());}
                        updateDB(editList,"date");

                        editList.clear();
                        editList=getDB(editList,length,"remarks");
                        if(remarksEt.getText().toString().isEmpty())
                        {
                            editList.set(position, remarksEt.getHint().toString());
                        }
                        else {editList.set(position, remarksEt.getText().toString());}
                        updateDB(editList,"remarks");


                        editor.putInt("Length",editList.size());
                        editor.commit();
                        /*itemNameEt.setVisibility(View.GONE);
                        itemNameTv.setVisibility(View.VISIBLE);
                        priceEt.setVisibility(View.GONE);
                        priceTv.setVisibility(View.VISIBLE);
                        dateEt.setVisibility(View.GONE);
                        dateTv.setVisibility(View.VISIBLE);
                        remarksEt.setVisibility(View.GONE);
                        remarksTv.setVisibility(View.VISIBLE);
                        saveB.setVisibility(View.GONE);
                        editB.setVisibility(View.VISIBLE);*/
                        ExpensesActivity.activity.recreate();
                    }
                });
                //get the data base to array list
                //use position to edit the entry through sending it to edit screen
                //edit array list by using position
                //update the database

            }
        });

        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //get the data base to array list
                    //use position to delete the entry in array list
                    //update the database
                ArrayList<String> editList=new ArrayList<>();
                int length=sharedPreferences.getInt("Length",0);

                editList=getDB(editList,length,"item");
                editList.remove(position);
                updateDB(editList,"item");

                editList.clear();
                editList=getDB(editList,length,"price");
                editList.remove(position);
                updateDB(editList,"price");

                editList.clear();
                editList=getDB(editList,length,"date");
                editList.remove(position);
                updateDB(editList,"date");

                editList.clear();
                editList=getDB(editList,length,"remarks");
                editList.remove(position);
                updateDB(editList,"remarks");


                editor.putInt("Length",editList.size());
                editor.commit();
                ExpensesActivity.activity.recreate();

            }
        });
    }

}
