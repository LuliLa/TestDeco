package ru.luli.gridadapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class AbstractArrayAdapter<T> extends ArrayAdapter {

    public AbstractArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    public abstract void removeRow(int ciId);
}
