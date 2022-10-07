package com.example.sotrydniki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Add extends AppCompatActivity {

String img;
    Connection connection;
    private ImageView imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String img="";
        imageButton=findViewById(R.id.ImgBut);


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

    public void Perehod()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void onClickADD(View v)
    {


        EditText Name = findViewById(R.id.etName);
        EditText Surname = findViewById(R.id.etSurname);
        ImageView Img = findViewById(R.id.ImgBut);
        EditText JobTitle = findViewById(R.id.etJobTitle);

        AlertDialog.Builder builder=new AlertDialog.Builder(Add.this);
        builder.setTitle("Добавление")
                .setMessage("Вы уверены что хотите добавить данные")
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Name.getText().length()==0|| Surname.getText().length()==0 ||  JobTitle.getText().length()==0 )
                        {
                            Toast.makeText(Add.this, "Не заполнены обязательные поля", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            String query="";
                            ConnectionHelper connectionHelper = new ConnectionHelper();
                            connection = connectionHelper.connectionClass();
                            if (connection != null) {
                                if(img==null)
                                {
                                    query = "INSERT INTO Sotrudnic (Name, Surname,  Job_title) VALUES ('" + Name.getText() + "', '" + Surname.getText() + "', '" + JobTitle.getText() + "')";
                                }
                                else
                                {
                                    query = "INSERT INTO Sotrudnic (Name, Surname, Img, Job_title) VALUES ('" + Name.getText() + "', '" + Surname.getText() + "', '"+img+"', '" + JobTitle.getText() + "')";
                                }
                                Statement statement = connection.createStatement();
                                Toast.makeText(Add.this,"Успешно добавлено", Toast.LENGTH_LONG).show();
                                ResultSet result = statement.executeQuery(query);


                            }
                        }

                        catch (Exception ex)
                        {
                            Toast.makeText(Add.this,"Произошла ошибка", Toast.LENGTH_LONG).show();
                        }
                        Name.setText("");
                        Surname.setText("");
                        JobTitle.setText("");
                        Perehod();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();

    }





    public void onClickNAZAD(View v) {
        switch (v.getId()) {
            case R.id.NazadDATA:
                Perehod();
                break;
        }
    }
}