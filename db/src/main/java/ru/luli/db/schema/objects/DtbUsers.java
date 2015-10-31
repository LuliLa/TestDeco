package ru.luli.db.schema.objects;

import ru.luli.db.schema.annotates.TableColumn;

public class DtbUsers extends DtbTable{
    public static final String TABLE_NAME = "dtb_users";
    public static final String CI_ID = "ci_id";
    public static final String CS_FIO = "cs_fio";
    public static final String CS_LOGIN = "cs_login";
    public static final String CS_PASS = "cs_pass";
    public static final String CB_ENABLE_TESTING = "cb_enable_testing";
    public static final String CB_IS_ADMIN = "cd_is_admin";
    public static final String CB_IS_LOGIN = "cd_is_login";
    public static final String CI_CLASS_ID = "ci_class_id";

    @TableColumn(name="ci_id")
    private int ciId;
    @TableColumn(name="cs_fio")
    private String csFio;
    @TableColumn(name="cs_login")
    private String csLogin;
    @TableColumn(name="cs_pass")
    private String csPass;
    @TableColumn(name="cb_enable_testing")
    private boolean cbEnableTesting;
    @TableColumn(name="cd_is_admin")
    private boolean cdIsAdmin;
    @TableColumn(name="cd_is_login")
    private boolean cbIsLogin;
    @TableColumn(name="ci_class_id")
    private int ciClassId;

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public int getCiClassId() {
        return ciClassId;
    }

    public void setCiClassId(int ciClassId) {
        this.ciClassId = ciClassId;
    }

    public int getCiId() {
        return ciId;
    }

    public void setCiId(int ciId) {
        this.ciId = ciId;
    }

    public String getCsFio() {
        return csFio;
    }

    public void setCsFio(String csFio) {
        this.csFio = csFio;
    }

    public String getCsLogin() {
        return csLogin;
    }

    public void setCsLogin(String csLogin) {
        this.csLogin = csLogin;
    }

    public String getCsPass() {
        return csPass;
    }

    public void setCsPass(String csPass) {
        this.csPass = csPass;
    }

    public boolean isCbEnableTesting() {
        return cbEnableTesting;
    }

    public void setCbEnableTesting(boolean cbEnableTesting) {
        this.cbEnableTesting = cbEnableTesting;
    }

    public boolean isCdIsAdmin() {
        return cdIsAdmin;
    }

    public void setCdIsAdmin(boolean cdIsAdmin) {
        this.cdIsAdmin = cdIsAdmin;
    }

    public boolean isCbIsLogin() {
        return cbIsLogin;
    }

    public void setCbIsLogin(boolean cbIsLogin) {
        this.cbIsLogin = cbIsLogin;
    }
}
