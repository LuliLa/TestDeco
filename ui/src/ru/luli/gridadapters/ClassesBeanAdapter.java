package ru.luli.gridadapters;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.luli.TestDecompiler.R;
import ru.luli.admin.ScheduleEditor;
import ru.luli.admin.ScholarsEditor;
import ru.luli.db.schema.objects.DtbClasses;
import ru.luli.decompiler.utils.DBUtils;
import ru.luli.decompiler.utils.UiUtils;
import ru.luli.listeners.OnGridItemClassClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassesBeanAdapter extends AbstractArrayAdapter<DtbClasses> {
    private Context context;
    private List<DtbClasses> data;

    public ClassesBeanAdapter(Context context, int resource, List<DtbClasses> objects) {
        super(context, resource, objects);
        data = objects;
        this.context = context;
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        super.setNotifyOnChange(notifyOnChange);
    }

    @Override
    public DtbClasses getItem(int position) {
        return data.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout result;
        if (convertView == null) {
            DtbClasses bean = data.get(position);

            result = new LinearLayout(context);
            ImageView sheduleButton = new ImageView(context);
            sheduleButton.setImageResource(R.drawable.cal);
            ImageView removeButton = new ImageView(context);
            removeButton.setImageResource(R.drawable.delete);

            Map<String, Integer> params = getParamsForActions(bean);
            result.setOnClickListener(new OnGridItemClassClickListener<DtbClasses>(context, OnGridItemClassClickListener.ALL_ITEM_POSITION, ScholarsEditor.class, params));
            sheduleButton.setOnClickListener(new OnGridItemClassClickListener<DtbClasses>(context, OnGridItemClassClickListener.SHEDULER_ITEM_POSITION, ScheduleEditor.class, params));
            removeButton.setOnClickListener(new OnGridItemClassClickListener<DtbClasses>(context, OnGridItemClassClickListener.REMOVE_ITEM_POSITION, null, params));

            TextView label = new TextView(context);
            label.setMinimumWidth(UiUtils.getWidth(70));
            label.setTextSize(35);
            label.setTextColor(Color.BLACK);
            label.setText(bean.getCsName());

            result.addView(label);
            result.addView(sheduleButton);
            result.addView(removeButton);
        } else {
            result = (LinearLayout) convertView;
        }

        return result;
    }

    public DtbClasses getObject(int position) {
        return data.get(position);
    }

    @Override
    public void removeRow(int ciId) {
        for (DtbClasses cl : data) {
            if (cl.getCiId()==ciId) {
                remove(cl);
                DBUtils.delete(cl.getTableName(), "ci_id=" + cl.getCiId());
                notifyDataSetChanged();
                break;
            }
        }
    }

    private Map<String, Integer> getParamsForActions(DtbClasses cl) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("ci_id", cl.getCiId());
        params.put("class_id", cl.getCiId());
        return params;
    }
}
