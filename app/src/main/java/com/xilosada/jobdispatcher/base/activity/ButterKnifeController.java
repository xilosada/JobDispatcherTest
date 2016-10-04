package com.xilosada.jobdispatcher.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.soundcloud.lightcycle.DefaultActivityLightCycle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xabierlosada on 03/10/16.
 */

public class ButterKnifeController extends DefaultActivityLightCycle<AppCompatActivity> {

    private Unbinder unbinder;

    @Inject
    public ButterKnifeController() {}

    @Override
    public void onCreate(AppCompatActivity activity, Bundle bundle) {
        super.onCreate(activity, bundle);
        unbinder = ButterKnife.bind(activity);
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        super.onDestroy(activity);
        unbinder.unbind();
    }

}
