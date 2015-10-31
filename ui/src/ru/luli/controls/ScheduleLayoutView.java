package ru.luli.controls;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import ru.luli.TestDecompiler.R;
import ru.luli.db.schema.lobjects.LdtbTest;
import ru.luli.db.schema.objects.DtbSchedules;
import ru.luli.db.schema.objects.DtbTests;

public class ScheduleLayoutView extends LinearLayout {

    public ScheduleLayoutView(Context context) {
        super(context);
    }

    public void init(DtbSchedules scheduleBean) {
        setOrientation(HORIZONTAL);
        TextView testName = new TextView(getContext());
        testName.setTextSize(20);
        String testInfo = "";
        DtbTests testBean = new LdtbTest().fetchByTestId(scheduleBean.getCiTestId());
        testInfo+=testBean.getCsName() + ' ';
        testInfo += " Пройти до: ";
        testInfo+= DateUtils.formatDateTime(getContext(), scheduleBean.getCdtPlannedDate().getTime(), DateUtils.LENGTH_SHORT);
        testName.setText(testInfo);
        Button but = new Button(getContext());
        but.setText(R.string.run_test);
        addView(testName);
        addView(but);
    }

}
