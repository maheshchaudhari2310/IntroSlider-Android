package com.fabrides.introslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class Slider_Adapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public Slider_Adapter(Context context) {
        this.context = context;
    }
    int images []={
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
    };

    String headings []={
        "Welcome to Car & Bike Sharing!",
        "Sign up for Free!",
        "Share and Earn!",
        "Guranted increase in Sale!"
    };

    String desciptions []={
            "As a Fabrides Partner, share your car or bike when not in use, and start earning",
            "Become part of the Fabrides Partner program for absolutely free.",
            "Share your vehicle through fabride partner app whenever you want. Flexibility in sharing according to your time and will. Your weekly earning will get credited directly to your account.",
            "Reaching out and getting known by people as much as can so that they can take advantage of us"
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slides_layout,container,false);

        ImageView imageView=view.findViewById(R.id.slider_image);
        TextView heading=view.findViewById(R.id.slider_heading);
        TextView desc=view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(desciptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
