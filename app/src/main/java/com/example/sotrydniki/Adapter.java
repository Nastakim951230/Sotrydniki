package com.example.sotrydniki;

import static com.example.sotrydniki.R.drawable.avator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class Adapter extends BaseAdapter {

    private Context nContext;


    public Adapter(Context nContext, List<Mask> maskList) {
        this.nContext = nContext;
        this.maskList = maskList;
    }

    List<Mask>  maskList;

    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int i) {return maskList.get(i);
    }

    @Override
    public long getItemId(int i) {return maskList.get(i).getID();}
    private Bitmap getUserImage(String encodedImg)
    {

        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else
        {
            return  BitmapFactory.decodeResource(nContext.getResources(), avator);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =View.inflate(nContext,R.layout.maska_table,null);

        TextView Name=v.findViewById(R.id.userNameTextView);
        TextView Surname= v.findViewById(R.id.userSurnameTextView);
        TextView Job_title= v.findViewById(R.id.userDolgnostextView);
        ImageView Image= v.findViewById(R.id.photoImageView);

        Mask mask=maskList.get(position);
        Name.setText(mask.getName());
        Surname.setText(mask.getSurname());
        Job_title.setText(mask.getJob_title());

        Image.setImageBitmap(getUserImage(mask.getImg()));

        return v;
    }
}
