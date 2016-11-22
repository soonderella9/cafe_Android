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


import com.example.gginiggini.Class.CircleImageView;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.Activity.Menu;
import com.example.gginiggini.R;
import com.example.gginiggini.Activity.StarPopUp;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<Item_Menu> items;
    int item_layout;

    public RecyclerAdapter(Context context, ArrayList<Item_Menu> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem_menu, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item_Menu item = items.get(position);
        holder.mName.setText(item.getmName());
        holder.mPrice.setText(item.getmPrice());
        holder.mLikeCount.setText(item.getlCount());
        holder.mScore.setText(item.getScore());
        holder.mReply.setText(item.getBestReply());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, item.getmName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent ( context, Menu.class);
                context.startActivity(intent);
            }
        });
        holder.mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getmName(), Toast.LENGTH_SHORT).show();

            }
        });
        holder.mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //Toast.makeText(context, item.getmName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent ( context, StarPopUp.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView mphoto;
        TextView mName;
        TextView mPrice;
        Button mLike;
        TextView mLikeCount;
        ImageView mStar;
        TextView mScore;
        TextView mReply;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.mname);
            mphoto = (CircleImageView) itemView. findViewById(R.id.mphoto);
            mPrice = (TextView) itemView.findViewById(R.id.mprice);
            mLike = (Button) itemView.findViewById(R.id.mlike) ;
            mLikeCount = (TextView) itemView.findViewById(R.id.mlikecount);
            mStar = (ImageView) itemView.findViewById(R.id.star_image);
            mScore = (TextView) itemView.findViewById(R.id.mscore);
            mReply = (TextView) itemView.findViewById(R.id.mreply);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}