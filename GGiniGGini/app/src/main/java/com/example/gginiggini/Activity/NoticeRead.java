package com.example.gginiggini.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gginiggini.R;

public class NoticeRead extends AppCompatActivity {
    private Toolbar toolBar;
    private TextView noticeTitle;
    private TextView noticeContent;
    private String userName;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_read);
        toolBar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("공지사항 내용");

        noticeTitle = (TextView) findViewById(R.id.notice_titleInfo);
        noticeContent =(TextView) findViewById(R.id.notice_contentInfo);

        noticeTitle.setText("열공국수 개시!!!");
        noticeContent.setText("11 / 26일 저녁 6~8시 상록원에서 열공국수를 개시합니다.");
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
