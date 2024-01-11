package com.senerunosoft.faturalarm.core;

import android.widget.EditText;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ViewFunc {

    public static float getFloatView(EditText text) {
        if (text.getText().toString().isEmpty()) {
            text.setHint("0");
            return 0;
        } else {
            return Float.parseFloat(text.getText().toString());
        }
    }
    public static float getFloatView(TextInputLayout inputLayout) {
        if (inputLayout.getEditText() != null) {
            if (inputLayout.getEditText().getText().toString().isEmpty()) {
                inputLayout.getEditText().setHint("0");
                return 0;
            } else {
                return Float.parseFloat(inputLayout.getEditText().getText().toString());
            }
        } else {
            return 0;
        }
    }
}
