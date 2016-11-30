package com.example.gginiggini.Activity;

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
import android.widget.ListView;

import com.example.gginiggini.Adapter.MyFavoritesAdapter;
import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Adapter.NoticeAdapter;
import com.example.gginiggini.Adapter.RecyclerAdapter;
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.Item.Item_Notice;
import com.example.gginiggini.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyFavorites extends AppCompatActivity {
    private Toolbar toolBar;
    private Bundle bundle;
    private String userName;
    private String userID;
    private SendPost myFavoritesListSP;
    private Handler handler;
    private MyFavoritesAdapter myFavoritesAdapter;
    private ArrayList<Item_Menu> items_MyFavoritesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        toolBar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("좋아요");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

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

        myFavoritesListSP = new SendPost(jsonParam, "notice/listApp");

        final String SPresult[] = new String[1];

        new Thread() {
            public void run() {
                SPresult[0] = myFavoritesListSP.executeClient();
                handler.post(new Runnable() {
                    public void run() {
                        doJSONParser(SPresult);
                        myFavoritesAdapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
        //my favorites info
        ArrayList<Item_Menu> items = new ArrayList<>();
        Item_Menu[] item = new Item_Menu[10];
        for(int i =0;i<10;i++){
            item[i] = new Item_Menu();
            item[i].setmName("전주비빔밥");
            item[i].setmPrice("3900원");
            item[i].setlCount(10);
            item[i].setScore(8);
            items.add(item[i]);
        }
        recyclerView.setAdapter(new MyFavoritesAdapter(userID, userName, getApplicationContext(), items, R.layout.activity_my_favorites));
//
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
                    Item_Menu[] itemarray = new Item_Menu[JArray.length()];
                    for (int i = 0; i < JArray.length(); i++) {
                        JSONObject jk = JArray.getJSONObject(i);  // JSONObject 추출
                        itemarray[i] = new Item_Menu();
                        itemarray[i].setmName(jk.getString("menuName"));
                        itemarray[i].setmPrice(jk.getString("price"));
                        itemarray[i].setlCount(jk.getInt("likeNum"));
                        itemarray[i].setScore(jk.getInt("point"));
                        itemarray[i].setmDetail(jk.getString("detail"));
                        items_MyFavoritesList.add(itemarray[i]);
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