package ru.eltexstudy.courseapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import ru.eltexstudy.courseapp.CurrentNavItem;
import ru.eltexstudy.courseapp.R;
import ru.eltexstudy.courseapp.RecyclerViewInterface;

public class AdapterMainNavigation extends RecyclerView.Adapter<AdapterMainNavigation.Holder> {
    private final RecyclerViewInterface recyclerViewInterface;
    LinkedList<CurrentNavItem> data;

    public AdapterMainNavigation(LinkedList<CurrentNavItem> data, RecyclerViewInterface recyclerViewInterface) {
        this.data = data;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_nav, null, false);
        return new Holder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.menuElement.setText(data.get(position).getName());
        holder.menuElement.setTextColor(Color.parseColor(data.get(position).getColor()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView menuElement;

        public Holder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            menuElement = itemView.findViewById(R.id.menuElement);
            itemView.setOnClickListener(view -> {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemNavigationClick(pos);
                    }
                }
            });
        }
    }


}
