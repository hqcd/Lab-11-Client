package com.example.duong.lab11client;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    Button retrieveBtn;
    TextView studentList;

    ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveBtn = (Button)findViewById(R.id.retrieveButton);
        studentList = (TextView)findViewById(R.id.studentList);

        contentResolver = getContentResolver();

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieve();
            }
        });
    }

    public void retrieve()
    {
        Cursor cursor = contentResolver.query(Uri.parse("content://students.com.duong/STUDENTS"), null, null, null, null, null);
        StringBuilder list = new StringBuilder("");
        if(cursor!= null && cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
                list.append("Stduent: " + cursor.getString(1) + " , " + "Grade: " + cursor.getInt(2) + "\n");
            }
        }
        cursor.close();

        studentList.setText(list.toString());
    }
}
