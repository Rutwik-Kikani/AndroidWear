package com.example.practicals.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.wear.widget.CircularProgressLayout;

import com.example.practicals.R;
import com.example.practicals.databinding.ActivityDeletetaskBinding;
import com.example.practicals.utils.TaskUtils;

public class DeletetaskActivity extends AppCompatActivity implements View.OnClickListener, CircularProgressLayout.OnTimerFinishedListener {

    ActivityDeletetaskBinding deleteBinding;
    Intent intent;
    String taskId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteBinding = ActivityDeletetaskBinding.inflate(getLayoutInflater());
        View view = deleteBinding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {

        intent = getIntent();
        taskId = intent.getStringExtra("TASK_ID");
        deleteBinding.circularProgress.setTotalTime(3000);
        deleteBinding.circularProgress.setOnClickListener(this);
        deleteBinding.circularProgress.setOnTimerFinishedListener(this);
        deleteBinding.circularProgress.startTimer();
    }

    @Override
    public void onClick(View v) {
        if(v==deleteBinding.circularProgress && deleteBinding.circularProgress.isTimerRunning()){
            deleteBinding.circularProgress.stopTimer();
            deleteBinding.txtDelete.setText(getString(R.string.cancel));
            deleteBinding.circularProgress.setBackgroundColor(ContextCompat.getColor (this, R.color.light_red));
        }
    }

    @Override
    public void onTimerFinished(CircularProgressLayout layout) {
        if (layout == deleteBinding.circularProgress) {
            if (taskId != "") {
                TaskUtils.deleteTask(taskId, this);
            }
            deleteBinding.txtDelete.setText(getString(R.string.task_removed));
            finish();
        }
    }
}