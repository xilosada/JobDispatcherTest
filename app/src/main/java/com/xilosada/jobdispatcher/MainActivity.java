package com.xilosada.jobdispatcher;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.xilosada.jobdispatcher.base.App;
import com.xilosada.jobdispatcher.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity {

    @BindView(R.id.click_list) ListView listView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void setActivityContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        RealmResults<Click> clicks = getRealm().where(Click.class).findAll();

        final ClickAdapter adapter = new ClickAdapter(this, clicks);

        listView.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View view) {
        getRealm().executeTransactionAsync(realm -> {
            Click click = new Click();
            click.setDatetime(System.currentTimeMillis());
            click.setSynchronized(false);
            realm.copyToRealm(click);
        });
        ((App) this.getApplication()).setUpServerSynchronization();
        Snackbar.make(view, "Click Added", Snackbar.LENGTH_LONG)
                .show();
    }
}
