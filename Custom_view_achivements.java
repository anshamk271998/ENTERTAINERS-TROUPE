package com.example.entertainers_troupe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_view_achivements extends BaseAdapter {
    String[]date,image,desc;
    private Context context;

    public Custom_view_achivements(Context appcontext, String[]date, String[]image, String[]desc)
    {
        this.context=appcontext;
        this.date=date;
        this.image=image;
        this.desc=desc;

    }

    @Override
    public int getCount() {
        return date.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=inflator.inflate(R.layout.custom_view_achivements,null);
        }
        else
        {
            gridView=(View)view;
        }
        TextView tv1=(TextView)gridView.findViewById(R.id.date);
        TextView tv2=(TextView)gridView.findViewById(R.id.des);
        ImageView img=(ImageView) gridView.findViewById(R.id.imageView8);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);

        tv1.setText(date[i]);
        tv2.setText(desc[i]);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");
        String url="http://" + ip + ":8000"+image[i];
        Picasso.with(context).load(url).into(img);



        return gridView;
    }
}
