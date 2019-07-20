package com.hzy.gdx.b2d.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.hzy.gdx.b2d.game.FirstBodyGame;

/**
 * Created by huzongyao on 2018/10/7.
 */

public class BodyGameFragment extends AndroidFragmentApplication {

    private FirstBodyGame mFirstBodyGame;
    private View mGameView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mFirstBodyGame = new FirstBodyGame();
        mGameView = initializeForView(mFirstBodyGame);
        return mGameView;
    }
}
