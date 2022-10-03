package com.example.sotrydniki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Base64;

public class Update extends AppCompatActivity {
 ImageButton imageButton;
 EditText Name, Surname, Dolgnost;
 Button Update,Nazad,Delet;
 Mask mask;
 Connection connection;
    View v;
String img="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mask=getIntent().getParcelableExtra("Sotrudnic");

        imageButton=findViewById(R.id.ImagBD);

        Name= findViewById(R.id.UpdataName);
        Name.setText(mask.getName());

        Surname=findViewById(R.id.UpdataSurname);
        Surname.setText(mask.getSurname());

        Dolgnost=findViewById(R.id.UpdataDolgnost);
        Dolgnost.setText(mask.getJob_title());

        imageButton.setImageBitmap(getImgBitmap(mask.getImg()));
        v =findViewById(com.google.android.material.R.id.ghost_view);


    }


    private Bitmap getImgBitmap(String encodedImg) {
        if (encodedImg != null) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getDecoder().decode(encodedImg);
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return BitmapFactory.decodeResource(Update.this.getResources(),
                R.drawable.avator);
    }


    public void onClickChooseImage(View view)
    {
        getImage();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && data!= null && data.getData()!= null)
        {
            if(resultCode==RESULT_OK)
            {
                Log.d("MyLog","Image URI : "+data.getData());
                imageButton.setImageURI(data.getData());
                Bitmap bitmap = ((BitmapDrawable)imageButton.getDrawable()).getBitmap();
                encodeImage(bitmap);

            }
        }
    }

    private void getImage()
    {
        Intent intentChooser= new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser,1);
    }

    private String encodeImage(Bitmap bitmap) {
        int prevW = 150;
        int prevH = bitmap.getHeight() * prevW / bitmap.getWidth();
        Bitmap b = Bitmap.createScaledBitmap(bitmap, prevW, prevH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            img=Base64.getEncoder().encodeToString(bytes);
            return img;
        }
        return "";
    }
    public void onClickUpdate(View v){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "UPDATE Sotrudnic Set Name = '" + Name.getText() + "', Surname = '" + Surname.getText() + "', Img ='" + img + "', Job_title ='" + Dolgnost.getText() + "' WHERE ID= "+mask.getID()+"";
                Statement statement = connection.createStatement();
                statement.executeQuery(query);
            }
        }

        catch (Exception ex)
        {

        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickDelet(View v){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "DELETE FROM  Sotrudnic  WHERE ID= "+mask.getID()+"";
                Statement statement = connection.createStatement();
                statement.executeQuery(query);
            }
        }

        catch (Exception ex)
        {

        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNAZAD(View v) {
        switch (v.getId()) {
            case R.id.Nazad:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}