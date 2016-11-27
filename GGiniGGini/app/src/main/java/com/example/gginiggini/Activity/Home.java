package com.example.gginiggini.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Adapter.Home_PageAdapter;
import com.example.gginiggini.Adapter.RecyclerAdapter;
import com.example.gginiggini.Class.BackPressCloseHandler;
import com.example.gginiggini.Fragment.MainTab_Cafe1;
import com.example.gginiggini.Fragment.WeeklyMenu;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;

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
    private int nWeek;
    private int checkDate=0;
    private SearchView searchView;
    private BackPressCloseHandler backPressCloseHandler;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        backPressCloseHandler = new BackPressCloseHandler(this);
        searchView = (SearchView)findViewById(R.id.search_view); //jh
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        String userName = bundle.get("USERNAME").toString();
        Toast.makeText(Home.this,userName+"님 환영합니다 ^^", Toast.LENGTH_LONG).show();
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
        toolBar= (Toolbar) findViewById(R.id.toolbar);
        //list = (ImageView) findViewById(R.id.list);
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolBar);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("학식세끼");

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("상록원"));
        tabLayout.addTab(tabLayout.newTab().setText("기숙사식당"));
        tabLayout.addTab(tabLayout.newTab().setText("그루터기"));
        tabLayout.addTab(tabLayout.newTab().setText("교직원"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        textDate = (TextView) findViewById(R.id.text_date);
        Calendar cal = Calendar.getInstance();
        nWeek = cal.get(Calendar.DAY_OF_WEEK);
        doDayOfWeek(nWeek);
        rightArrow = (ImageView) findViewById(R.id.rightarrow);
        leftArrow = (ImageView) findViewById(R.id.leftarrow);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nWeek!=7) {
                    nWeek = nWeek + 1;
                    checkDate = checkDate +1;
                    //textDate.setText(doDayOfWeek(nWeek));
                    doDayOfWeek(nWeek);
                    FragmentManager fragmentManager = getFragmentManager();

                    //TestFragment frament = new TestFragment();
                    WeeklyMenu newFr = new WeeklyMenu();
                    Bundle bundle = new Bundle();
                    newFr.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.sdfa, newFr);
                    fragmentTransaction.commit();
                    //viewPager.setAdapter();
                    //viewPager.setCurrentItem(newFr);
                    //viewPager.setVisibility(INVISIBLE);
                }else{
                    Toast.makeText(Home.this,"다음주를 기대하세요 ^0^", Toast.LENGTH_LONG).show();
                }
            }
        });
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nWeek!=1) {
                    nWeek = nWeek - 1;
                    checkDate = checkDate -1;
                    //textDate.setText(doDayOfWeek(nWeek));
                    doDayOfWeek(nWeek);
                        FragmentManager fragmentManager = getFragmentManager();

                        //TestFragment frament = new TestFragment();
                        WeeklyMenu newFr = new WeeklyMenu();
                        Bundle bundle = new Bundle();
                        newFr.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.sdfa, newFr);
                        fragmentTransaction.commit();

                    //viewPager.setAdapter(new Home_PageAdapter(fragmentManager);
                    //viewPager.setVisibility(INVISIBLE);
                }else{
                    Toast.makeText(Home.this,"지난주는 잊으세요 ^0^", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Creating Home_PageAdapter adapter
        Home_PageAdapter homePageAdapter = new Home_PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(homePageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Set TabSelectedListner
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
//                switch (tab.getPosition()) {
//                    case 0:
//                        tab.set
//                        tab.setIcon(R.drawable.main_icon_home);
//                        break;
//                    case 1:
//                        tab.setIcon(R.drawable.main_icon_give);
//                        break;
//                    case 2:
//                        tab.setIcon(R.drawable.main_icon_get);
//                        break;
//                    case 3:
//                        tab.setIcon(R.drawable.main_icon_etc);
//                        break;
//                }
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
                "Top 10") ;
        // 두 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.complaint),
                "소리함") ;
        // 세 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.amplifier),
                "공지사항") ;
        // 네 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.heart_fill),
                "좋아요") ;
        // 다섯 번째 아이템 추가.
        navAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.logout),
                "로그 아웃") ;
        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        lvNavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                //ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
                if(position==0) {
                    Intent intent = new Intent(Home.this, Ranking.class);
                    startActivity(intent);
                }else if(position ==1){
                    Intent intent = new Intent(Home.this, Complain.class);
                    startActivity(intent);
                }else if(position ==2){
                    Intent intent = new Intent(Home.this, Notice.class);
                    startActivity(intent);
                }else if(position ==3){
                    Intent intent = new Intent(Home.this, MyFavorites.class);
                    startActivity(intent);
                }else if(position ==4){
                    Intent intent = new Intent(Home.this, Login.class);
                    intent.putExtra("reFlag",1);
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

    private String doDayOfWeek(int date ) {
        //Calendar cal = Calendar.getInstance();
        String strWeek=null;
        if (date == 1) {
            textDate.setText("일");
            textDate.setTextColor(Color.parseColor("#F44336"));
        } else if (date == 2) {
            textDate.setText("월");
            textDate.setTextColor(Color.parseColor("#000000"));
        } else if (date == 3) {
            textDate.setText("화");
            textDate.setTextColor(Color.parseColor("#000000"));
        } else if (date == 4) {
            textDate.setText("수");
            textDate.setTextColor(Color.parseColor("#000000"));
        } else if (date == 5) {
            textDate.setText("목");
            textDate.setTextColor(Color.parseColor("#000000"));
        } else if (date == 6) {
            textDate.setText("금");
            textDate.setTextColor(Color.parseColor("#000000"));
        } else if (date == 7) {
            textDate.setText("토");
            textDate.setTextColor(Color.parseColor("#2196F3"));
        }
        return strWeek;
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
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}