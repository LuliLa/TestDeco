package ru.luli.decompiler.utils.testconverter;

import android.view.View;
import ru.luli.compiler.api.TestAnswer;
import ru.luli.compiler.api.TestMaquette;
import ru.luli.compiler.api.TestQuestion;

import java.util.List;

public interface IConverter {
    public List<? extends TestAnswer> getAllAnswers(TestQuestion question);

    public List<? extends TestQuestion> getAllQuestions(TestMaquette mq);

    public View convertQuestion(TestQuestion quest);

    public View convertAnswer(TestAnswer answer);

    public boolean accept(TestMaquette mq);
}
