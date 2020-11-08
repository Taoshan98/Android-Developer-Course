package com.example.viewpagerscroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

public class MyViewPager extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;

    public int[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3};

    public MyViewPager (Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
     /*   inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.slide_layout, container, false);*/

        View v = LayoutInflater.from(context).inflate(R.layout.slide_layout, container, false);

        ImageView imageView = v.findViewById(R.id.image);

        imageView.setImageResource(images[position]);

        Glide.with(context).load(images[position]).into(imageView);
        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
