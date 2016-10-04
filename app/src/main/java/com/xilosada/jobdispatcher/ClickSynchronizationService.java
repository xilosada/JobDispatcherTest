package com.xilosada.jobdispatcher;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.xilosada.jobdispatcher.base.logger.Logger;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class ClickSynchronizationService extends JobService {

    private static final long SLEEP_TIME = 3000;

    @Override
    public boolean onStartJob(JobParameters job) {
        Logger.time("Job Service onStartJob() called");

        Bundle extras = job.getExtras();
        assert extras != null;

        sendClicksToServer()
                .subscribeOn(Schedulers.computation())
                .blockingAwait();

        int result = extras.getInt("return");

        return false; // No more work to do
    }

    @SuppressLint("DefaultLocale")
    private Completable sendClicksToServer() {
        return Completable.create(emitter -> {
            Logger.time("Inside create");
            Realm realm = Realm.getDefaultInstance();
            RealmResults<Click> results = realm.where(Click.class).findAll();


            Logger.time("Job executed");

            for(Click click : results) {

                realm.executeTransaction(realm1 -> {
                    click.setSynchronized(true);
                    realm1.copyToRealmOrUpdate(click);
                });

            }

            try {
                Logger.time(String.format("Sleeping %dms", SLEEP_TIME));
                Thread.sleep(SLEEP_TIME);
                Logger.time("Job executed");
                emitter.onComplete();

            } catch (InterruptedException e) {
                Logger.time(String.format("Exception %s", e.getLocalizedMessage()));

                emitter.onError(e);

            } finally {

                Logger.time("Finalized");
                realm.close();
            }
        });
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false; // No more work to do
    }

}