package ru.luli.db.schema.lobjects;

import ru.luli.db.schema.objects.DtbUsers;

import java.util.List;

public class LDtbUsers extends LDbTable<DtbUsers> {

    protected List<DtbUsers> fetch(String whereCause) {
        return fetch(DtbUsers.class, whereCause);
    }

    public List<DtbUsers> fetchByClassId (int classId) {
        return fetch(DtbUsers.CI_CLASS_ID + "=" + classId);
    }

    public DtbUsers fetchByUserId (int userId) {
        List<DtbUsers> dtbUserses = fetch(DtbUsers.CI_ID + "=" + userId);
        if (dtbUserses!=null && !dtbUserses.isEmpty()) {
            return dtbUserses.get(0);
        }
        return null;
    }

    @Override
    public String getTableName() {
        return DtbUsers.TABLE_NAME;
    }
}
