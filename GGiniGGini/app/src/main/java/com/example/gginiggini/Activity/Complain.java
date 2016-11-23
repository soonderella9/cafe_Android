package com.example.gginiggini.Activity;

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
import android.widget.ListView;

import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Adapter.Home_PageAdapter;
import com.example.gginiggini.R;

public class Complain extends AppCompatActivity {
    private Toolbar toolBar;
    //private TabLayout tabLayout;
    //private ViewPager viewPager;
    private ListView lvNavList=null;
    private NavAdapter navAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        toolBar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("소리함");

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