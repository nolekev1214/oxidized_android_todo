package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {
    private final LayoutInflater mInflator;
    private List<JSONObject> mItems;

    ToDoListAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ToDoListAdapter.ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.recyclerview_item, parent, false);
        return new ToDoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListAdapter.ToDoViewHolder holder, int position) {
        if (mItems != null) {
            try{
                JSONObject current = mItems.get(position);
                holder.todoItemBox.setText(current.getString("todo"));
                holder.todoItemBox.setChecked(current.getBoolean("done"));
                holder.todoCategory.setText(current.getString("category"));
            } catch (Exception e) {}
        } else {
            holder.todoItemBox.setText("Fucky");
            holder.todoItemBox.setChecked(false);
            holder.todoCategory.setText("wucky");
        }
    }

    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox todoItemBox;
        private final TextView todoCategory;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            todoItemBox = itemView.findViewById(R.id.todo_item);
            todoCategory = itemView.findViewById(R.id.category_text);
        }
    }
}
