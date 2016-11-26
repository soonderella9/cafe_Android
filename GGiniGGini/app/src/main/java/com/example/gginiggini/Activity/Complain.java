package com.example.gginiggini.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
import com.example.gginiggini.Adapter.Home_PageAdapter;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;

import java.util.ArrayList;

public class Complain extends AppCompatActivity {
    private Toolbar toolBar;
    private ImageView writeImage;
    private ListView lvComplainList=null;
    private ComplainAdapter complainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        toolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("소리함");
        complainAdapter = new ComplainAdapter();
        lvComplainList = (ListView) findViewById(R.id.compalinlist);
        lvComplainList.setAdapter(complainAdapter);
        for (int i = 0; i < 10; i++) {
            complainAdapter.addItem("에어콘좀 틀어주세요!!!!", "2016/11/25 18.25",
                    "이용준", "O");
        }
        lvComplainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                //ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                    Intent intent = new Intent(Complain.this, ComplainRead.class);
                    startActivity(intent);

            }
        }) ;
        writeImage = (ImageView) findViewById(R.id.writeimage);
        writeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Complain.this, ComplainWrite.class);
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
}