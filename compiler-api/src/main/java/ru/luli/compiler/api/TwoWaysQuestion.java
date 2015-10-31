package ru.luli.compiler.api;

import java.util.Map;

public class TwoWaysQuestion extends TestQuestion {
    private static final long serialVersionUID = -3744021443295037845L;
    private String question;

    private Map<TestVariant, TwoWaysAnswer> questions;

    private String pathToImage;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<TestVariant, TwoWaysAnswer> getAnswers() {
        return questions;
    }

    public void setAnswers(Map<TestVariant, TwoWaysAnswer> questions) {
        this.questions = questions;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
