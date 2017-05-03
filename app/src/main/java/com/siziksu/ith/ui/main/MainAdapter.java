package com.siziksu.ith.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siziksu.ith.R;
import com.siziksu.ith.common.helper.IthAdapter;
import com.siziksu.ith.common.helper.IthViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Simple RecyclerView.Adapter that implements {@link IthAdapter} to respond to move and
 * dismiss events from a {@link android.support.v7.widget.helper.ItemTouchHelper}.
 */
public final class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IthAdapter {

    private final List<String> items;
    private final Context context;
    private final OnStartDragListener dragStartListener;

    public MainAdapter(Context context, OnStartDragListener dragStartListener) {
        this.dragStartListener = dragStartListener;
        this.context = context;
        this.items = new ArrayList<>();
        items.addAll(Arrays.asList(context.getResources().getStringArray(R.array.mocked_items)));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).textView.setText(items.get(position));
        // Start a drag whenever the handle view it touched
        ((ItemViewHolder) holder).handleView.setOnTouchListener((v, event) -> {
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                dragStartListener.onStartDrag(holder);
            }
            return false;
        });
    }

    @Override
    public void onItemDismiss(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder implements IthViewHolder {

        final TextView textView;
        final ImageView handleView;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            handleView = (ImageView) itemView.findViewById(R.id.item_handler);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    /**
     * Listener for manual initiation of a drag.
     */
    public interface OnStartDragListener {

        /**
         * Called when a view is requesting a start of a drag.
         *
         * @param viewHolder The holder of the view to drag.
         */
        void onStartDrag(RecyclerView.ViewHolder viewHolder);
    }
}
