package com.example.practicals;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.activity.ConfirmationActivity;

import com.example.practicals.databinding.MenuItemsBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<MenuItem> mnuList;
    Intent confirmIntent1;
    Context context;
    MenuItemsBinding itemsBinding;

    public MenuAdapter(List<MenuItem> itemList, Context context) {
        super();
        this.mnuList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        itemsBinding = MenuItemsBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mnuList.get(position));
    }

    @Override
    public int getItemCount() {
        return mnuList.size();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.menuIcon){
            confirmIntent1 = new Intent(context, ConfirmationActivity.class);
            confirmIntent1.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION);
            confirmIntent1.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Succeeded!!");
        }
        else if(view.getId() == R.id.menuItem){
            confirmIntent1 = new Intent(context, ConfirmationActivity.class);
            confirmIntent1.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.FAILURE_ANIMATION);
            confirmIntent1.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Failed!!");
        }
        confirmIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(confirmIntent1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MenuItemsBinding recyclerRowBinding;

        public ViewHolder(MenuItemsBinding menuItemsBinding) {
            super(menuItemsBinding.getRoot());
            this.recyclerRowBinding = menuItemsBinding;
        }

        public void bindView(MenuItem objMenu) {
            recyclerRowBinding.menuItem.setText(objMenu.getText());
            recyclerRowBinding.menuIcon.setImageResource(objMenu.getImage());
            recyclerRowBinding.menuIcon.setOnClickListener(MenuAdapter.this);
            recyclerRowBinding.menuItem.setOnClickListener(MenuAdapter.this);
        }
    }
}
