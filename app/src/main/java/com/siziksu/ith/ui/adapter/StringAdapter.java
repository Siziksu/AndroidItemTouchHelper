package com.siziksu.ith.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siziksu.ith.R;

import java.util.ArrayList;
import java.util.List;

public final class StringAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<String> items;
    private final Context context;
    private final ClickListener listener;

    public StringAdapter(Context context, ClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_string, parent, false);
        return new LocalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LocalViewHolder) holder).itemText.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void showItems(List<String> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    private final class LocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View itemRow;
        TextView itemText;

        LocalViewHolder(View view) {
            super(view);
            itemRow = view.findViewById(R.id.listStringItemRow);
            itemText = (TextView) view.findViewById(R.id.listStringItemText);
            itemRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(view, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {

        void onClick(View view, int position);
    }
}
