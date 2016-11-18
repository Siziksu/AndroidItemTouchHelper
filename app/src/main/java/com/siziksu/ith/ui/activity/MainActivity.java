package com.siziksu.ith.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.siziksu.ith.R;
import com.siziksu.ith.ui.fragment.GridFragment;
import com.siziksu.ith.ui.fragment.ListFragment;
import com.siziksu.ith.ui.fragment.MainFragment;
import com.siziksu.ith.ui.manager.ContentManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private static final int LIST = 0;
    private static final int GRID = 1;
    private static final String FRAGMENT_MAIN = "main";
    private static final String FRAGMENT_LIST = "list";
    private static final String FRAGMENT_GRID = "grid";

    private ContentManager contentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        contentManager = new ContentManager(getSupportFragmentManager());
        if (savedInstanceState == null) {
            contentManager.show(R.id.mainContent, new MainFragment(), FRAGMENT_MAIN, false);
        }
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case LIST:
                contentManager.show(R.id.mainContent, new ListFragment(), FRAGMENT_LIST, true);
                break;
            case GRID:
                contentManager.show(R.id.mainContent, new GridFragment(), FRAGMENT_GRID, true);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
