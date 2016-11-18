package com.siziksu.ith.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siziksu.ith.R;
import com.siziksu.ith.common.helper.IthCallback;
import com.siziksu.ith.ui.adapter.MainAdapter;
import com.siziksu.ith.ui.manager.ToolbarManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    @BindView(R.id.mainList)
    RecyclerView mainList;

    private ItemTouchHelper itemTouchHelper;

    public ListFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new ToolbarManager(getActivity()).set(true, false);
        final MainAdapter adapter = new MainAdapter(getActivity(), viewHolder -> itemTouchHelper.startDrag(viewHolder));
        mainList.setHasFixedSize(true);
        mainList.setAdapter(adapter);
        mainList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper.Callback callback = new IthCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mainList);
    }
}
