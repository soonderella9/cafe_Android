package com.example.gginiggini.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gginiggini.Class.ListViewHolderComplain;
import com.example.gginiggini.Item.Item_Complain;
import com.example.gginiggini.R;

import java.util.ArrayList;

//custom ArrayAdapter
public class ComplainAdapter extends ArrayAdapter<Item_Complain> {

    private Context context;
    private ArrayList<Item_Complain> item_ComplainArrayList;

    //constructor, call on creation
    public ComplainAdapter(Context context, ArrayList<Item_Complain> objects) {
        super(context, 0, objects);

        this.context = context;
        this.item_ComplainArrayList = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        ListViewHolderComplain holder; // Holder Pattern -> prevent overload

        //get the property we are displaying
        Item_Complain curItem_Complain = item_ComplainArrayList.get(position);

        //get the inflater and inflate the XML layout for each item
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_complain, null);
            holder = new ListViewHolderComplain();
            holder.title = (TextView) convertView.findViewById(R.id.complain_title);
            holder.regDate = (TextView) convertView.findViewById(R.id.complain_date);
            holder.writer = (TextView) convertView.findViewById(R.id.complain_uid);
            holder.isReply = (TextView) convertView.findViewById(R.id.complain_isreply);
            convertView.setTag(holder);
        }
        else {
            holder = (ListViewHolderComplain) convertView.getTag();
        }

        //set price and rental attributes
        holder.title.setText(String.valueOf(curItem_Complain.getTitle()));
        holder.regDate.setText(String.valueOf(curItem_Complain.getDate()));
        holder.writer.setText(String.valueOf(curItem_Complain.getUid()));
        holder.isReply.setText(String.valueOf(curItem_Complain.getIsReply()));

//        get the image associated with this property

        return convertView;
    }
}