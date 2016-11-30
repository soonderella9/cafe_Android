package com.example.gginiggini.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gginiggini.Class.ListViewHolderComment;
import com.example.gginiggini.Item.Item_Comment;
import com.example.gginiggini.Item.Item_Complain;
import com.example.gginiggini.R;

import java.util.ArrayList;

//custom ArrayAdapter
public class CommentAdapter extends ArrayAdapter<Item_Comment> {

    private Context context;
    private ArrayList<Item_Comment> item_CommentArrayList;

    //constructor, call on creation
    public CommentAdapter(Context context, ArrayList<Item_Comment> objects) {
        super(context, 0, objects);

        this.context = context;
        this.item_CommentArrayList = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        ListViewHolderComment holder; // Holder Pattern -> prevent overload

        //get the property we are displaying
        Item_Comment curItem_Comment = item_CommentArrayList.get(position);

        //get the inflater and inflate the XML layout for each item
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_comment, null);
            holder = new ListViewHolderComment();
            holder.content = (TextView) convertView.findViewById(R.id.comment_content);
            holder.uName = (TextView) convertView.findViewById(R.id.comment_writer);
            holder.deleteButton = (Button) convertView.findViewById(R.id.deletebutton);

            convertView.setTag(holder);
        }
        else {
            holder = (ListViewHolderComment) convertView.getTag();
        }

        //set price and rental attributes
        holder.content.setText(String.valueOf(curItem_Comment.getContent()));
        holder.uName.setText(String.valueOf(curItem_Comment.getUid()));

//        get the image associated with this property

        return convertView;
    }
}