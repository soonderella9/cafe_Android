//package com.frenzy.ebook.dgbook2.Activity;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
//import android.text.method.PasswordTransformationMethod;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.SpinnerAdapter;
//import android.widget.Toast;
//
//import com.frenzy.ebook.dgbook2.Crawler.Crawler;
//import com.frenzy.ebook.dgbook2.Crawler.DonggukCrawler;
//import com.frenzy.ebook.dgbook2.R;
//
//import java.util.ArrayList;
//
///**
// * Created by SeungHyeonPark on 2016. 11. 11..
// * Modified by YoungHoonKim on  2016. 11. 12..
// * Modified by SeungHyeonPark on 2016.11. 13..
// */
//
//
//public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
//
//    private Crawler crawler;
//    private String geocode = "서울";
//
//    private SharedPreferences pref;
//    private SharedPreferences.Editor prefEditor;
//
//    private Spinner _spinner;
//    private Button _login;
//    private EditText _id, _pw;
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.login:
//                signIn(_id.getText().toString(), _pw.getText().toString());
//                break;
//            default:
//                break;
//        }
//    }
//
//    public void showToast(String message) {
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//    }
//
//    public void signIn(String id, String pw) {
//        final ProgressDialog pdial = new ProgressDialog(this);
//        pdial.setMessage("로그인하는 중...");
//        pdial.setCancelable(false);
//        pdial.show();
//
//        _login.setEnabled(false);
//        crawler = new DonggukCrawler(id, pw);
//        geocode = Crawler.GEO_SEOUL;
//        if (id.length() != Crawler.LENGTH_DONGGUK) {
//            showToast(Crawler.LENGTH_DONGGUK + "자리 학번을 사용합니다");
//            _login.setEnabled(true);
//            pdial.dismiss();
//            return;
//        }
//
//        if (pw.length() < 1) {
//            showToast("패스워드를 입력하세요");
//            _login.setEnabled(true);
//            pdial.dismiss();
//            return;
//        }
//
//        crawler.verify(new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Log.e("Handle", msg.getData().toString());
//                if (msg.getData().getBoolean("result")) {
//                    prefEditor.putBoolean("auto", true);
//                    prefEditor.putString("geocode", geocode);
//                    // prefEditor.putInt("selection", _spinner.getSelectedItemPosition());
//                    prefEditor.putString("id", msg.getData().getString("id"));
//                    prefEditor.putString("pw", msg.getData().getString("pw"));
//                    prefEditor.putString("name", msg.getData().getString("name"));
//                    //prefEditor.putInt("ucode", spinList.get(_spinner.getSelectedItemPosition()).ucode);
//                    prefEditor.commit();
//                    Intent i = new Intent(LoginActivity.this, MainmenuActivity.class);
//                    startActivity(i);
//                    pdial.dismiss();
//                    _login.setEnabled(true);
//                    finish();
//                } else {
//                    prefEditor.putBoolean("auto", false);
//                    prefEditor.putString("id", "#");
//                    prefEditor.putString("pw", "#");
//                    prefEditor.putString("name", "#");
//                    prefEditor.commit();
//                    showToast("로그인에 실패하였습니다");
//                    _login.setEnabled(true);
//                    pdial.dismiss();
//                    return;
//                }
//            }
//        });
//
//    }
//
//    public void initView() {
//        _login = (Button) findViewById(R.id.login);
//        _id = (EditText) findViewById(R.id.user_id2);
//        _pw = (EditText) findViewById(R.id.passwd2);
//        _pw.setTransformationMethod(new PasswordTransfromMethod());
//        _login.setOnClickListener(this);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        pref = getSharedPreferences("Univtable", MODE_PRIVATE);
//        prefEditor = pref.edit();
//
//        initView();
//
//        if (pref.getBoolean("auto", false)) {
//            signIn(pref.getString("id", "#"), pref.getString("pw", "#"));
//        }
//    }
//
//    public class PasswordTransfromMethod extends PasswordTransformationMethod {
//        @Override
//        public CharSequence getTransformation(CharSequence source, View view) {
//            return new PasswordCharSequence(source);
//        }
//
//        private class PasswordCharSequence implements CharSequence {
//            private CharSequence mSource;
//
//            public PasswordCharSequence(CharSequence source) {
//                mSource = source;
//            }
//
//            public char charAt(int index) {
//                return '●';
//            }
//
//            public int length() {
//                return mSource.length();
//            }
//
//            public CharSequence subSequence(int start, int end) {
//                return mSource.subSequence(start, end); // Return default
//            }
//        }
//    }
//}
//
