package ru.luli.controls;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import ru.luli.db.schema.lobjects.LdtbTest;
import ru.luli.db.schema.objects.DtbTestingHistory;
import ru.luli.db.schema.objects.DtbTests;

public class StatsLayoutView extends LinearLayout {
    public StatsLayoutView(Context context) {
        super(context);
    }

    public void init(DtbTestingHistory history) {
        setOrientation(HORIZONTAL);
        TextView testName = new TextView(getContext());
        testName.setTextSize(20);
        String testInfo = "";
        DtbTests testBean = new LdtbTest().fetchByTestId(history.getCi_test_id());
        testInfo+=testBean.getCsName() + ' ';
        testInfo += " Пройден : ";
        testInfo+= DateUtils.formatDateTime(getContext(), history.getCdtTestingDate().getTime(), DateUtils.LENGTH_SHORT);
        testInfo+= " Результат :";
        testInfo+=history.getCdTestResult();
        testName.setText(testInfo);
        addView(testName);
    }
}
