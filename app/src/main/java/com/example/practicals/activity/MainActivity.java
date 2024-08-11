package com.example.practicals.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.practicals.R;
import com.example.practicals.adapter.TaskRecyclerViewAdapter;
import com.example.practicals.databinding.ActivityMainBinding;
import com.example.practicals.model.Task;
import com.example.practicals.utils.ConfirmUtils;
import com.example.practicals.utils.Constants;
import com.example.practicals.utils.TaskUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    private List<Task> myTasks = new ArrayList<>();
    private TaskRecyclerViewAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTaskAdapter();
    }

    public void init() {
        setLayout();
        initTask();
    }

    private void initTask() {
        mainBinding.edtTask.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String text = v.getText().toString();
                    if (!TextUtils.isEmpty(text)) {
                        Task task = createTask(null, text);
                        updateTask(task, Constants.Action_ADD);
                        v.setText("");
                        return (true);
                    }
                }
                return false;
            }
        });
    }

    private void setLayout() {
        mainBinding.wrcViewTasks.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mainBinding.wrcViewTasks.setLayoutManager(mLayoutManager);
    }


    private Task createTask(String id, String task) {
        if (id == null) {
            id = String.valueOf(System.currentTimeMillis());
        }
        return new Task(id, task);
    }

    private void updateTask(Task task, int action) {
        if (action == Constants.Action_ADD) {
            TaskUtils.saveTask(task, this);
            ConfirmUtils.showSavedMessage(getString(R.string.task_saved), this);
        }
        updateTaskAdapter();
    }

    private void updateTaskAdapter() {
        myTasks.clear();
        myTasks.addAll(TaskUtils.getAllTasks(this));
        bindAdapter();
        
    }

    private void bindAdapter() {
        mAdapter= new TaskRecyclerViewAdapter(myTasks, getApplicationContext());
        mainBinding.wrcViewTasks.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


}
