package com.xilosada.jobdispatcher.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.soundcloud.lightcycle.DefaultActivityLightCycle;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by xabierlosada on 03/10/16.
 */
public class RealmController extends DefaultActivityLightCycle<AppCompatActivity> {

    private Realm realm;

    @Inject RealmController() {}

    @Override
    public void onCreate(AppCompatActivity activity, @Nullable Bundle bundle) {
        super.onCreate(activity, bundle);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        super.onDestroy(activity);
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    public Realm getRealm() {
        return realm;
    }
}
