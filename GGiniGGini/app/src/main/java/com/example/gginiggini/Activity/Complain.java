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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.gginiggini.Adapter.ComplainAdapter;
import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.Item.Item_Complain;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/**
 * complainlist page
 */
public class Complain extends AppCompatActivity {
    private Toolbar toolBar;
    private ImageView writeImage;
    private ListView lvComplainList=null;
    private ComplainAdapter complainAdapter;
    private ArrayList<Item_Complain> items_complainList;
    private Bundle bundle;
    private String userID;
    private String userName;
    private SendPost complainListSP;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        toolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("소리함");
        //complainAdapter = new ComplainAdapter();
        lvComplainList = (ListView) findViewById(R.id.compalinlist);
        items_complainList = new ArrayList<Item_Complain>();
        complainAdapter = new ComplainAdapter(this, items_complainList);
        lvComplainList.setAdapter(complainAdapter);
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        //get userInfo from intent
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

        complainListSP = new SendPost(jsonParam, "complain/listApp");

        final String SPresult[] = new String[1];

        new Thread() {
            public void run() {
                SPresult[0] = complainListSP.executeClient();
                handler.post(new Runnable() {
                    public void run() {
                        doJSONParser(SPresult);
                        complainAdapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
        /**
         * for complain read, link to complain readepage
         */
        lvComplainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent complainRead = new Intent(Complain.this, ComplainRead.class);
                complainRead.putExtra("USERID", userID);
                complainRead.putExtra("COMPLAINNUM", items_complainList.get(i).getComplainNum());
                startActivity(complainRead);
            }
        });
        /**
         * for complain write, link to complain writepage
         */
        writeImage = (ImageView) findViewById(R.id.writeimage);
        writeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Complain.this, ComplainWrite.class);
                intent.putExtra("USERID", userID);
                intent.putExtra("USERNAME", userName);
                startActivity(intent);
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
                    Item_Complain[] itemarray = new Item_Complain[JArray.length()];
                    for (int i = 0; i < JArray.length(); i++) {
                        JSONObject jk = JArray.getJSONObject(i);  // JSONObject 추출
                        itemarray[i] = new Item_Complain();
                        itemarray[i].setComplainNum(Integer.parseInt(jk.getString("complainNum").toString()));
                        itemarray[i].setTitle(jk.getString("title").toString());
                        itemarray[i].setDate(jk.getString("regDate").toString());
                        itemarray[i].setUid(jk.getString("uid").toString());
                        if(jk.getBoolean("isReply")) {
                            itemarray[i].setIsReply("O");
                        }
                        else if(!jk.getBoolean("isReply")){
                            itemarray[i].setIsReply("X");
                        }
                        items_complainList.add(itemarray[i]);
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