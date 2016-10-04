package com.xilosada.jobdispatcher.base.activity;

import dagger.Component;

/**
 * Created by xabierlosada on 04/10/16.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity target);
    BaseActivity activity();
}
