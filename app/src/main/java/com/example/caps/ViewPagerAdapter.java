package com.example.caps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.caps.model.ResultsItem;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    Context mContext ;
    List<ResultsItem> mListScreen;

    public ViewPagerAdapter(Context mContext, List<ResultsItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mlayout = inflater.inflate(R.layout.m_layout,null);

        ImageView imgSlide = mlayout.findViewById(R.id.intro_img);
        TextView title = mlayout.findViewById(R.id.intro_title);
        TextView description = mlayout.findViewById(R.id.intro_description);

        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getOverview());
        imgSlide.setImageResource(mListScreen.get(position).getPosterPath);

        container.addView(mlayout);

        return mlayout;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }
}
