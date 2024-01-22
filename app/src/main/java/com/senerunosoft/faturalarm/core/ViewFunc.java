package com.senerunosoft.faturalarm.core;

import android.widget.EditText;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

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

    public static String setEditText(float value) {
        if (value <= 0) {
            return "";
        } else {
            return String.format(Locale.US,"%.3f", value);
        }
    }    public static String setEditTextWithTl(float value) {
        if (value <= 0) {
            return "";
        } else {
            return String.format(Locale.US,"%.3f ₺", value);
        }
    }

    public static void MyLog(Object o) {
        System.out.println("CustomLog: " + o.toString());
    }
}
