package ru.luli.TestDecompiler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import ru.luli.admin.ClassesEditor;
import ru.luli.controls.MovableView;
import ru.luli.db.schema.lobjects.LDtbClasses;
import ru.luli.db.schema.lobjects.LDtbUsers;
import ru.luli.db.schema.objects.DtbClasses;
import ru.luli.db.schema.objects.DtbUsers;
import ru.luli.decompiler.utils.BasicUtils;
import ru.luli.decompiler.utils.DBUtils;
import ru.luli.decompiler.utils.UiUtils;
import ru.luli.decompiler.utils.UserUtils;
import ru.luli.equipment.IEditorParameter;
import ru.luli.equipment.SessionEquipment;
import ru.luli.gridadapters.SimpleArrayAdapter;
import ru.luli.scholar.TestAllWorld;
import ru.luli.scholar.TestInfo;


public class TestDecompiler extends Activity implements IEditorParameter{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DBUtils.initDb(getBaseContext());
        UiUtils.setDisplay(this);
        initLoginControl();
    }

    private void initLoginControl() {
        SimpleArrayAdapter<DtbClasses> adapter = new SimpleArrayAdapter<DtbClasses>(this, android.R.layout.simple_spinner_dropdown_item, new LDtbClasses().fetch(""));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.classes_list);
        spinner.setAdapter(adapter);
        spinner.setPrompt(getString(R.string.classes));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DtbClasses cl = (DtbClasses) ((SimpleArrayAdapter) parent.getAdapter()).getObject(position);
                SimpleArrayAdapter<DtbUsers> usersAdap = new SimpleArrayAdapter<DtbUsers>(view.getContext(), android.R.layout.simple_spinner_dropdown_item,
                        new LDtbUsers().fetchByClassId(cl.getCiId()));
                usersAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner spinner = (Spinner) findViewById(R.id.users_list);
                spinner.setAdapter(usersAdap);
                spinner.setPrompt(getString(R.string.scholars));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        customizeMenuItems(menu);
        return true;
    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
    }

    public void onMenuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.export_class_to_xls: {
                break;
            }
            case R.id.export_schl_to_xls: {
                break;
            }
            case R.id.personal_stat: {
                Intent intent = new Intent(this, TestInfo.class);
                intent.putExtra(TAB_INDEX_PARAM, 1);
                intent.putExtra(S_EQ, BasicUtils.getLoginInfo());
                startActivity(intent);
                break;
            }
            case R.id.show_planned_tests: {
                Intent intent = new Intent(this, TestInfo.class);
                intent.putExtra(TAB_INDEX_PARAM, 0);
                intent.putExtra(S_EQ, BasicUtils.getLoginInfo());
                startActivity(intent);
                break;
            }
            case R.id.show_scholars_db: {
                Intent intent = new Intent(this, ClassesEditor.class);
                startActivity(intent);
                break;
            }
            case R.id.show_tests_db: {
                Intent intent = new Intent(this, TestAllWorld.class);
                startActivity(intent);
                break;
            }
            case R.id.login: {
                break;
            }
            case R.id.unlogin: {UserUtils.logoff("");
                break;
            }
            default: {
                //log
                break;
            }
        }
    }

    private void customizeMenuItems(Menu menu) {
        MenuItem personMItem = menu.findItem(R.id.person_pack);
        personMItem.setTitle(getString(R.string.enter_like) + " Admin");
        //menu.setGroupVisible(R.id.admin_menu, UserUtils.isUserLogin("admin"));
        menu.setGroupVisible(R.id.scholars_menu, UserUtils.isAnyScholarsLogin());
        menu.findItem(R.id.unlogin).setVisible(UserUtils.isAnyUserLogin());
    }

    public void onLoginClick(View view) {
        MovableView v = new MovableView(getApplicationContext(), "TEST to TEST", "");
        LinearLayout ll = (LinearLayout) findViewById(R.id.wtf);
        ll.addView(v);
        /*
        Spinner spinner = (Spinner) findViewById(R.id.users_list);
        DtbUsers userToLogin = (DtbUsers) ((SimpleArrayAdapter) spinner.getAdapter()).getObject(spinner.getSelectedItemPosition());
        SessionEquipment sess = new SessionEquipment(getApplicationContext(), userToLogin.getCsLogin(), userToLogin.getCiId());
        BasicUtils.saveLoginInfo(sess);*/
    }
}
