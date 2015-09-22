package com.sprint3r.ronin.udemyfeedreader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {

    private List<String> itemsTitle;
    private List<String> itemsUrl;
    private List<String> itemImage;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        itemsTitle = new ArrayList();
        itemsUrl = new ArrayList();
        itemImage = new ArrayList();
    }

    @Override
    public RecyclerViewAdapter.ItemHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        CardView itemCardView = (CardView)layoutInflater.inflate(R.layout.layout_cardview, viewGroup, false);
        return new ItemHolder(itemCardView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ItemHolder itemHolder, int position) {
        itemHolder.setItemCourseTitle(itemsTitle.get(position));
        itemHolder.setItemCourseUrl(itemsUrl.get(position));
        itemHolder.setImageView(itemImage.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsTitle.size();
    }

    public void add(JsonArray results) {
        String courseTitle = "title";
        String courseUrl = "url";
        String courseImage = "image_240x135";
        int lastCardView = results.size() + itemsTitle.size();

        for (int index = itemsTitle.size() ; index < lastCardView; index++) {
            itemsTitle.add(index, results.get(index).getAsJsonObject().get(courseTitle).getAsString());
            itemsUrl.add(index, results.get(index).getAsJsonObject().get(courseUrl).getAsString());
            itemImage.add(index, results.get(index).getAsJsonObject().get(courseImage).getAsString());
            notifyItemInserted(getItemCount());
        }
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        private RecyclerViewAdapter parent;
        private CardView cardView;
        TextView textCourseTitle;
        TextView textCourseUrl;
        ImageView imageView;

        public ItemHolder(CardView cView, RecyclerViewAdapter parent) {
            super(cView);
            cardView = cView;
            this.parent = parent;
            textCourseTitle = (TextView) cardView.findViewById(R.id.course_title);
            textCourseUrl = (TextView) cardView.findViewById(R.id.course_url);
            imageView = (ImageView) cardView.findViewById(R.id.person_photo);
        }

        public void setItemCourseTitle(String courseTitle){
            textCourseTitle.setText(courseTitle);
        }

        public void setItemCourseUrl(String courseUrl){
            textCourseUrl.setText(courseUrl);
        }

        public void setImageView(String url){
            Bitmap mIcon_val;
            URL newUrl = null;
            try {
                newUrl = new URL(url);
                mIcon_val = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());
                imageView.setImageBitmap(mIcon_val);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}