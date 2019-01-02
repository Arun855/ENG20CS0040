package com.mysterium.a1pra.helpinghand.expenses;
/*
 * Author: Prabhutva Agrawal
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mysterium.a1pra.helpinghand.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NewExpensesActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView newExpenseTv;
    EditText addItem,addPrice,addDate,addRemarks;
    Button addExpenseB,clearExpenseB;
    String newItem, newPrice, newDate, newRemarks;
    LinearLayout linearLayout;

    /*This is function which uses the SharedPreferences
     * to get database from the sharedpreferences and run
     * a loop to store data in the arraylist
     * category is used as a key so that we dont need to
     * create multiple functions.*/
    public ArrayList<String> getDB(ArrayList<String> arrayList, int length, String category)
    {
        sharedPreferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
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
        sharedPreferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] array=new String[arrayList.size()];
        arrayList.toArray(array);
        for(int i=0;i<array.length;i++)
        {
            String key=category+i;
            editor.putString(key, array[i]);
            editor.commit();
        }
        editor.putInt("Length",array.length);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expenses);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        linearLayout=findViewById(R.id.new_exp_ll);
        int darkTheme=sharedPreferences.getInt("darkTheme",0);
        if(darkTheme==1)
        {//change theme to dark.
            linearLayout.setBackgroundResource(R.drawable.gradientdark);
        }
        else if(darkTheme==2){
            Calendar c=Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH");
            String time=df.format(c.getTime());
            int check=Integer.parseInt(time);
            if(5<=check&&check<11){
                linearLayout.setBackgroundResource(R.drawable.gradientmorning);
            }
            else if(11<=check&&check<16){
                linearLayout.setBackgroundResource(R.drawable.gradientnoon);
            }
            else if(16<=check&&check<19){
                linearLayout.setBackgroundResource(R.drawable.gradientevening);
            }
            else{
                linearLayout.setBackgroundResource(R.drawable.gradientnight);
            }


        }
        else{
            //set theme bright.
            linearLayout.setBackgroundResource(R.drawable.gradient);
        }
        newExpenseTv=findViewById(R.id.new_expense_tv);
        addItem=findViewById(R.id.expense_item_name_et);
        addPrice=findViewById(R.id.expense_price_et);
        addDate=findViewById(R.id.expense_date_et);
        addRemarks=findViewById(R.id.expense_remarks_et);
        addExpenseB=findViewById(R.id.save_expense_b);
        clearExpenseB=findViewById(R.id.clear_expense_b);

        addExpenseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(addItem.getText().toString().isEmpty()||addPrice.getText().toString().isEmpty())   {
                Toast.makeText(NewExpensesActivity.this, "Please enter valid data.", Toast.LENGTH_SHORT).show();
            }
            else{
                newItem=addItem.getText().toString();
                newPrice=addPrice.getText().toString();
                if(addDate.getText().toString().isEmpty()){
                    Calendar c=Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss, EEE\ndd-MM-yyyy");
                    newDate=df.format(c.getTime());
                }
                else{
                newDate=addDate.getText().toString();}
                newRemarks=addRemarks.getText().toString();

                try{
                double price=Double.parseDouble(newPrice);
                ArrayList<String> tempItemList=new ArrayList();
                ArrayList<String> tempPriceList=new ArrayList();
                ArrayList<String> tempDateList=new ArrayList();
                ArrayList<String> tempRemarksList=new ArrayList();

                int length=sharedPreferences.getInt("Length",0);
                tempItemList=getDB(tempItemList, length, "item");
                tempPriceList=getDB(tempPriceList, length, "price");
                tempDateList=getDB(tempDateList, length, "date");
                tempRemarksList=getDB(tempRemarksList, length, "remarks");

                tempItemList.add(newItem);
                tempPriceList.add(newPrice);
                tempDateList.add(newDate);
                tempRemarksList.add(newRemarks);

                updateDB(tempItemList,"item");
                updateDB(tempPriceList,"price");
                updateDB(tempDateList,"date");
                updateDB(tempRemarksList,"remarks");

                Intent intent=new Intent(NewExpensesActivity.this, com.mysterium.a1pra.helpinghand.expenses.ExpensesActivity.class);
                startActivity(intent);
                finish();}
                catch (Exception e)
                {
                    Toast.makeText(NewExpensesActivity.this, "Please enter the amount in numbers.", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

        clearExpenseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem.setText("");
                addPrice.setText("");
                addDate.setText("");
                addRemarks.setText("");
            }
        });
    }
}
