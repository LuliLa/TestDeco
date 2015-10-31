package ru.luli.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import ru.luli.TestDecompiler.R;
import ru.luli.db.schema.lobjects.LDtbUsers;
import ru.luli.db.schema.objects.DtbUsers;
import ru.luli.equipment.IEditorParameter;
import ru.luli.gridadapters.ScholarsBeanAdapter;

import java.util.List;

public class ScholarsEditor extends Activity implements IEditorParameter {
    private int classId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scholarseditor);

        classId = getIntent().getIntExtra("class_id", -1);
        initControls(classId);
    }

    private void initControls(int classId) {
        List<DtbUsers> data = new LDtbUsers().fetchByClassId(classId);
        GridView grid = (GridView) findViewById(R.id.persons_grid);
        grid.setAdapter(new ScholarsBeanAdapter(getBaseContext(), R.id.persons_grid, data));
    }

    public void onAddPupil(View view) {
        Intent intent = new Intent(getBaseContext(), PersonEditor.class);
        intent.putExtra(CLASS_ID_PARAM, classId);
        startActivity(intent);
    }
}
