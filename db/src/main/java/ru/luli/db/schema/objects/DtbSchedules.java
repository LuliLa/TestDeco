package ru.luli.db.schema.objects;

import ru.luli.db.schema.annotates.TableColumn;

import java.util.Date;

public class DtbSchedules extends DtbTable{
    public static final String TABLE_NAME = "dtb_schedules";
    public static final String CI_ID = "ci_id";
    public static final String CI_TEST_ID = "ci_test_id";
    public static final String CI_USER_ID = "ci_user_id";
    public static final String CS_STATUS = "cs_status";
    public static final String CDT_PLANNED_DATE = "cdt_planned_date";

    @TableColumn(name="ci_id")
    private int ciId;
    @TableColumn(name="ci_test_id")
    private int ciTestId;
    @TableColumn(name="ci_user_id")
    private int ciUserId;
    @TableColumn(name="cs_status")
    private String status;
    @TableColumn(name="cdt_planned_date")
    private Date cdtPlannedDate;


    public void setCdtPlannedDate(Date cdtPlannedDate) {
        this.cdtPlannedDate = cdtPlannedDate;
    }

    public Date getCdtPlannedDate() {
        return cdtPlannedDate;
    }

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

    public int getCiTestId() {
        return ciTestId;
    }

    public void setCiTestId(int ciTestId) {
        this.ciTestId = ciTestId;
    }

    public int getCiUserId() {
        return ciUserId;
    }

    public void setCiUserId(int ciUserId) {
        this.ciUserId = ciUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
