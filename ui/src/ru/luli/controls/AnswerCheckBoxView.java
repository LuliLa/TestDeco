package ru.luli.controls;

import android.content.Context;
import android.widget.CheckBox;

public class AnswerCheckBoxView extends CheckBox {
    private boolean isValid;

    public AnswerCheckBoxView(Context context) {
        super(context);
    }

    public AnswerCheckBoxView(Context c, String text, boolean isValid) {
        super(c);
        this.isValid = isValid;
        this.setText(text);
    }

    public boolean isValueBean() {
        return isValid;
    }
}
