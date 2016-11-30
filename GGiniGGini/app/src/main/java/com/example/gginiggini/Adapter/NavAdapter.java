package com.example.gginiggini.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.gginiggini.Item.Item_NavList;
import com.example.gginiggini.R;

import java.util.ArrayList;

public class NavAdapter extends BaseAdapter {
    // ArrayList for save data which added on Adapter
    private ArrayList<Item_NavList> listViewItemList = new ArrayList<Item_NavList>() ;

    public NavAdapter() {
    }

    // return data number used on Adapter
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // return view that is going to use print data on position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // convertview obtain reference by inflate "item_complain" layout
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_navlist, parent, false);
        }

        //obtain reference about widget on xml
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.nav_image) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.nav_text) ;

        //obtain data reference on data from dataset
        Item_NavList listViewItem = listViewItemList.get(position);

        // reflect data on widget
        iconImageView.setImageDrawable(listViewItem.getNavIcon());
        titleTextView.setText(listViewItem.getNavText());
        return convertView;
    }

    // id return associated with data on position
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // data return for setted position
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // Function for add item data.
    public void addItem(Drawable icon, String title) {
        Item_NavList item = new Item_NavList();
        item.setNavIcon(icon);
        item.setNavText(title);

        listViewItemList.add(item);
    }
}