package ru.luli.db.schema.lobjects;

import ru.luli.db.schema.objects.DtbTests;

import java.util.List;

public class LdtbTest extends LDbTable<DtbTests> {

    protected List<DtbTests> fetch(String whereCause) {
        return fetch(DtbTests.class, whereCause);
    }

    public DtbTests fetchByTestId(int testId) {
        List<DtbTests> tsts = fetch(DtbTests.CI_ID + "=" + testId);
        if (tsts!=null && !tsts.isEmpty()) {
            return tsts.get(0);
        }
        return null;
    }
    @Override
    public String getTableName() {
        return DtbTests.TABLE_NAME;
    }
}
