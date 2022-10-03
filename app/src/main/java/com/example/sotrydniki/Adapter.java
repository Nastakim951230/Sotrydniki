package com.example.sotrydniki;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {


    private Context nContext;
    private ArrayList<Mask> mMask;
    private OnItemClickListener mListener;
    String img="";

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


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

    public static Bitmap loadContactPhoto(ContentResolver cr, long id, Context context) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
        if (input == null) {
            Resources res = context.getResources();
            return BitmapFactory.decodeResource(res, R.drawable.avatar);
        }
        return BitmapFactory.decodeStream(input);
    }


    private Bitmap getUserImage(String encodedImg)
    {
        byte[] bytes;
        if(encodedImg!=null&& !encodedImg.equals("null")) {
            bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else
        {

            return BitmapFactory.decodeResource(nContext.getResources(), R.drawable.avatar);
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

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenDetalis=new Intent(nContext,Update.class);
                intenDetalis.putExtra("Sotrudnic",mask);
                nContext.startActivity(intenDetalis);

            }
        });
        return v;
    }
}
