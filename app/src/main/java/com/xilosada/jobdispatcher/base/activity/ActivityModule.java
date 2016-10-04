package com.xilosada.jobdispatcher.base.activity;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by xabierlosada on 04/10/16.
 */
@Module
public class ActivityModule {

    private final BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    public BaseActivity providesBaseActivity() {
        return baseActivity;
    }

    @Provides
    public Realm providesRealm(RealmController realmController) {
        return realmController.getRealm();
    }
}
