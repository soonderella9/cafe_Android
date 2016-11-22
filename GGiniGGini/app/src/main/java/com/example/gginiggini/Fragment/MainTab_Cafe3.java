package com.example.gginiggini.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gginiggini.Adapter.RecyclerAdapter;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;

import java.util.ArrayList;

/**
 * Created by 이용준 on 2016-11-01.
 */

public class MainTab_Cafe3 extends Fragment {

    private View view;
    private RecyclerView recyclerViewCafe1;
    private RecyclerAdapter recyclerAdapterCafe1;
    private LinearLayoutManager layoutManager;
    private ArrayList<Item_Menu> items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maintab_cafe1, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_cafe1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Item_Menu> items = new ArrayList<>();
        Item_Menu[] item = new Item_Menu[10];
        for(int i =0;i<10;i++){
            item[i] = new Item_Menu();
            item[i].setmName("비빔밥");
            item[i].setmPrice("3900원");
            item[i].setlCount("별10개");
            item[i].setScore("8.7점");
            item[i].setBestReply("너무맛있어요!");
            //item[i].setcInfo("맛있음");
            items.add(item[i]);
        }
        recyclerView.setAdapter(new RecyclerAdapter(getActivity(), items, R.layout.maintab_cafe1));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
