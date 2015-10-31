package ru.luli.decompiler.utils.testconverter;

import android.view.View;
import ru.luli.compiler.api.TestMaquette;
import ru.luli.compiler.api.TestQuestion;

import java.util.Map;

public interface ITestProcessor {

    public void init(TestMaquette mq);

    public View getNextQuestion();

    public boolean checkUserAsnwer(View view);

    public boolean hasNextQuestion();

    public ITestInfo getTestInfo();

    public TestQuestion getCurrentQuestion();

    public View getResultsView(Map<TestQuestion, Boolean> testResults);
}
