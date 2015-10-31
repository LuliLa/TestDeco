package ru.luli.scholar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import ru.luli.TestDecompiler.R;
import ru.luli.controls.ScheduleLayoutView;
import ru.luli.controls.StatsLayoutView;
import ru.luli.db.schema.lobjects.LDtbSchedules;
import ru.luli.db.schema.lobjects.LDtbTestingHistory;
import ru.luli.db.schema.objects.DtbSchedules;
import ru.luli.db.schema.objects.DtbTestingHistory;
import ru.luli.decompiler.utils.LogUtils;
import ru.luli.equipment.IEditorParameter;
import ru.luli.equipment.SessionEquipment;

import java.util.List;

public class TestInfo extends Activity implements IEditorParameter{

    private SessionEquipment equip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_info);
        if (savedInstanceState!=null) {
            equip = (SessionEquipment) savedInstanceState.getSerializable(S_EQ);
            initTabs(savedInstanceState.getInt(TAB_INDEX_PARAM));
        }
        if (equip==null) {
            LogUtils.processError("session is null!", this.getClass());
        }
    }

    private void initTabs(int defaultTabIndex) {
        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");
        spec.setContent(R.id.planned_tests);
        spec.setIndicator(getString(R.string.shedules));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.testing_history);
        spec.setIndicator(getString(R.string.statistics));
        tabs.addTab(spec);

        tabs.setCurrentTab(defaultTabIndex);
        refreshTabContent(defaultTabIndex);
    }

    private void refreshTabContent(int tabIndex) {
        switch (tabIndex) {
            case 0: {
                refreshPlannedTests();
                break;
            }
            case 1: {
                refreshStats();
                break;
            }

        }
    }

    private void refreshPlannedTests() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.planned_tests);
        ll.removeAllViewsInLayout();
        LDtbSchedules fetcher = new LDtbSchedules();
        List<DtbSchedules> schedules = fetcher.fetchByUserId(equip.getActiveUserId());
        if (schedules.isEmpty()) {
            TextView label = new TextView(getApplicationContext());
            label.setText(R.string.no_planned_test_notif);
            ll.addView(new TextView(getApplicationContext()));
        } else {
            for (DtbSchedules sh : schedules) {
                ScheduleLayoutView vl = new ScheduleLayoutView(getApplicationContext());
                vl.init(sh);
                ll.addView(vl);
            }
        }
    }

    private void refreshStats() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.planned_tests);
        ll.removeAllViewsInLayout();
        LDtbTestingHistory fetcher = new LDtbTestingHistory();
        List<DtbTestingHistory> schedules = fetcher.fetchByUserId(equip.getActiveUserId());
        if (schedules.isEmpty()) {
            TextView label = new TextView(getApplicationContext());
            label.setText(R.string.no_stats_notif);
            ll.addView(new TextView(getApplicationContext()));
        } else {
            for (DtbTestingHistory sh : schedules) {
                StatsLayoutView vl = new StatsLayoutView(getApplicationContext());
                vl.init(sh);
                ll.addView(vl);
            }
        }
    }
}
