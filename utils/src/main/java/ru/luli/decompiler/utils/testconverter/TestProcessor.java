package ru.luli.decompiler.utils.testconverter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import ru.luli.compiler.api.TestMaquette;
import ru.luli.compiler.api.TestQuestion;
import ru.luli.controls.AnswerCheckBoxView;
import ru.luli.controls.SimpleChartView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestProcessor implements ITestProcessor {
    private static final Random LOTTERY = new Random();

    private final List<IConverter> converterList = new ArrayList<IConverter>();
    private IConverter converter;

    private List<? extends TestQuestion> questions;
    private TestQuestion currentQuestion;
    private TestInfo testInfo;

    private static ITestProcessor processor;
    private Context context;

    public static ITestProcessor getInstance(Context c) {
        if (processor==null) {
            processor = new TestProcessor(c);
        }
        return processor;
    }

    public TestProcessor(Context context) {
        this.context = context;
        if (converterList.isEmpty()) {
            converterList.add(new BasicConverter(context));
            converterList.add(new TwoWaysConverter(context));
        }
    }

    private IConverter getConverterOnQuestion(TestMaquette mq) {
        for (IConverter candidat : converterList) {
            if (candidat.accept(mq)) {
                return candidat;
            }
        }
        return null;
    }

    @Override
    public void init(TestMaquette mq) {
        converter = getConverterOnQuestion(mq);
        questions = converter.getAllQuestions(mq);
    }

    @Override
    public View getNextQuestion() {
        LOTTERY.setSeed(0);
        int nextQuestPos = LOTTERY.nextInt(questions.size());
        currentQuestion = questions.get(nextQuestPos);
        questions.remove(nextQuestPos);

        return converter.convertQuestion(currentQuestion);
    }

    @Override
    public boolean checkUserAsnwer(View v) {
        LinearLayout ll  = (LinearLayout) v;
        LinearLayout answersLa = (LinearLayout) ll.getChildAt(1);
        for (int i=0; i< answersLa.getChildCount();i++) {
            AnswerCheckBoxView cb = (AnswerCheckBoxView) answersLa.getChildAt(i);
            if (cb.isChecked()) {
                return !cb.isValueBean();
            }
        }
        return false;
    }

    @Override
    public boolean hasNextQuestion() {
        return questions.size()>0;
    }

    @Override
    public ITestInfo getTestInfo() {
        return testInfo;
    }

    @Override
    public TestQuestion getCurrentQuestion() {
        return currentQuestion;
    }

    @Override
    public View getResultsView(Map<TestQuestion, Boolean> testResults) {
        SimpleChartView chartView = new SimpleChartView(context);
        int validAnswers = 0;
        for (Boolean b : testResults.values()) {
            if (b) {
                validAnswers++;
            }
        }
        chartView.setParams(testResults.size(), validAnswers);
        return chartView;
    }
}
