package com.fabrides.introslider;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Fancy_Walk_Through extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout dots_layout;
    private Slider_Adapter slider_adapter;
    private TextView[] dots;
    private Button get_started_btn;
    private Button next_btn;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fancy_walk_through);

        viewPager=findViewById(R.id.slider);
        dots_layout=findViewById(R.id.dots);
        get_started_btn=findViewById(R.id.get_started_btn);
        next_btn=findViewById(R.id.next_btn);

        sp=getSharedPreferences("FabridesPartner",MODE_PRIVATE);

        slider_adapter=new Slider_Adapter(this);
        viewPager.setAdapter(slider_adapter);
        addDots(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        get_started_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor se=sp.edit();
                se.putString("FANCYWALK","SUCCESS");
                se.apply();
                //startActivity(new Intent(Fancy_Walk_Through.this,Login_Activity.class));
                finish();
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
    }
    private void addDots(int position)
    {
        dots=new TextView[4];
        dots_layout.removeAllViews();
        for(int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dots_layout.addView(dots[i]);
        }
        if(dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.purple_200));
        }
        if(position>=0 && position<3)
        {
            get_started_btn.setVisibility(View.GONE);
            next_btn.setVisibility(View.VISIBLE);
        }
        else
        {
            next_btn.setVisibility(View.GONE);
            get_started_btn.setVisibility(View.VISIBLE);
        }
    }
    ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab=new AlertDialog.Builder(Fancy_Walk_Through.this);
        ab.setTitle("Please wait!");
        ab.setMessage("Do you want to exit?");
        ab.setNegativeButton("No",null);
        ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
    }
}