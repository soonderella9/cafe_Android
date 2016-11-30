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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.gginiggini.Activity.Comment;
import com.example.gginiggini.Activity.Home;
import com.example.gginiggini.Class.CircleImageView;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;
import com.example.gginiggini.Activity.StarPopUp;

import java.util.ArrayList;

public class MyFavoritesAdapter extends RecyclerView.Adapter<MyFavoritesAdapter.ViewHolder> {
    Context context;
    ArrayList<Item_Menu> items;
    int item_layout;
    private String userID;
    private String userName;

    public MyFavoritesAdapter(String userID, String userName, Context context, ArrayList<Item_Menu> items, int item_layout) {
        //this.userName = userName;
        this.userID = userID;
        this.userName = userName;
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
        holder.mDetail.setText(item.getmDetail());
        holder.mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "좋아요가 반영되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
        holder.relaScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( context, StarPopUp.class);
                context.startActivity(intent);
            }
        });
        holder.relaComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( context, Comment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("USERID",userID);
                intent.putExtra("USERNAME",userName);
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
        TextView mDetail;
        CardView cardview;
        RelativeLayout relaScore;
        RelativeLayout relaComment;
        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.mname);
            mphoto = (CircleImageView) itemView. findViewById(R.id.mphoto);
            mPrice = (TextView) itemView.findViewById(R.id.mprice);
            mLike = (Button) itemView.findViewById(R.id.heartimage) ;
            mLikeCount = (TextView) itemView.findViewById(R.id.mlikecount);
            mDetail = (TextView) itemView.findViewById(R.id.mdetail);
            mStar = (ImageView) itemView.findViewById(R.id.starimage);
            mScore = (TextView) itemView.findViewById(R.id.mscore);
            relaScore=(RelativeLayout) itemView.findViewById(R.id.rela_score);
            relaComment=(RelativeLayout) itemView.findViewById(R.id.rela_comment);
            cardview = (CardView) itemView.findViewById(R.id.cardview_menu);
        }
    }
}