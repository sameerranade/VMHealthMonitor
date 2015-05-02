package com.cmpe283.vmhealthmonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cmpe283.vmhealthmonitor.DAO.DBHelper;
import com.cmpe283.vmhealthmonitor.activity.HomeActivity;
import com.cmpe283.vmhealthmonitor.models.Users;


public class LauncherActivity extends Activity {
    public static Users APP_USER;
    DBHelper db = new DBHelper(LauncherActivity.this);
    Users user1 = new Users("user", "user", "defaultUser");
    Users user2 = new Users("admin", "admin", "admin");
    EditText userName;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        userName = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String userNameTxt = userName.getText().toString();
        String passwordTxt = userName.getText().toString();

        if(userNameTxt.equals(user1.getUserName()) || userNameTxt.equals(user2.getUserName())){
            if(passwordTxt.equals(user1.getPassword()) || passwordTxt.equals(user2.getPassword())){
                APP_USER = userNameTxt.equals(user1.getUserName()) ? user1 : user2;
                Intent intent = new Intent(LauncherActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lancher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
