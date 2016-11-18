package com.siziksu.ith.ui.manager;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class ToolbarManager {

    private ActionBar bar;

    public ToolbarManager(Activity activity) {
        bar = ((AppCompatActivity) activity).getSupportActionBar();
    }

    public void set(boolean up, boolean icon) {
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(up);
            bar.setDisplayShowHomeEnabled(icon);
        }
    }
}
