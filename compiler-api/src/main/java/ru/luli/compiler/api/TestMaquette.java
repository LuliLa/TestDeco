package ru.luli.compiler.api;

import java.io.Serializable;
import java.util.List;

public class TestMaquette implements Serializable{
    private static final long serialVersionUID = 5386866430782805439L;

    private String testName;

    private int testType;

    private boolean isSchuffle;

    private List<TestQuestion> questions;

    private String testUid;

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public String getTestUid() {
        return testUid;
    }

    public void setTestUid(String testUid) {
        this.testUid = testUid;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public boolean isSchuffle() {
        return isSchuffle;
    }

    public void setSchuffle(boolean isSchuffle) {
        this.isSchuffle = isSchuffle;
    }

    public List<? extends TestQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestQuestion> questions) {
        this.questions = questions;
    }
}
