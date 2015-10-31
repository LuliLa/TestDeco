package ru.luli.admin;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ru.luli.TestDecompiler.R;
import ru.luli.db.schema.lobjects.LDtbUsers;
import ru.luli.db.schema.objects.DtbUsers;
import ru.luli.decompiler.utils.DBUtils;
import ru.luli.decompiler.utils.LogUtils;
import ru.luli.equipment.IEditorParameter;

public class PersonEditor extends Activity implements IEditorParameter {
    private int classId;
    private int personId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personeditor);

        if (savedInstanceState != null) {
            classId = savedInstanceState.getInt(CLASS_ID_PARAM);
            if ((personId = savedInstanceState.getInt(PERSON_ID_PARAM)) >= 0) {
                loadPersonInfo(personId, classId);
            } else {
                loadClassInfo(classId);
            }

        }
    }

    private void loadClassInfo(int classId) {
        ((TextView)findViewById(R.id.ci_class_id)).setText(classId);
    }

    private void loadPersonInfo(int personId, int classId) {
        LDtbUsers usersList = new LDtbUsers();
        DtbUsers user = usersList.fetchByUserId(personId);
        if (user==null) {
            LogUtils.processError("Person is NULL!", this.getClass());
            loadClassInfo(classId);
            return;
        }
        ((TextView)findViewById(R.id.ci_id)).setText(personId);
        EditText view = ((EditText)findViewById(R.id.cs_fio));
        view.setText(user.getCsFio());
        view = ((EditText)findViewById(R.id.cs_login));
        view.setText(user.getCsLogin());
        view = ((EditText)findViewById(R.id.cs_pass));
        view.setText(user.getCsPass());
    }


    public void onSaveClick(View view) {
        String fio = ((EditText)findViewById(R.id.cs_fio)).getText().toString();
        String login = ((EditText)findViewById(R.id.cs_login)).getText().toString();
        String pass = ((EditText)findViewById(R.id.cs_pass)).getText().toString();
        if (personId>=0) {
            ContentValues vals = new ContentValues();
            vals.put(DtbUsers.CS_LOGIN, login);
            vals.put(DtbUsers.CS_FIO, fio);
            vals.put(DtbUsers.CS_PASS, pass);
            DBUtils.update(vals, DtbUsers.TABLE_NAME, DtbUsers.CI_ID + "=" + personId);
        } else {
            DBUtils.insertUser(login, pass, fio, classId);
        }
        finish();
    }

    public void OnCancelClick(View view) {
        personId = -1;
        classId = -1;
        finish();
    }
}
