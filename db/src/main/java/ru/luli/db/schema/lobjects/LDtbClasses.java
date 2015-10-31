package ru.luli.db.schema.lobjects;

import ru.luli.db.schema.objects.DtbClasses;

import java.util.List;

public class LDtbClasses extends LDbTable<DtbClasses> {
    public List<DtbClasses> fetch(String whereCause) {
        return fetch(DtbClasses.class, whereCause);
    }

    @Override
    public String getTableName() {
        return DtbClasses.TABLE_NAME;
    }
}
