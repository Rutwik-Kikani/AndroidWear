package com.example.practicals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicals.databinding.MenuItemsBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<MenuItem> mnuList;

    MenuItemsBinding itemsBinding;
    public MenuAdapter(List<MenuItem> itemList, Context context ) {
        super();
        this.mnuList = itemList;
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

    class ViewHolder extends RecyclerView.ViewHolder {
        MenuItemsBinding recyclerRowBinding;
        public ViewHolder(MenuItemsBinding menuItemsBinding){
            super(menuItemsBinding.getRoot());
            this.recyclerRowBinding = menuItemsBinding;
        }
        public void bindView(MenuItem objMenu){
            recyclerRowBinding.menuItem.setText(objMenu.getText());
            recyclerRowBinding.menuIcon.setImageResource(objMenu.getImage());
        }
    }
}
