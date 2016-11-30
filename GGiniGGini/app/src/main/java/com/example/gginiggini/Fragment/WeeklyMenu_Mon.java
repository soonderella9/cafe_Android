package com.example.gginiggini.Fragment;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gginiggini.Activity.Home;
import com.example.gginiggini.Activity.Weekly_Domi;
import com.example.gginiggini.Adapter.RecyclerAdapter;
import com.example.gginiggini.Class.Flag;
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.Item.Item_Complain;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 이용준 on 2016-11-01.
 */

public class WeeklyMenu_Mon extends Fragment {

    private Weekly_Domi home;
    private View view;
    private RecyclerView recyclerViewCafe1;
    private LinearLayoutManager layoutManager;
    private ArrayList<Item_Menu> items= new ArrayList<>();;
    private String userID;
    private String userName;
    private Bundle bundle;
    private Calendar cal = Calendar.getInstance();
    private int nWeek = cal.get(Calendar.DAY_OF_WEEK);
    private String stringDate=doDayOfWeek(nWeek);
    private SendPost sangrokTodaySP;
    private SendPost sangrokFixedSP;
    private Handler handler = new Handler();
    private RecyclerAdapter recyclerAdapter1;
    private RecyclerView recyclerView1;
    private String cafeName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maintab_cafe1, container, false);
        recyclerView1 = (RecyclerView)view.findViewById(R.id.recyclerview_cafe1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager);
        bundle = new  Bundle();
        userID = Home.flag.userUID;
        recyclerAdapter1= new RecyclerAdapter(userID, userName, getActivity(), items, R.layout.maintab_cafe1);
        return view;
    }
    private String doDayOfWeek(int date ) {
        String strWeek=null;
        if (date == 1) {
            strWeek="SUNDAY";
        } else if (date == 2) {
            strWeek="MONDAY";
        } else if (date == 3) {
            strWeek="TUESDAY";
        } else if (date == 4) {
            strWeek="WEDNESDAY";
        } else if (date == 5) {
            strWeek="THUSDAY";
        } else if (date == 6) {
            strWeek="FRIDAY";
        } else if (date == 7) {
            strWeek="SATURDAY";
        }
        return strWeek;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                        if(jk.getString("detailName").equals("일품"))
                            itemarray[i].setmPhoto(getResources().getDrawable(R.drawable.illpoom));
                        else if(jk.getString("detailName").equals("뚝배기"))
                            itemarray[i].setmPhoto(getResources().getDrawable(R.drawable.bowl));
                        else if(jk.getString("detailName").equals("백반"))
                            itemarray[i].setmPhoto(getResources().getDrawable(R.drawable.back));
                        else if(jk.getString("detailName").equals("양식"))
                            itemarray[i].setmPhoto(getResources().getDrawable(R.drawable.donn));
                        else
                            itemarray[i].setmPhoto(getResources().getDrawable(R.drawable.food));
                        itemarray[i].setcName(jk.get("cafeName").toString());
                        itemarray[i].setmName(jk.getString("menuName"));
                        itemarray[i].setmPrice(jk.getString("price"));
                        itemarray[i].setlCount(jk.getInt("likeNum"));
                        itemarray[i].setScore(jk.getInt("point"));
                        if(jk.getBoolean("isLike"))
                            itemarray[i].setmLike(getResources().getDrawable(R.drawable.heart_fill));
                        else if(!jk.getBoolean("isLike"))
                            itemarray[i].setmLike(getResources().getDrawable(R.drawable.heart_blank));
                        if(jk.getString("dateFlag").equals("BREAKFAST"))
                            itemarray[i].setmWhen("아침");
                        else if(jk.getString("dateFlag").equals("LUNCH"))
                            itemarray[i].setmWhen("점심");
                        else if(jk.getString("dateFlag").equals("DINNER"))
                            itemarray[i].setmWhen("저녁");
                        itemarray[i].setmDetail(jk.getString("detailName"));

                        items.add(itemarray[i]);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView1.setAdapter(recyclerAdapter1);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            Log.v("상롱상록랏옥1", "??");
            items.clear();
            //recyclerAdapter1.notifyDataSetChanged();
            JSONObject jsonParam = new JSONObject();
            try {
                jsonParam.put("uid",userID);
                jsonParam.put("cafeName", Home.flag.cafeFlag);
                jsonParam.put("wFlag", "MONDAY");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            sangrokTodaySP = new SendPost(jsonParam, "weekly/listApp");

            final String SPresult[] = new String[1];

            new Thread() {
                public void run() {
                    SPresult[0] = sangrokTodaySP.executeClient();
                    handler.post(new Runnable() {
                        public void run() {
                            doJSONParser(SPresult);
                            //recyclerAdapter1.notifyDataSetChanged();
                        }
                    });
                }
            }.start();
        }
        else
        {
            Thread.interrupted();
        }
    }
}
