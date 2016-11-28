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

import com.example.gginiggini.Adapter.CommentAdapter;
import com.example.gginiggini.Adapter.ComplainAdapter;
import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Adapter.Home_PageAdapter;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;

import java.util.ArrayList;

/**
 * for show comment list class
 */
public class Comment extends AppCompatActivity {
    private Toolbar toolBar;
    private ListView lvCommentList=null;
    private CommentAdapter commentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        //import toolbar
        toolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("한줄평");
        commentAdapter = new CommentAdapter();
        lvCommentList = (ListView) findViewById(R.id.commentlist);
        lvCommentList.setAdapter(commentAdapter);
        //for enroll comment content
        for (int i = 0; i < 10; i++) {
            commentAdapter.addItem("이용준", "야채가 좀많긴 한데 매우 맛있군요 ㅎㅎㅎ", "122");
        }
//        lvCommentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                // get item
//                //ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
//
//                Intent intent = new Intent(Comment.this, CommentRead.class);
//                startActivity(intent);
//
//            }
//        }) ;
    }

    /**
     * back button method
     */
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