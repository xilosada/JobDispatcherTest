package com.xilosada.jobdispatcher;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by xabierlosada on 03/10/16.
 */
@RealmClass
public class Click implements RealmModel {

    @PrimaryKey
    private long datetime;
    private boolean isSynchronized;

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    public void setSynchronized(boolean aSynchronized) {
        isSynchronized = aSynchronized;
    }
}
