package ru.luli.compiler.api;

import java.io.Serializable;

public class TestAnswer implements Serializable {
    private static final long serialVersionUID = 8108214614115717967L;

    private String testAnswerUid;

    public String getTestAnswerUid() {
        return testAnswerUid;
    }

    public void setTestAnswerUid(String testAnswerUid) {
        this.testAnswerUid = testAnswerUid;
    }
}
