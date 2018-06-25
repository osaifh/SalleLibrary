package axel.telkamper.mydatetextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.widget.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: document your custom view class.
 */
public class MyView extends AppCompatTextView {
    private String defaultFormat = "dd-MM-yyy HH:mm:ss";

    public MyView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyView, defStyle,0);
        String dateFormat = a.getString(R.styleable.MyView_dateFormat);

        if(dateFormat!=null){
            setDateFormat(dateFormat);

        }else{
            setDateFormat(defaultFormat);
        }

        a.recycle();
    }
    public void setDateFormat(String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat) ;
        this.setText(simpleDateFormat.format(new Date()));
        invalidate();
        requestLayout();
    }

}