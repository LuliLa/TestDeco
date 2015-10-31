package ru.luli.db.schema.objects;

import ru.luli.db.schema.annotates.TableColumn;

import java.util.Date;

public class DtbTestingHistory extends DtbTable{
    public static final String TABLE_NAME = "dtb_testing_histrory";
    public static final String CI_ID = "ci_id";
    public static final String CI_TEST_ID = "ci_test_id";
    public static final String CI_USER_ID = "ci_user_id";
    public static final String CD_TEST_RESULT = "cd_test_result";
    public static final String CDT_TESTING_DATE = "cdt_testing_date";

    @TableColumn(name="ci_id")
    private int ciId;
    @TableColumn(name="ci_test_id")
    private int ci_test_id;
    @TableColumn(name="ci_user_id")
    private int ciUserId;
    @TableColumn(name="cd_test_result")
    private double cdTestResult;
    @TableColumn(name="cdt_testing_date")
    private Date cdtTestingDate;


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

    public int getCi_test_id() {
        return ci_test_id;
    }

    public void setCi_test_id(int ci_test_id) {
        this.ci_test_id = ci_test_id;
    }

    public int getCiUserId() {
        return ciUserId;
    }

    public void setCiUserId(int ciUserId) {
        this.ciUserId = ciUserId;
    }

    public double getCdTestResult() {
        return cdTestResult;
    }

    public void setCdTestResult(double cdTestResult) {
        this.cdTestResult = cdTestResult;
    }

    public Date getCdtTestingDate() {
        return cdtTestingDate;
    }

    public void setCdtTestingDate(Date cdtTestingDate) {
        this.cdtTestingDate = cdtTestingDate;
    }
}
