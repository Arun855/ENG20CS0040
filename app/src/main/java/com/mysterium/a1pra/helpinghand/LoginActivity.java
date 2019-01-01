package com.mysterium.a1pra.helpinghand;
/*
 * Author: Prabhutva Agrawal
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEt, passwordEt, nameEt, newPasswordEt;
    Button loginButton, clearButton;
    TextView loginTv;
    String username, password, name, usernameCheck, passwordCheck;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt=findViewById(R.id.user_et);
        passwordEt=findViewById(R.id.pass_et);
        newPasswordEt=findViewById(R.id.newpass_et);
        nameEt=findViewById(R.id.name_et);
        loginTv=findViewById(R.id.login_tv);
        loginButton=findViewById(R.id.login_b);
        clearButton=findViewById(R.id.clear_b);
        newPasswordEt.setVisibility(View.GONE);
        sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        username = sharedPreferences.getString("username", null);
        password = sharedPreferences.getString("password", null);
        usernameCheck = sharedPreferences.getString("checkuser", null);
        passwordCheck = sharedPreferences.getString("checkpassword", null);

        usernameEt.setText(username);
        passwordEt.setText(password);

        if(sharedPreferences.getString("checkuser", null) == null || sharedPreferences.getString("checkpass", null) == null)
        {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //change

                    usernameCheck = usernameEt.getText().toString();
                    passwordCheck = passwordEt.getText().toString();

                    username=usernameCheck;
                    password=passwordCheck;
                    name=nameEt.getText().toString();

                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);


                        if(name.isEmpty()||username.isEmpty()||password.isEmpty())
                        {
                            Toast.makeText(LoginActivity.this, "Please input valid data!!", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {

                            intent.putExtra("name", name);
                            editor.putString("name", name);
                            editor.commit();
                            intent.putExtra("username", username);
                            intent.putExtra("password", password);
                            intent.putExtra("usernamecheck", usernameCheck);
                            intent.putExtra("passwordcheck", passwordCheck);
                            startActivity(intent);
                            finish();

                        }



                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putString("checkuser", usernameCheck);
                    editor.putString("checkpass", passwordCheck);
                    editor.commit();






                }
            });
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usernameEt.setText("");
                    passwordEt.setText("");
                    nameEt.setText("");
                }
            });

        }
        else if(sharedPreferences.getString("username", null) == null && sharedPreferences.getString("password", null) == null)//when the data is locked.
        {

            nameEt.setVisibility(View.GONE);
            loginTv.setText("Welcome, Please unlock the Locker.");
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    username=usernameEt.getText().toString();
                    password=passwordEt.getText().toString();

                    usernameCheck = sharedPreferences.getString("checkuser", null);
                    passwordCheck = sharedPreferences.getString("checkpass",null);

                    if(username.isEmpty()||password.isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please input valid data!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (username.equals(usernameCheck) && password.equals(passwordCheck)) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                            name = sharedPreferences.getString("name", null);
                            intent.putExtra("username1", username);
                            intent.putExtra("password1", password);
                            intent.putExtra("name", name);
                            intent.putExtra("usernamecheck", usernameCheck);
                            intent.putExtra("passwordcheck", passwordCheck);
                            startActivity(intent);
                            finish();
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.commit();

                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Privacy Invader go attack apps of other groups :P", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usernameEt.setText("");
                    passwordEt.setText("");
                    nameEt.setText("");
                }
            });

        }
        else if(sharedPreferences.getString("username", null) != null && sharedPreferences.getString("password", null) == null)
        {
            //Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            username = sharedPreferences.getString("username", null);
            password = sharedPreferences.getString("password", null);
            usernameCheck = sharedPreferences.getString("checkuser", null);
            passwordCheck = sharedPreferences.getString("checkpass",null);
            name = sharedPreferences.getString("name", null);

            loginTv.setText("Edit the Locker details.");
            usernameEt.setText(username);
            nameEt.setText(name);
            passwordEt.setHint("Confirm old Password");
            passwordEt.setText("");
            newPasswordEt.setVisibility(View.VISIBLE);
            loginButton.setText("Save");
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(passwordEt.getText().toString().equals(passwordCheck)) {

                        if(newPasswordEt.getText().toString().equals(passwordCheck))
                        {
                            Toast.makeText(LoginActivity.this, "New and old passwords cannot be same.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            usernameCheck = usernameEt.getText().toString();
                            passwordCheck = newPasswordEt.getText().toString();

                            username = usernameCheck;
                            password = passwordCheck;
                            name = nameEt.getText().toString();

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);


                            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                                Toast.makeText(LoginActivity.this, "Please input valid data!!", Toast.LENGTH_SHORT).show();
                            } else {

                                intent.putExtra("name", name);
                                editor.putString("name", name);
                                editor.commit();
                                intent.putExtra("username", username);
                                intent.putExtra("password", password);
                                intent.putExtra("usernamecheck", usernameCheck);
                                intent.putExtra("passwordcheck", passwordCheck);
                                startActivity(intent);
                                finish();

                            }


                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.putString("checkuser", usernameCheck);
                            editor.putString("checkpass", passwordCheck);
                            editor.commit();
                        }
                    }
                    else if(!passwordEt.getText().toString().equals(passwordCheck)){
                        Toast.makeText(LoginActivity.this, "You have forgotten your password!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Please input complete data!!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usernameEt.setText("");
                    passwordEt.setText("");
                    nameEt.setText("");
                    newPasswordEt.setText("");
                }
            });
        }
        else
        {
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            username = sharedPreferences.getString("username", null);
            password = sharedPreferences.getString("password", null);
            usernameCheck = sharedPreferences.getString("checkuser", null);
            passwordCheck = sharedPreferences.getString("checkpass",null);
            name = sharedPreferences.getString("name", null);

            usernameEt.setText(username);
            passwordEt.setText(password);

            intent.putExtra("username1", username);
            intent.putExtra("password1", password);
            intent.putExtra("usernamecheck", usernameCheck);
            intent.putExtra("passwordcheck", passwordCheck);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        }
        //else go to next activity
    }
}
