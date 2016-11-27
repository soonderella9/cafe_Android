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
import java.util.List;

public class MyFavorites extends AppCompatActivity {
    private Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        toolBar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("좋아요");
        //
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Item_Menu> items = new ArrayList<>();
        Item_Menu[] item = new Item_Menu[10];
        for(int i =0;i<10;i++){
            item[i] = new Item_Menu();
            item[i].setmName("전주비빔밥");
            item[i].setmPrice("3900원");
            item[i].setlCount("10");
            item[i].setScore("8.7");
            items.add(item[i]);
        }
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_my_favorites));
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
}