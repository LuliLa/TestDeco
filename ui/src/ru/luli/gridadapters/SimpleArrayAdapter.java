package ru.luli.gridadapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import ru.luli.db.schema.objects.DtbClasses;
import ru.luli.db.schema.objects.DtbTable;
import ru.luli.db.schema.objects.DtbUsers;
import ru.luli.decompiler.utils.UiUtils;

import java.util.List;

public class SimpleArrayAdapter<T extends DtbTable> extends AbstractArrayAdapter {
    private List<T> data;
    private Context context;

    public SimpleArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        this.data = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView result;
        if (convertView == null) {
            T bean = data.get(position);
            result = new TextView(context);
            String label = "";
            if (bean instanceof DtbUsers) {
                label = ((DtbUsers) bean).getCsFio();
            }else if (bean instanceof DtbClasses) {
                label = ((DtbClasses) bean).getCsName();
            }
            result.setText(label);
            result.setMinimumWidth(UiUtils.getWidth(70));
            result.setTextSize(35);
            result.setTextColor(Color.BLACK);
        } else {
            result = (TextView) convertView;
        }

        return result;
    }


    @Override
    public void removeRow(int ciId) {

    }

    public T getObject(int position) {
        return data.get(position);
    }
}
