package com.siziksu.ith.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siziksu.ith.R;
import com.siziksu.ith.ui.activity.IMainActivity;
import com.siziksu.ith.ui.activity.MainActivity;
import com.siziksu.ith.ui.adapter.StringAdapter;
import com.siziksu.ith.ui.manager.ToolbarManager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    @BindView(R.id.mainList)
    RecyclerView mainList;

    private IMainActivity parent;

    public MainFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        parent = (MainActivity) getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new ToolbarManager(getActivity()).set(false, false);
        final List<String> items = Arrays.asList(getResources().getStringArray(R.array.main_items));
        final StringAdapter adapter = new StringAdapter(getActivity(), (v, position) -> parent.onItemClick(position));
        adapter.showItems(items);
        mainList.setAdapter(adapter);
        mainList.setHasFixedSize(true);
        mainList.setLayoutManager(adapter.getLayoutManager());
    }
}
