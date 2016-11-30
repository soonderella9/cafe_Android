package com.example.gginiggini.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.gginiggini.Activity.NoticeRead;
import com.example.gginiggini.Activity.StarPopUp;
import com.example.gginiggini.Class.AdapterCommunicate;
import com.example.gginiggini.Class.CircleImageView;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.Item.Item_Notice;
import com.example.gginiggini.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by 이용준 on 2016-11-24.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    Context context;
    ArrayList<Item_Notice> items;
    int item_layout;
    public NoticeAdapter(Context context, ArrayList<Item_Notice> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem_notice, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticeAdapter.ViewHolder holder, int position) {
        final Item_Notice item = items.get(position);
        holder.nTitle.setText(item.getTitle());
        holder.nDate.setText(item.getDate());
        holder.nContent.setText(item.getContent());

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nTitle;
        TextView nDate;
        TextView nContent;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);

            nTitle = (TextView) itemView.findViewById(R.id.notice_title);
            nDate = (TextView) itemView. findViewById(R.id.notice_date);
            nContent = (TextView) itemView.findViewById(R.id.notice_content);
            cardView = (CardView) itemView.findViewById(R.id.cardview_notice);
        }
    }
}
