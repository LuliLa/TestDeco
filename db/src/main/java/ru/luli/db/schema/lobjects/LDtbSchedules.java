package ru.luli.db.schema.lobjects;

import ru.luli.db.schema.objects.DtbSchedules;
import java.util.List;

public class LDtbSchedules extends LDbTable<DtbSchedules> {

    public List<DtbSchedules> fetch(String whereCause) {
        return fetch(DtbSchedules.class, whereCause);
    }


    public List<DtbSchedules> fetchByUserId(int userId) {
        return fetch(DtbSchedules.class, DtbSchedules.CI_USER_ID + "=" + userId + " AND " + DtbSchedules.CS_STATUS + " ='NEW'");
    }

    @Override
    public String getTableName() {
        return DtbSchedules.TABLE_NAME;
    }
}
