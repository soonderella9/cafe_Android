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

public class Home extends AppCompatActivity {
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    private Toolbar toolBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListView lvNavList=null;
    private NavAdapter navAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolBar= (Toolbar) findViewById(R.id.toolbar);
        //list = (ImageView) findViewById(R.id.list);
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolBar);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("상록원"));
        tabLayout.addTab(tabLayout.newTab().setText("긱식"));
        tabLayout.addTab(tabLayout.newTab().setText("그루터기"));
        tabLayout.addTab(tabLayout.newTab().setText("교직원"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        //Creating Home_PageAdapter adapter
        Home_PageAdapter homePageAdapter = new Home_PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(homePageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Set TabSelectedListner
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        navAdapter = new NavAdapter();
        lvNavList = (ListView) findViewById(R.id.drawer);
        lvNavList.setAdapter(navAdapter);
        // 첫 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.trophy),
                "인기 메뉴") ;
        // 두 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.complaint),
                "소리함") ;
        // 세 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.amplifier),
                "공지사항") ;
        // 네 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.heart_fill),
                "내 좋아요") ;
        // 다섯 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.logout),
                "로그 아웃") ;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
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