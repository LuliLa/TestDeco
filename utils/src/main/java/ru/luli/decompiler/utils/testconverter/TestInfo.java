package ru.luli.decompiler.utils.testconverter;

public class TestInfo implements ITestInfo {
    private int allAswersCount;
    private int allQuestCount;

    public void setAllAswersCount(int allAswersCount) {
        this.allAswersCount = allAswersCount;
    }

    public void setAllQuestCount(int allQuestCount) {
        this.allQuestCount = allQuestCount;
    }

    @Override
    public int getAllAnswersCount() {
        return allAswersCount;
    }

    @Override
    public int getAllQuestionCount() {
        return allQuestCount;
    }
}
