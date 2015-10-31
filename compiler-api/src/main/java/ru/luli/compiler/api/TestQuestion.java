package ru.luli.compiler.api;

import java.io.Serializable;
import java.util.List;

public class TestQuestion implements Serializable {
    private static final long serialVersionUID = -9155003634403616264L;

    private String testQuestionUid;

    public String getTestQuestionUid() {
        return testQuestionUid;
    }

    public void setTestQuestionUid(String testQuestionUid) {
        this.testQuestionUid = testQuestionUid;
    }
}
