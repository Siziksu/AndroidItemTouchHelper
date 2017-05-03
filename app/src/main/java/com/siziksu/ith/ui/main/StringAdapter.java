package com.siziksu.ith.ui.main;

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

import butterknife.BindView;
import butterknife.ButterKnife;

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

    final class LocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.list_string_item_view)
        View itemView;
        @BindView(R.id.list_string_item_text)
        TextView itemText;

        LocalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
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
