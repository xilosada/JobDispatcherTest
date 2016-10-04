package com.xilosada.jobdispatcher.base;

import android.app.Application;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Trigger;
import com.xilosada.jobdispatcher.ClickSynchronizationService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by xabierlosada on 03/10/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        configureRealm();
        setUpServerSynchronization();
    }

    public void setUpServerSynchronization() {
        FirebaseJobDispatcher fbDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job scheduledJob = fbDispatcher.newJobBuilder()
                .setService(ClickSynchronizationService.class)
                .setTrigger(Trigger.NOW)
                .setTag("The Tag")
                .build();
        fbDispatcher.schedule(scheduledJob);
    }

    private void configureRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("testDb")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
