package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final List<JSONObject> mTodosList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 20; i++){
            Map objectData = new HashMap();
            objectData.put("todo", "item number " + i);
            objectData.put("done", "false");
            objectData.put("category", "None");
            JSONObject dummy = new JSONObject(objectData);
            mTodosList.add(dummy);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ToDoListAdapter adapter = new ToDoListAdapter(this, mTodosList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}