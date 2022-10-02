package com.example.sotrydniki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {

    Connection connection;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

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




    public void onClickADD(View v)
    {
        getImage();
        EditText Name = findViewById(R.id.etName);
        EditText Surname = findViewById(R.id.etSurname);
        ImageButton Img = findViewById(R.id.ImgBut);
        EditText JobTitle = findViewById(R.id.etJobTitle);


        if (Name.getText().length()==0|| Surname.getText().length()==0 ||  JobTitle.getText().length()==0 )
        {
            Toast.makeText(this, "Не заполнены обязательные поля", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "INSERT INTO Sotrudnic (Name, Surname, Img, Job_title) VALUES ('" + Name.getText() + "', '" + Surname.getText() + "', 'null', '" + JobTitle.getText() + "')";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(query);
                Toast.makeText(this,"Успешно добавлено", Toast.LENGTH_LONG).show();

            }
        }

        catch (Exception ex)
        {
            Toast.makeText(this,"Произошла ошибка", Toast.LENGTH_LONG).show();
        }
        Name.setText("");
        Surname.setText("");

        JobTitle.setText("");

    }



    public void onClickNAZAD(View v) {
        switch (v.getId()) {
            case R.id.NazadDATA:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}