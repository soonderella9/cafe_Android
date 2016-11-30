package com.example.gginiggini.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.R;

import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.GONE;

/**
 * complain read page
 */
public class ComplainRead extends AppCompatActivity {
    private Toolbar toolBar;
    private TextView complainTitle;
    private TextView complainRegdate;
    private TextView complainContent;
    private TextView complainReply;
    private TextView complainReplyDate;
    private Button complainDelete;
    private Bundle bundle;
    private String userName;
    private String userID;
    private int complainNum;
    private SendPost complainReadSP;
    private SendPost complainDeleteSP;
    private Handler handler;
    private RelativeLayout relativeLayout5;
    private RelativeLayout relativeLayout6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_read);

        toolBar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("소리함 내용");

        handler = new Handler();
        complainTitle = (TextView) findViewById(R.id.complain_titleInfo);
        complainRegdate = (TextView) findViewById(R.id.complain_regdateInfo);
        complainContent = (TextView) findViewById(R.id.complain_contentInfo);
        complainReply = (TextView) findViewById(R.id.complain_replyInfo);
        complainReplyDate = (TextView) findViewById(R.id.complain_replydateInfo);
        complainDelete = (Button) findViewById(R.id.complaindelete);
        relativeLayout5 = (RelativeLayout) findViewById(R.id.rela5);
        relativeLayout6 = (RelativeLayout) findViewById(R.id.rela6);
        bundle = new Bundle();
        bundle= getIntent().getExtras();
        userID = bundle.get("USERID").toString();
        complainNum = Integer.parseInt(bundle.get("COMPLAINNUM").toString());
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("uid", userID);
            jsonParam.put("complainNum", complainNum);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        complainReadSP = new SendPost(jsonParam, "complain/readOneApp");
        final String SPresult[] = new String[1];

        new Thread() {
            public void run() {
                SPresult[0] = complainReadSP.executeClient();
                handler.post(new Runnable() {
                    public void run() {
                        doJSONParser(SPresult);
                    }
                });
            }
        }.start();
        complainDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("uid", userID);
                    jsonParam.put("complainNum", complainNum);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                complainDeleteSP = new SendPost(jsonParam, "complain/deleteApp");
                final String SPresult[] = new String[1];

                new Thread() {
                    public void run() {
                        complainDeleteSP.executeClient();
                    }
                }.start();
                finish();
            }
        });
    }

    /**
     * for back button
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    void doJSONParser(String[] SP) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();

        String return_test = SP[0].toString();
        try {
            JSONObject jObject = new JSONObject(return_test);
            int code = jObject.getInt("code");
            String message = jObject.getString("message");
            String result = jObject.getString("result");

            sb1.append("code:" + code + "\n");
            sb2.append("message:" + message + "\n");
            sb3.append("result:" + result + "\n");
            System.out.println("" + sb1.toString());
            System.out.println("" + sb2.toString());
            System.out.println("" + sb3.toString());


            if (code == 200) { //
                try {
                    JSONObject jk = new JSONObject(result);  // JSONObject 추출

                    complainTitle.setText(jk.getString("title").toString());
                    complainRegdate.setText(jk.getString("regDate").toString());
                    complainContent.setText(jk.getString("content").toString());
                    if(jk.getString("reply").toString().equals("")) {
                        relativeLayout5.setVisibility(GONE);
                        relativeLayout6.setVisibility(GONE);
                    }
                    else{
                        complainReply.setText(jk.getString("reply").toString());
                        complainReplyDate.setText(jk.getString("replyDate").toString());
                    }
                    if(userID.equals(jk.getString("uid").toString())){
                        complainDelete.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
