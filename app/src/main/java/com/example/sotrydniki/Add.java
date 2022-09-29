package com.example.sotrydniki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {

    View v;
    Connection connection;
    List<Mask> data;
    ListView listView;
    Adapter pAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        v = findViewById(com.google.android.material.R.id.ghost_view);
        GetTextFromSQL(v);
    }

    public void enterMobile() {
        pAdapter.notifyDataSetInvalidated();
        listView.setAdapter(pAdapter);
    }

    public void SetLister()
    {

    }

    public void GetTextFromSQL(View v) {
        data = new ArrayList<Mask>();
        listView = findViewById(R.id.BD);
        pAdapter = new Adapter(Add.this, data);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "Select * From Sotrudnic";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Mask tempMask = new Mask
                            (resultSet.getInt("ID"),
                                    resultSet.getString("Name"),
                                    resultSet.getString("Surname"),
                                    resultSet.getString("Img"),
                                    resultSet.getString("Job_title")

                            );
                    data.add(tempMask);
                    pAdapter.notifyDataSetInvalidated();
                }
                connection.close();
            } else {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        enterMobile();

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