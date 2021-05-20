package com.example.entertainers_troupe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Custome_view_nottifications extends BaseAdapter {
    String[]nid,not,date;
    private Context context;

    public Custome_view_nottifications(Context appcontext, String[]nid1, String[]not, String[]date)
    {
        this.context=appcontext;
        this.nid=nid1;
        this.not=not;
        this.date=date;

    }

    @Override
    public int getCount() {
        return not.length;
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
            gridView=inflator.inflate(R.layout.custom_view_nottification,null);
        }
        else
        {
            gridView=(View)view;
        }
        TextView tv1=(TextView)gridView.findViewById(R.id.date);
        TextView tv2=(TextView)gridView.findViewById(R.id.notti);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);

        tv1.setText(date[i]);
        tv2.setText(not[i]);



        return gridView;
    }
}
