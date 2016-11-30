package com.example.gginiggini.Activity;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gginiggini.Adapter.CommentAdapter;
import com.example.gginiggini.Adapter.ComplainAdapter;
import com.example.gginiggini.Adapter.NavAdapter;
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.Item.Item_Comment;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * for show comment list class
 */
public class Comment extends AppCompatActivity {
    private Toolbar toolBar;
    private TextView commentContent;
    private ImageView commentRegister;
    private ListView lvCommentList = null;
    private CommentAdapter commentAdapter;
    private ArrayList<Item_Comment> items_commentList;
    private Bundle bundle;
    private String userName;
    private String userID;
    private String cafeName;
    private String menuName;
    private String menuDetail;
    private InputMethodManager inputMethodManager;
    private Context context;
    private SendPost commentListSP;
    private SendPost commentRegisterSP;
    private SendPost commentDeleteSP;
    private Handler handler;
    private Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        //import toolbar
        toolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("한줄평");
        lvCommentList = (ListView) findViewById(R.id.commentlist);
        items_commentList = new ArrayList<Item_Comment>();
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        commentAdapter = new CommentAdapter(this, items_commentList);
        commentContent = (EditText) findViewById(R.id.inputCommentInfo);
        commentRegister = (ImageView) findViewById(R.id.comment_register);
        deleteButton = (Button) findViewById(R.id.deletebutton);
        lvCommentList.setAdapter(commentAdapter);
        handler = new Handler();
        bundle = new Bundle();
        bundle = getIntent().getExtras();

        //get userInfo from intent
        userID = bundle.get("USERID").toString();
        userName = bundle.get("USERNAME").toString();
        cafeName = bundle.get("CAFENAME").toString();
        menuName = bundle.get("MENUNAME").toString();
        menuDetail = bundle.get("MENUDETAIL").toString();
        //Toast.makeText(Comment.this,userName+"님 환영합니다 ^^"+menuName+cafeName, Toast.LENGTH_LONG).show();
        //for reading comment list about menu
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("cafeName", cafeName);
            jsonParam.put("menuName", menuName);
            jsonParam.put("detailName", menuDetail);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        commentListSP = new SendPost(jsonParam, "comment/listApp");

        final String SPresult[] = new String[1];

        new Thread() {
            public void run() {
                SPresult[0] = commentListSP.executeClient();
                handler.post(new Runnable() {
                    public void run() {
                        doJSONParser(SPresult);
                        //recyclerAdapter1.notifyDataSetChanged();
                    }
                });
            }
        }.start();
     
        commentAdapter.notifyDataSetChanged();

        commentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("cafeName", cafeName);
                    jsonParam.put("menuName", menuName);
                    jsonParam.put("detailName", menuDetail);
                    jsonParam.put("contents", commentContent.getText().toString());
                    jsonParam.put("uid", userID);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                commentRegisterSP = new SendPost(jsonParam, "comment/registerApp");

                new Thread() {
                    public void run() {
                        commentRegisterSP.executeClient();
                    }
                }.start();
                commentContent.setText("");
                InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(commentContent.getWindowToken(), 0);
            }
        });
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

    public void linearOnClick(View v) {
        inputMethodManager.hideSoftInputFromWindow(commentContent.getWindowToken(), 0);
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
                    Item_Comment[] itemarray = new Item_Comment[JArray.length()];
                    for (int i = 0; i < JArray.length(); i++) {
                        JSONObject jk = JArray.getJSONObject(i);  // JSONObject 추출
                        itemarray[i] = new Item_Comment();
                        itemarray[i].setContent(jk.getString("contents"));
                        itemarray[i].setUid(jk.getString("name"));
                        items_commentList.add(itemarray[i]);
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