package com.sekhar.android.calculator.view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by sekhar on 24-07-2016.
 */
public class ViewEditText extends EditText {

    public ViewEditText(Context context) {
        super(context);
        initialize();
    }

    public ViewEditText(Context context, AttributeSet attr) {
        super(context, attr);
        initialize();
    }

    private void initialize() {
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Layout layout = ((EditText) v).getLayout();
                        float x = event.getX() + getScrollX();
                        int offset = layout.getOffsetForHorizontal(0, x);
                        if (offset > 0)
                            if (x > layout.getLineMax(0))
                                setSelection(offset);     // touch was at end of text
                            else
                                setSelection(offset - 1);
                        break;
                }
                return true;
            }
        });
        setTextIsSelectable(true);
    }

    public void appendToCursorLocation(String text) {
        int start = Math.max(getSelectionStart(), 0);
        int end = Math.max(getSelectionEnd(), 0);
        getText().replace(Math.min(start, end), Math.max(start, end),
                text, 0, text.length());
    }

    public void deletePrevCharCursorLocation() {
        int start = Math.max(getSelectionStart(), 0);
        int end = Math.max(getSelectionEnd(), 0);
        if(start > 0) {
            getText().delete(start - 1, end);
        }
    }
}
