package com.example.gginiggini.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.R;

public class ComplainRead extends AppCompatActivity {
    private Toolbar toolBar;
    private TextView complainTitle;
    private TextView complainContent;
    private TextView complainReply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_read);

        toolBar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("소리함 내용");

        complainTitle = (TextView) findViewById(R.id.complain_titleInfo);
        complainContent = (TextView) findViewById(R.id.complain_contentInfo);
        complainReply = (TextView) findViewById(R.id.complain_replyInfo);
        complainTitle.setText("에어컨좀 틀어주세요!!!!");
        complainContent.setText("요즘 밥을 먹는데 너무 더워요....");
        complainReply.setText("지금 에어컨이 고장나있어서 빠른시일내에 처리하겠습니다");
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
