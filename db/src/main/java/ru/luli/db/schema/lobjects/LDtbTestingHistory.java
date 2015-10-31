package ru.luli.db.schema.lobjects;

import ru.luli.db.schema.objects.DtbTestingHistory;

import java.util.List;

public class LDtbTestingHistory extends LDbTable<DtbTestingHistory> {

    protected List<DtbTestingHistory> fetch(String whereCause) {
        return fetch(DtbTestingHistory.class, whereCause);
    }

    public List<DtbTestingHistory> fetchByUserId(int userId) {
        return fetch(DtbTestingHistory.class, DtbTestingHistory.CI_USER_ID + "=" + userId);
    }

    @Override
    public String getTableName() {
        return DtbTestingHistory.TABLE_NAME;
    }
}
