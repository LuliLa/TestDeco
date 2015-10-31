package ru.luli.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import ru.luli.db.schema.objects.DtbTable;
import ru.luli.decompiler.utils.LogUtils;
import ru.luli.gridadapters.AbstractArrayAdapter;

import java.util.Map;

public class OnGridItemClassClickListener<T extends DtbTable> implements View.OnClickListener {
    public static final int ALL_ITEM_POSITION = 0;
    public static final int SHEDULER_ITEM_POSITION = 1;
    public static final int REMOVE_ITEM_POSITION = 2;

    private Context context;
    private int position = -1;
    private Class editor;
    private Map<String, Integer> params;

    public OnGridItemClassClickListener(Context context, int positionInLayot, Class editorClass, Map<String, Integer> params) {
        this.context = context;
        this.position = positionInLayot;
        this.editor = editorClass;
        this.params = params;
    }

    @Override
    public void onClick(View v) {
        switch (position) {
            case ALL_ITEM_POSITION: {
                Intent intent = new Intent(context, editor);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                for (String key : params.keySet()) {
                    intent.putExtra(key, params.get(key));
                }
                context.startActivity(intent);
                break;
            }
            case SHEDULER_ITEM_POSITION: {
                Intent intent = new Intent(context, editor);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                for (String key : params.keySet()) {
                    intent.putExtra(key, params.get(key));
                }
                context.startActivity(intent);
                break;
            }
            case REMOVE_ITEM_POSITION : {
                ((AbstractArrayAdapter)((GridView) v.getParent().getParent()).getAdapter()).removeRow(params.get("ci_id"));
                break;
            }
            default: {
                LogUtils.processError("can't find action code!", this.getClass());
            }
        }
    }
}
