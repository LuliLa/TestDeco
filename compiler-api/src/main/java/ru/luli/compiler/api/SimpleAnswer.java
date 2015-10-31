package ru.luli.compiler.api;

public class SimpleAnswer extends TestAnswer {
    private static final long serialVersionUID = 1181296045472286897L;

    private String answerText;

    private boolean isRight;

    private String pathToImage;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean isRight) {
        this.isRight = isRight;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
