package com.siziksu.ith.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.siziksu.ith.R;
import com.siziksu.ith.common.Constants;
import com.siziksu.ith.common.manager.ContentManager;
import com.siziksu.ith.ui.fragment.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity implements IMainActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ContentManager contentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        contentManager = new ContentManager(getSupportFragmentManager());
        if (savedInstanceState == null) {
            contentManager.show(R.id.main_content, new MainFragment(), Constants.FRAGMENT_MAIN);
        }
    }

    @Override
    public void onBackPressed() {
        switch (contentManager.getSection()) {
            case Constants.FRAGMENT_MAIN:
                super.onBackPressed();
                break;
            default:
                contentManager.show(R.id.main_content, new MainFragment(), Constants.FRAGMENT_MAIN);
                break;
        }
    }

    @Override
    public ContentManager getContentManager() {
        return contentManager;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
