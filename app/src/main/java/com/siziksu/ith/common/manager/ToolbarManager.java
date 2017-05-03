package com.siziksu.ith.common.manager;

import android.support.v7.app.ActionBar;

public final class ToolbarManager {

    public static void initActionBar(ActionBar actionBar, boolean up, boolean icon) {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(up);
            actionBar.setDisplayShowHomeEnabled(icon);
        }
    }
}
