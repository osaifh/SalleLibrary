package com.example.alumne.componente;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: document your custom view class.
 */
public class MyView extends TextView {
    private String defaultFormat = "dd-MM-yyyy HH:mm:ss";

    public MyView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyle, 0);
        String dateFormat = a.getString(R.styleable.MyView_dateFormat);
        if (defaultFormat != null) {
            setDateFormat(dateFormat);
        } else {
            setDateFormat(defaultFormat);
        }
        a.recycle();
    }
    private void setDateFormat(String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        this.setText(simpleDateFormat.format(new Date()));
        invalidate();
        requestLayout();
    }
}