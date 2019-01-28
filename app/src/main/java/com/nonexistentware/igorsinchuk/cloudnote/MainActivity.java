package com.nonexistentware.igorsinchuk.cloudnote;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nonexistentware.igorsinchuk.cloudnote.Adapter.TaskAdapter;
import com.nonexistentware.igorsinchuk.cloudnote.Model.TaskModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlePage, subtitlePage, endPage;

    DatabaseReference reference;
    RecyclerView taskRecycler;
    ArrayList<TaskModel> taskList;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlePage = (TextView) findViewById(R.id.title_page);
        subtitlePage = (TextView) findViewById(R.id.subtitle_page);
        endPage = (TextView) findViewById(R.id.end_page);

        taskRecycler = findViewById(R.id.tasRecycler);
        taskRecycler.setLayoutManager(new LinearLayoutManager(this));
        taskList = new ArrayList<TaskModel>();

        reference = FirebaseDatabase.getInstance().getReference().child("CloudMemo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    TaskModel taskModel = dataSnapshot1.getValue(TaskModel.class);
                    taskList.add(taskModel);
                }
                taskAdapter = new TaskAdapter(MainActivity.this, taskList);
                taskRecycler.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
