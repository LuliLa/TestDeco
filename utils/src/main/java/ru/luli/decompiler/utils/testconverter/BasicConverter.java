package ru.luli.decompiler.utils.testconverter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ru.luli.TestDecompiler.R;
import ru.luli.compiler.api.*;
import ru.luli.controls.AnswerCheckBoxView;
import ru.luli.decompiler.utils.BasicUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BasicConverter implements IConverter {
    private Context context;

    public BasicConverter(Context context) {
        this.context = context;
    }

    @Override
    public List<? extends TestAnswer> getAllAnswers(TestQuestion question) {
        return new ArrayList<SimpleAnswer>(((SimpleQuestion) question).getAnswers());
    }

    @Override
    public List<? extends TestQuestion> getAllQuestions(TestMaquette mq) {
        return new ArrayList<SimpleQuestion>((Collection<SimpleQuestion>) mq.getQuestions());
    }

    @Override
    public View convertQuestion(TestQuestion quest) {
        LinearLayout ll  = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setX(RelativeLayout.LayoutParams.MATCH_PARENT);
        ll.setY(RelativeLayout.LayoutParams.WRAP_CONTENT);
        String questText = ((SimpleQuestion) quest).getQuestionText();
        String pathToImage  = ((SimpleQuestion) quest).getPathToImage();

        TextView textView = createBasicVew(questText, pathToImage, Color.WHITE);
        ll.addView(textView);

        LinearLayout answersLl = new LinearLayout(context);
        answersLl.setOrientation(LinearLayout.VERTICAL);
        List<SimpleAnswer> answersBeans = (List<SimpleAnswer>) getAllAnswers(quest);
        for (SimpleAnswer bean : answersBeans) {
            AnswerCheckBoxView answerView = new AnswerCheckBoxView(context, bean.getAnswerText(), bean.isRight());
            answersLl.addView(answerView);
        }
        ll.addView(answersLl);
        return ll;
    }

    @Override
    public View convertAnswer(TestAnswer answer) {
        String answerText = ((SimpleAnswer) answer).getAnswerText();
        String pathToImage  = ((SimpleAnswer) answer).getPathToImage();
        return createBasicVew(answerText, pathToImage, Color.WHITE);
    }

    private TextView createBasicVew(String text, String pathToImage, int colourText) {
        TextView textView = new TextView(context);
        Drawable imgBack;
        if (BasicUtils.isNotBlank(pathToImage)) {
            imgBack = Drawable.createFromPath(pathToImage);
        } else {
            imgBack = context.getResources().getDrawable(R.drawable.rectangle);
        }

        textView.setTextSize(15);
        textView.setText(text  );
        textView.setBackgroundDrawable(imgBack);

        return textView;
    }

    @Override
    public boolean accept(TestMaquette mq) {
        if (mq.getQuestions()!=null && !mq.getQuestions().isEmpty()) {
            TestQuestion qq = mq.getQuestions().get(0);
            if (qq instanceof SimpleQuestion) {
                return true;
            }
        }
        return false;
    }

}
