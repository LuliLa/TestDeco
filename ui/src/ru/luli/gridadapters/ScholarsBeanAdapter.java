package ru.luli.gridadapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.luli.TestDecompiler.R;
import ru.luli.admin.PersonEditor;
import ru.luli.admin.ScheduleEditor;
import ru.luli.db.schema.objects.DtbUsers;
import ru.luli.decompiler.utils.DBUtils;
import ru.luli.decompiler.utils.UiUtils;
import ru.luli.listeners.OnGridItemClassClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScholarsBeanAdapter extends AbstractArrayAdapter<DtbUsers>{

    private List<DtbUsers> data;
    private Context context;

    public ScholarsBeanAdapter(Context context, int resource, List<DtbUsers> objects) {
        super(context, resource, objects);
        this.data= objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout result;
        if (convertView == null) {
            result = new LinearLayout(context);
            ImageView personPng = new ImageView(context);
            personPng.setLayoutParams(new GridView.LayoutParams(85, 85));
            personPng.setScaleType(ImageView.ScaleType.CENTER_CROP);
            personPng.setPadding(8, 8, 8, 8);
            personPng.setImageResource(R.drawable.woman139);
            ImageButton sheduleButton = new ImageButton(context);
            sheduleButton.setImageResource(R.drawable.cal);

            ImageButton removeButton = new ImageButton(context);
            removeButton.setImageResource(R.drawable.delete);

            DtbUsers bean = data.get(position);
            Map<String, Integer> params = getParamsForActions(bean);
            sheduleButton.setOnClickListener(new OnGridItemClassClickListener(context, 1, ScheduleEditor.class, params));
            removeButton.setOnClickListener(new OnGridItemClassClickListener<DtbUsers>(context, 2, null, params));
            result.setOnClickListener(new OnGridItemClassClickListener<DtbUsers>(context, 0, PersonEditor.class, params));

            TextView label = new TextView(context);
            label.setText(bean.getCsFio());
            label.setMinimumWidth(UiUtils.getWidth(70));
            label.setTextSize(35);
            label.setTextColor(Color.BLACK);

            result.addView(personPng);
            result.addView(label);
            result.addView(sheduleButton);
            result.addView(removeButton);
        } else {
            result = (LinearLayout) convertView;
        }

        return result;
    }

    @Override
    public void removeRow(int ciId) {
        for (DtbUsers cl : data) {
            if (cl.getCiId()==ciId) {
                remove(cl);
                DBUtils.delete(cl.getTableName(), "ci_id=" + cl.getCiId());
                notifyDataSetChanged();
                break;
            }
        }
    }

    private Map<String, Integer> getParamsForActions(DtbUsers cl) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("ci_id", cl.getCiId());
        params.put("person_id", cl.getCiId());
        params.put("class_id", cl.getCiClassId());
        return params;
    }
}
