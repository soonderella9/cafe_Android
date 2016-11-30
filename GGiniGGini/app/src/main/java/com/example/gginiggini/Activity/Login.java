package com.example.gginiggini.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.example.gginiggini.R;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;

public class Login extends AppCompatActivity {
    private EditText myId;
    private EditText myPwd;
    private Button login;
    private Document document;
    private String userName="";
    private int loginFlag=0;
    private Bundle bundle;
    private CheckBox auto_Login;
    private SharedPreferences setting;
    private SharedPreferences.Editor editor;
    private Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myId = (EditText) findViewById(R.id.idInfo);
        myPwd= (EditText) findViewById(R.id.pwdInfo);
        login = (Button) findViewById(R.id.btn_login);
        auto_Login = (CheckBox) findViewById(R.id.auto_login);
        setting = getSharedPreferences("setting", 0);
        editor= setting.edit();
        /**
         * when clicked
         */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //before login check show progressdialog
                final ProgressDialog pdial = new ProgressDialog(Login.this);
                pdial.setMessage("로그인하는 중...");
                pdial.setCancelable(false);
                pdial.show();
                Thread t = new Thread() {
                    public void run() {
                        boolean result = false;
                        try{
                            //connect to eclass for login with eclass student id, pwd
                            Connection.Response res = Jsoup.connect("https://eclass.dongguk.edu/User.do?cmd=loginUser")
                                    .followRedirects(true)
                                    //send id & pwd
                                    .data("userDTO.userId", myId.getText().toString())
                                    .data("userDTO.password", myPwd.getText().toString())
                                    .method(Connection.Method.POST)
                                    .timeout(15000)
                                    .execute();
                            //get userName from eclass with jsoup parsing
                            document = Jsoup.connect("https://eclass.dongguk.edu/Main.do?cmd=viewEclassMain")
                                    .followRedirects(true)
                                    .cookies(res.cookies())
                                    .timeout(15000)
                                    .get();
                            Element getUser = document.select("SPAN>strong").first();
                            //when login failed
                            if(getUser == null) {
                                loginFlag=0;
                                result = false;
                            //when login success
                            }else{
                                loginFlag=1;
                                result = true;
                                userName = getUser.text().trim();
                            }
                        }catch (IOException e){
                            loginFlag=0;
                            result = false;
                        }
                        //bundle for user info
                        bundle = new Bundle();

                        bundle.putBoolean("result", result);
                        bundle.putString("id", myId.getText().toString());
                        bundle.putString("pw", myPwd.getText().toString());
                        bundle.putString("name", userName);

                        if (loginFlag==1) {
                            //move activity with username info
                            Intent intent = new Intent(Login.this, Home.class);
                            intent.putExtra("USERID",myId.getText().toString());
                            intent.putExtra("USERNAME",userName);
                            startActivity(intent);
                            pdial.dismiss();
                            finish();
                        } else {
                            //show fail toast to notice login fail
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pdial.dismiss();
                                    Toast.makeText(Login.this,"로그인에 실패하였습니다", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            });
                        }
                    }
                };
                t.start();
            }
        });
        //for save id & pwd info
        auto_Login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    String ID = myId.getText().toString();
                    String PW = myPwd.getText().toString();

                    editor.putString("ID", ID);
                    editor.putString("PW", PW);
                    editor.putBoolean("Auto_Login_enabled", true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
            }
        });
        //setting id & pw info when auto_login checked
        if(setting.getBoolean("Auto_Login_enabled", false)){
            myId.setText(setting.getString("ID", ""));
            myPwd.setText(setting.getString("PW", ""));
            auto_Login.setChecked(true);
        }

    }

}