package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.astonbitecode.j4rs.api.java2rust.Java2RustUtils;

public class AddItem extends AppCompatActivity {
    private EditText todoEditText;
    private EditText categoryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        todoEditText = findViewById(R.id.todo_edittext);
        categoryEditText = findViewById(R.id.category_edittext);
    }

    public void sendItem(View view) {
        String todoString = todoEditText.getText().toString();
        String todoCategoryString = categoryEditText.getText().toString();

        RustDatabase.addTodoItem(Java2RustUtils.createInstance(todoString),
                Java2RustUtils.createInstance(todoCategoryString));
    }
}