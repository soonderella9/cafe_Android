package com.example.gginiggini.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.app.FragmentTransaction;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Adapter.Home_PageAdapter;
import com.example.gginiggini.Class.BackPressCloseHandler;
import com.example.gginiggini.Class.Flag;
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * MainView
 */
public class Home extends AppCompatActivity {
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    private Toolbar toolBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ListView lvNavList=null;
    private NavAdapter navAdapter;
    private ImageView rightArrow;
    private ImageView leftArrow;
    private TextView textDate;
    private String stringDate;
    private int nWeek;
    private int checkDate=0;
    private SearchView searchView;
    private BackPressCloseHandler backPressCloseHandler;
    private Bundle bundle;
    private Calendar cal;
    private String userID;
    private String userName;
    private SendPost userRegisterSP;
    private Home_PageAdapter homePageAdapter;
    public static Flag flag = new Flag();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Obtain reference from xml
        searchView = (SearchView)findViewById(R.id.search_view);
        toolBar= (Toolbar) findViewById(R.id.toolbar);
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //rightArrow = (ImageView) findViewById(R.id.rightarrow);
        //leftArrow = (ImageView) findViewById(R.id.leftarrow);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        textDate = (TextView) findViewById(R.id.text_date);
        lvNavList = (ListView) findViewById(R.id.drawer);

        backPressCloseHandler = new BackPressCloseHandler(this);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        //get userInfo from intent
        userID = bundle.get("USERID").toString();
        userName = bundle.get("USERNAME").toString();
        flag.setUserUID(userID);
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("uid", userID);
            jsonParam.put("nick", userName);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        userRegisterSP = new SendPost(jsonParam, "user/check");

        new Thread() {
            public void run() {
                userRegisterSP.executeClient();
            }
        }.start();
        //show toast about username
        Toast.makeText(Home.this,userName+"님 환영합니다 ^^", Toast.LENGTH_LONG).show();
        //for search and move to search activity
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {//jh
            @Override
            public boolean onQueryTextSubmit(String s) {

                Intent intent = new Intent(Home.this , SearchResult.class);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        setSupportActionBar(toolBar);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("오늘의 메뉴");

        //from this,make tap
        tabLayout.addTab(tabLayout.newTab().setText("상록원"));
        tabLayout.addTab(tabLayout.newTab().setText("기숙사식당"));
        tabLayout.addTab(tabLayout.newTab().setText("그루터기"));
        tabLayout.addTab(tabLayout.newTab().setText("교직원"));
        //set equal tab interval
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //for get today's date
        cal = Calendar.getInstance();
        nWeek = cal.get(Calendar.DAY_OF_WEEK);
        stringDate=doDayOfWeek(nWeek);
        textDate.setText(getDateString()+" "+stringDate);

        //setting today's date
        doDayOfWeek(nWeek);
        /**
         * method for move date from today to tomorrow
         */

        //creating Home_PageAdapter adapter
        homePageAdapter = new Home_PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(homePageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //set TabSelectedListner
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
        lvNavList.setAdapter(navAdapter);
        //add 1st item to navigation drawer
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.trophy),
                "Top 10") ;
        //add 2nd item to navigation drawer
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.complaint),
                "소리함") ;
        //add 3rd item to navigation drawer
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.amplifier),
                "공지사항") ;
        //add 4th item to navigation drawer
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cafeteria),
                "상록원주간메뉴") ;
        //add 5th item to navigation drawer
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cafeteria),
                "기숙사식당주간메뉴") ;
        //add 6th item to navigation drawer
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cafeteria),
                "그루터기주간메뉴") ;
        //add 7th item to navigation drawer
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cafeteria),
                "교직원식당주간메뉴") ;
        //add 8th item to navigation drawer
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.logout),
                "로그 아웃") ;
        //define click event handler on listview
        lvNavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //define each item click event
                if(position==0) {
                    Intent intent = new Intent(Home.this, Ranking.class);
                    intent.putExtra("USERID", userID);
                    intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                }else if(position ==1){
                    Intent intent = new Intent(Home.this, Complain.class);
                    intent.putExtra("USERID", userID);
                    intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                }else if(position ==2){
                    Intent intent = new Intent(Home.this, Notice.class);
                    intent.putExtra("USERID", userID);
                    intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                }else if(position == 3){
                    flag.setCafeFlag("상록원");
                    Intent intent = new Intent(Home.this, Weekly_Sangrok.class);
                    intent.putExtra("CAFENAME", "상록원");
                    intent.putExtra("USERID", userID);
                    intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                }else if(position == 4){
                    flag.setCafeFlag("기숙사식당");
                    Intent intent = new Intent(Home.this, Weekly_Domi.class);
                    intent.putExtra("CAFENAME", "기숙사식당");
                    intent.putExtra("USERID", userID);
                    intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                }else if(position == 5){
                    flag.setCafeFlag("그루터기");
                    Intent intent = new Intent(Home.this, Weekly_Gru.class);
                    intent.putExtra("CAFENAME", "그루터기");
                    intent.putExtra("USERID", userID);
                    intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                }else if(position == 6){
                    flag.setCafeFlag("교직원식당");
                    Intent intent = new Intent(Home.this, Weekly_Staff.class);
                    intent.putExtra("CAFENAME", "교직원식당");
                    intent.putExtra("USERID", userID);
                    intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                }else if(position == 7){
                    Intent intent = new Intent(Home.this, Login.class);
                    //intent.putExtra("USERID", userID);
                    //intent.putExtra("USERNAME", userName);
                    startActivity(intent);
                    finish();
                }
            }
        }) ;

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

    /**
     *method for get tday's date with color
     */
    private String doDayOfWeek(int date ) {
        String strWeek=null;
        if (date == 1) {
            strWeek="Sun";
        } else if (date == 2) {
            strWeek="Mon";
        } else if (date == 3) {
            strWeek="Tue";
        } else if (date == 4) {
            strWeek="Wed";
        } else if (date == 5) {
            strWeek="Thu";
        } else if (date == 6) {
            strWeek="Fri";
        } else if (date == 7) {
            strWeek="Sat";
        }
        return strWeek;
    }
    public String getDateString()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String str_date = df.format(new Date());
        return str_date;
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
        homePageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}