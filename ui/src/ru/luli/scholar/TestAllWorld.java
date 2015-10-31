package ru.luli.scholar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import ru.luli.TestDecompiler.R;
import ru.luli.compiler.api.TestQuestion;
import ru.luli.controls.MovableView;
import ru.luli.db.schema.lobjects.LdtbTest;
import ru.luli.db.schema.objects.DtbTests;
import ru.luli.decompiler.utils.BasicUtils;
import ru.luli.decompiler.utils.testconverter.ITestProcessor;
import ru.luli.decompiler.utils.testconverter.TestProcessor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestAllWorld extends Activity {
    private LinearLayout testContainer;
    private Map<TestQuestion, Boolean> testResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_all_world);
        //int testId = savedInstanceState.getInt("test_id");
        initControls(0);
        //initControls(testId);


    }

    private void initControls(int testId) {
        //LdtbTest tests = new LdtbTest();
        //DtbTests testBean = tests.fetchByTestId(testId);
        testContainer = (LinearLayout) findViewById(R.id.base_test_ll);
        //TestProcessor.getInstance(getApplicationContext()).init(BasicUtils.getTestMaquette(testBean.getCsPath()));
        String path =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + '/' + "CompiledTests/1445681801026";
        String p = "";
        File f = new File(path);
        if (f.isDirectory()) {
            File[] ff = f.listFiles();
            if (ff.length>0) {
                p = ff[0].getAbsolutePath();
            }
        }
        TestProcessor.getInstance(getApplicationContext()).init(BasicUtils.getTestMaquette(path));
        testResults = new HashMap<TestQuestion, Boolean>();
        renderTestQuestion();
    }

    private void renderTestQuestion() {
        ITestProcessor pro = TestProcessor.getInstance(getApplicationContext());
        boolean hasNextQuest = pro.hasNextQuestion();
        if (hasNextQuest) {
            View questionView = pro.getNextQuestion();
            testContainer.removeViewAt(0);
            testContainer.addView(questionView, 0);
        } else {
            showResults(testResults);
        }
        findViewById(R.id.test_manipulator).setVisibility(hasNextQuest ? View.VISIBLE : View.INVISIBLE);
    }

    private void showResults(Map<TestQuestion, Boolean> testResults) {
        View resultTest = TestProcessor.getInstance(getApplicationContext()).getResultsView(testResults);
        testContainer.removeViewAt(0);
        testContainer.addView(resultTest, 0);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Во время прохождения теста переход назад не возможен", Toast.LENGTH_SHORT).show();
    }

    public void onAnswerDoneClick(View view) {
        ITestProcessor pro = TestProcessor.getInstance(getApplicationContext());
        boolean isRight = pro.checkUserAsnwer(testContainer.getChildAt(0));
        testResults.put(pro.getCurrentQuestion(), isRight);
        renderTestQuestion();
    }
}
