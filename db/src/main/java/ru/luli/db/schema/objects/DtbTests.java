package ru.luli.db.schema.objects;

import ru.luli.db.schema.annotates.TableColumn;

public class DtbTests extends DtbTable{
    public static final String TABLE_NAME = "dtb_tests";
    public static final String CI_ID = "ci_id";
    public static final String CS_NAME = "cs_name";
    public static final String CS_PATH = "cs_path";

    @TableColumn(name="ci_id")
    private int ciId;
    @TableColumn(name="cs_name")
    private String csName;
    @TableColumn(name="cs_path")
    private String csPath;

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public int getCiId() {
        return ciId;
    }

    public void setCiId(int ciId) {
        this.ciId = ciId;
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public String getCsPath() {
        return csPath;
    }

    public void setCsPath(String csPath) {
        this.csPath = csPath;
    }
}
