package ru.luli.compiler.api;

public class TwoWaysAnswer extends TestAnswer {
    private static final long serialVersionUID = -6656300771121999541L;

    private String answerText;

    private String pathToImage;

    public TwoWaysAnswer(String answerText, String pathToImage) {
        this.answerText = answerText;
        this.pathToImage = pathToImage;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
