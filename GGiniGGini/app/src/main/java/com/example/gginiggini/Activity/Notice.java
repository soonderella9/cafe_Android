package com.example.gginiggini.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Adapter.NoticeAdapter;
import com.example.gginiggini.Adapter.RecyclerAdapter;
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.Item.Item_Complain;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.Item.Item_Notice;
import com.example.gginiggini.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Notice extends AppCompatActivity {
    private Toolbar toolBar;
    private NoticeAdapter noticeAdapter;
    private ArrayList<Item_Notice> items_noticeList;
    private Bundle bundle;
    private String userName;
    private String userID;
    private SendPost noticeListSP;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        toolBar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("공지사항");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items_noticeList = new ArrayList<Item_Notice>();
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        userID = bundle.get("USERID").toString();
        userName = bundle.get("USERNAME").toString();
        handler = new Handler();
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("null", "null");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        noticeListSP = new SendPost(jsonParam, "notice/listApp");

        final String SPresult[] = new String[1];

        new Thread() {
            public void run() {
                SPresult[0] = noticeListSP.executeClient();
                handler.post(new Runnable() {
                    public void run() {
                        doJSONParser(SPresult);
                        //noticeAdapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
        recyclerView.setAdapter(new NoticeAdapter(getApplicationContext(), items_noticeList, R.layout.activity_notice));

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
                    JSONArray JArray = new JSONArray(result);   // JSONArray 생성
                    Item_Notice[] itemarray = new Item_Notice[JArray.length()];
                    for (int i = 0; i < JArray.length(); i++) {
                        JSONObject jk = JArray.getJSONObject(i);  // JSONObject 추출
                        itemarray[i] = new Item_Notice();
                        itemarray[i].setTitle(jk.getString("title").toString());
                        itemarray[i].setContent(jk.getString("contents").toString());
                        itemarray[i].setDate(jk.getString("regDate").toString());
                        items_noticeList.add(itemarray[i]);
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