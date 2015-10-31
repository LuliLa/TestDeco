package ru.luli.compiler.api;

import java.util.List;

public class SimpleQuestion extends TestQuestion {
    private static final long serialVersionUID = 1835820599260474428L;

    private String questionText;

    private List<SimpleAnswer> answers;

    private String pathToImage;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<SimpleAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SimpleAnswer> answers) {
        this.answers = answers;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
