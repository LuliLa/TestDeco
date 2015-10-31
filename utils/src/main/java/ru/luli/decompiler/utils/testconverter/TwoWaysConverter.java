package ru.luli.decompiler.utils.testconverter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import ru.luli.compiler.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TwoWaysConverter implements IConverter{
    private Context context;
    public TwoWaysConverter(Context context) {
        this.context = context;
    }

    @Override
    public List<TwoWaysAnswer> getAllAnswers(TestQuestion question) {
        TwoWaysQuestion q = (TwoWaysQuestion) question;
        return new ArrayList<TwoWaysAnswer>(q.getAnswers().values());
    }

    @Override
    public List<? extends TestQuestion> getAllQuestions(TestMaquette mq) {
        return new ArrayList<TwoWaysQuestion>((Collection<TwoWaysQuestion>) mq.getQuestions());
    }

    @Override
    public View convertQuestion(TestQuestion quest) {
        LinearLayout ll = new LinearLayout(context);

        return null;
    }

    @Override
    public View convertAnswer(TestAnswer answer) {
        return null;
    }

    @Override
    public boolean accept(TestMaquette mq) {
        return false;
    }
}
