package com.example.gginiggini.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ComplainWrite extends AppCompatActivity {
    private Toolbar toolBar;
    private ImageView sendImage;
    private SendPost mySendPost;
    private EditText complainTitle;
    private EditText complainContent;
    private Bundle bundle;
    private String userID;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_write);

        toolBar= (Toolbar) findViewById(R.id.toolbar);
        complainTitle = (EditText) findViewById(R.id.complain_title);
        complainContent = (EditText) findViewById(R.id.complain_content);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("소리함 작성");
        sendImage = (ImageView) findViewById(R.id.writeimage);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        //get userInfo from intent
        userID = bundle.get("USERID").toString();
        userName = bundle.get("USERNAME").toString();
        /**
         * send complain content to server
         */
        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("title", complainTitle.getText().toString());
                    jsonParam.put("content", complainContent.getText().toString());
                    jsonParam.put("uid", userID);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                mySendPost = new SendPost(jsonParam, "complain/registerApp");

                final String SPresult[] = new String[1];

                new Thread() {
                    public void run() {
                        mySendPost.executeClient();
                    }
                }.start();
                finish();
            }
        });
    }


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
}
