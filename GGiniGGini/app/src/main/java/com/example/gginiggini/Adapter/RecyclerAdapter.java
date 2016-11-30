package com.example.gginiggini.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.example.gginiggini.Class.SendPost;
import com.example.gginiggini.Item.Item_Menu;
import com.example.gginiggini.R;
import com.example.gginiggini.Activity.StarPopUp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.gginiggini.R.drawable.heart_fill;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<Item_Menu> items;
    int item_layout;
    private String userID;
    private String userName;
    private String cafeName;
    private SendPost menuLikeSP;
    public RecyclerAdapter(String userID, String userName, Context context, ArrayList<Item_Menu> items, int item_layout) {
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
        holder.cName.setText(item.getcName());
        holder.mphoto.setImageDrawable(item.getmPhoto());
        holder.mName.setText(item.getmName());
        holder.mPrice.setText(item.getmPrice());
        holder.mLike.setImageDrawable(item.getmLike());
        holder.mLikeCount.setText(Integer.toString(item.getlCount()));
        holder.mScore.setText(Integer.toString(item.getScore()));
        holder.mWhen.setText(item.getmWhen());
        holder.mDetail.setText(item.getmDetail());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView mphoto;
        TextView cName;
        TextView mName;
        TextView mPrice;
        ImageView mLike;
        TextView mLikeCount;
        ImageView mStar;
        TextView mScore;
        TextView mWhen;
        TextView mDetail;
        CardView cardview;
        RelativeLayout relaScore;
        RelativeLayout relaComment;
        public ViewHolder(View itemView) {
            super(itemView);

            cName = (TextView) itemView.findViewById(R.id.cname);
            mName = (TextView) itemView.findViewById(R.id.mname);
            mphoto = (CircleImageView) itemView. findViewById(R.id.mphoto);
            mPrice = (TextView) itemView.findViewById(R.id.mprice);
            mLike = (ImageView) itemView.findViewById(R.id.heartimage) ;
            mLikeCount = (TextView) itemView.findViewById(R.id.mlikecount);
            mWhen = (TextView) itemView.findViewById(R.id.mwhen);
            mDetail = (TextView) itemView.findViewById(R.id.mdetail);
            mStar = (ImageView) itemView.findViewById(R.id.starimage);
            mScore = (TextView) itemView.findViewById(R.id.mscore);
            relaScore=(RelativeLayout) itemView.findViewById(R.id.rela_score);
            relaComment=(RelativeLayout) itemView.findViewById(R.id.rela_comment);
            cardview = (CardView) itemView.findViewById(R.id.cardview_menu);
            mLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      //  Drawable judgeImg = heart_fill.getDrawable();
                    //Drawable tempRes = context.getResources().getDrawable(R.drawable.he );
                       Toast.makeText(context, "좋아요가 반영되었습니다", Toast.LENGTH_SHORT).show();
                       int n = Integer.parseInt(mLikeCount.getText().toString());
                       mLike.setImageResource(heart_fill);
                       n = n + 1;
                       mLikeCount.setText(n + "");
                       JSONObject jsonParam = new JSONObject();
                       try {
                           jsonParam.put("cafeName", cName.getText().toString());
                           jsonParam.put("menuName", mName.getText().toString());
                           jsonParam.put("detailName", mDetail.getText().toString());
                           jsonParam.put("uid", userID);
                       } catch (JSONException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                       }

                       menuLikeSP = new SendPost(jsonParam, "like/checkLike");

                       new Thread() {
                           public void run() {
                               menuLikeSP.executeClient();
                           }
                       }.start();
                }
            });
            relaScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (context, StarPopUp.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("MENUNAME", mName.getText().toString());
                    intent.putExtra("MENUDETAIL", mDetail.getText().toString());
                    intent.putExtra("CAFENAME", cName.getText().toString());
                    intent.putExtra("USERID",userID);
                    intent.putExtra("USERNAME",userName);
                    context.startActivity(intent);
                }
            });
            relaComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( context, Comment.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("MENUNAME", mName.getText().toString());
                    intent.putExtra("MENUDETAIL", mDetail.getText().toString());
                    intent.putExtra("CAFENAME", cName.getText().toString());
                    intent.putExtra("USERID",userID);
                    intent.putExtra("USERNAME",userName);
                    context.startActivity(intent);
                }
            });
        }
    }
}