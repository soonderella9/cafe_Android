package com.example.gginiggini.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
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

import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Adapter.Home_PageAdapter;
import com.example.gginiggini.Adapter.NoticeAdapter;
import com.example.gginiggini.Adapter.RecyclerAdapter;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.Item.Item_Notice;
import com.example.gginiggini.R;

import java.util.ArrayList;

public class Notice extends AppCompatActivity {
    private Toolbar toolBar;
    private ListView lvNavList=null;
    private NavAdapter navAdapter;
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

        ArrayList<Item_Notice> items = new ArrayList<>();
        Item_Notice[] item = new Item_Notice[10];
        for(int i =0;i<10;i++){
            item[i] = new Item_Notice();
            item[i].setTitle("오늘 열공국수 개시!");
            item[i].setDate("2016-11-24 16:14");

            items.add(item[i]);
        }
        recyclerView.setAdapter(new NoticeAdapter(getApplicationContext(), items, R.layout.activity_my_favorites));
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