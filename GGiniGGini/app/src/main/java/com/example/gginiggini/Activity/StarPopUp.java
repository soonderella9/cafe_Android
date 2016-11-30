package com.example.gginiggini.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.R;

import org.json.JSONException;
import org.json.JSONObject;

public class StarPopUp extends Activity{
    private Button scoreRegister;
    private Button cancel;
    private RatingBar ratingBar;
    private TextView ratingScore;
    private SendPost scoreRegisterSP;
    private Bundle bundle;
    private String userName;
    private String userID;
    private String cafeName;
    private String menuName;
    private String menuDetail;
    private Float score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_star_pop_up);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
        ratingScore = (TextView) findViewById(R.id.ratingScore);
        scoreRegister = (Button) findViewById(R.id.button1);
        cancel = (Button) findViewById(R.id.button2);
        bundle = new Bundle();
        bundle = getIntent().getExtras();

        //get userInfo from intent
        userID = bundle.get("USERID").toString();
        userName = bundle.get("USERNAME").toString();
        cafeName = bundle.get("CAFENAME").toString();
        menuName = bundle.get("MENUNAME").toString();
        menuDetail = bundle.get("MENUDETAIL").toString();
        ratingScore.setText(3+"");
        //when click score enrollment button
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratingScore.setText(""+rating+"");
            }
        });
        scoreRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score=Float.parseFloat(ratingScore.getText().toString());
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("cafeName", cafeName);
                    jsonParam.put("menuName", menuName);
                    jsonParam.put("detailName", menuDetail);
                    jsonParam.put("score", score);
                    jsonParam.put("uid", userID);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                scoreRegisterSP = new SendPost(jsonParam, "score/registerApp");

                new Thread() {
                    public void run() {
                        scoreRegisterSP.executeClient();
                    }
                }.start();
                finish();
                Toast.makeText(StarPopUp.this , "별점이 반영되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
        //when click cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(StarPopUp.this , "취소 하셨습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
