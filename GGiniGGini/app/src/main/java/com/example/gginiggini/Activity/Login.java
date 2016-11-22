package com.example.gginiggini.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.gginiggini.R;

public class Login extends AppCompatActivity {
    private EditText id;
    private EditText pwd;
    private Button login;
    private Button enroll;
    private CheckBox autoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id =(EditText) findViewById(R.id.id_info);
        pwd= (EditText) findViewById(R.id.pwd_info);
        login =(Button) findViewById(R.id.btn_login);
        enroll = (Button) findViewById(R.id.btn_enroll);
        autoLogin = (CheckBox)findViewById(R.id.cB_autologin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Login.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
