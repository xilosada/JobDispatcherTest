package com.xilosada.jobdispatcher.base.activity;

import android.os.Bundle;

import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleAppCompatActivity;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by xabierlosada on 03/10/16.
 */
public abstract class BaseActivity extends LightCycleAppCompatActivity<BaseActivity> {

    @Inject @LightCycle ButterKnifeController butterKnifeController;

    @Inject @LightCycle RealmController realmController;

    public Realm getRealm() {
        return realmController.getRealm();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
        super.onCreate(savedInstanceState);
    }
}
