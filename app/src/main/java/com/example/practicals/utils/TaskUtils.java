package com.example.practicals.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.practicals.activity.DeletetaskActivity;
import com.example.practicals.model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskUtils {
    public static void saveTask(Task task , Context context){
        if(task != null){
            SharedPreferences sharedPref = context.getSharedPreferences ( "task_details", Context.MODE_PRIVATE);
            SharedPreferences. Editor editor = sharedPref.edit();
            editor.putString(task.getId(), task.getTaskDetails());
            editor.commit();
        }
    }

    public static List<Task> getAllTasks(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("task_details", context.MODE_PRIVATE);
        List<Task> mTasks = new ArrayList<>();
        Map<String, ?> map = sharedPreferences.getAll();

        Set set = map.entrySet();
        Iterator itr = set.iterator();

        while(itr.hasNext()){
            Map.Entry entry = (Map.Entry)itr.next();
            String savedTask = (String) entry.getValue();
            if(savedTask != null){
                Task task = new Task(entry.getKey().toString(), savedTask);
                mTasks.add(task);
            }
        }
        return mTasks;

    }

    public static void deleteTask(String id, Context context) {
        if (id != null) {
            SharedPreferences sharedPref = context.getSharedPreferences ("task_details", Context. MODE_PRIVATE);
            SharedPreferences. Editor editor = sharedPref.edit();
            editor.remove(id);
            editor.commit();
        }
    }
}
