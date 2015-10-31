package ru.luli.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import ru.luli.TestDecompiler.R;
import ru.luli.db.schema.lobjects.LDtbClasses;
import ru.luli.db.schema.objects.DtbClasses;
import ru.luli.decompiler.utils.BasicUtils;
import ru.luli.decompiler.utils.DBUtils;
import ru.luli.gridadapters.ClassesBeanAdapter;

import java.util.List;

public class ClassesEditor extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_classes);
        fillClassesGrid();
    }

    private void fillClassesGrid() {
        LDtbClasses classesFetcher = new LDtbClasses();
        List<DtbClasses> classes = classesFetcher.fetch("");
        GridView grid = (GridView) findViewById(R.id.grid);
        grid.removeAllViewsInLayout();
        grid.setAdapter(new ClassesBeanAdapter(getApplicationContext(), R.id.grid, classes));
    }

    public void onAddClick(View view) {
        EditText classNameControl = (EditText) findViewById(R.id.new_class_name);
        String value = classNameControl.getText().toString();
        if (BasicUtils.isNotBlank(value)) {
            DBUtils.insertClass(value);
            classNameControl.setText("");
            fillClassesGrid();
        }

    }
}
