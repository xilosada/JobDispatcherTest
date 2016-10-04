package com.xilosada.jobdispatcher;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by xabierlosada on 03/10/16.
 */

public class ClickAdapter extends RealmBaseAdapter<Click> implements ListAdapter {

    private static class ViewHolder {
        TextView timestamp;
        CheckBox isSynchronized;
    }

    public ClickAdapter(Context context, OrderedRealmCollection<Click> realmResults) {
        super(context, realmResults);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.click_model_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.timestamp_text_view);
            viewHolder.isSynchronized = (CheckBox) convertView.findViewById(R.id.synchronized_checkbox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Click item = adapterData.get(position);
        viewHolder.timestamp.setText(String.valueOf(item.getDatetime()));
        viewHolder.isSynchronized.setChecked(item.isSynchronized());
        return convertView;
    }
}