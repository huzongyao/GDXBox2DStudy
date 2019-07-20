package com.hzy.gdx.b2d.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.hzy.gdx.b2d.R;

/**
 * Created by huzongyao on 2018/10/7.
 */

public class GameActivity extends AppCompatActivity
        implements AndroidFragmentApplication.Callbacks {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public void exit() {
        finish();
    }
}
