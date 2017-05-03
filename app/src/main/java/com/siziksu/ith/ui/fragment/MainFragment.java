package com.siziksu.ith.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siziksu.ith.R;
import com.siziksu.ith.common.Constants;
import com.siziksu.ith.common.manager.ContentManager;
import com.siziksu.ith.common.manager.ToolbarManager;
import com.siziksu.ith.ui.main.IMainActivity;
import com.siziksu.ith.ui.main.MainActivity;
import com.siziksu.ith.ui.main.StringAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainFragment extends Fragment {

    @BindView(R.id.main_list)
    RecyclerView mainList;

    private static final int LIST = 0;
    private static final int GRID = 1;

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
        ToolbarManager.initActionBar(((AppCompatActivity) getActivity()).getSupportActionBar(), false, false);
        final List<String> items = Arrays.asList(getResources().getStringArray(R.array.main_items));
        final StringAdapter adapter = new StringAdapter(getActivity(), (v, position) -> {
            ContentManager contentManager = parent.getContentManager();
            switch (position) {
                case LIST:
                    contentManager.show(R.id.main_content, new ListFragment(), Constants.FRAGMENT_LIST);
                    break;
                case GRID:
                    contentManager.show(R.id.main_content, new GridFragment(), Constants.FRAGMENT_GRID);
                    break;
            }
        });
        adapter.showItems(items);
        mainList.setAdapter(adapter);
        mainList.setHasFixedSize(true);
        mainList.setLayoutManager(adapter.getLayoutManager());
    }
}
