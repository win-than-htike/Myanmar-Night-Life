package myanmarnightlife.lower.team1.components.textviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import myanmarnightlife.lower.team1.utils.MMFontUtils;

/**
 * Created by winthanhtike on 6/27/16.
 */
public class MMTextView extends TextView {

    public MMTextView(Context context) {
        super(context);
        if (!isInEditMode()){
            MMFontUtils.setMMFont(this);
        }
    }

    public MMTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()){
            MMFontUtils.setMMFont(this);
        }
    }

    public MMTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()){
            MMFontUtils.setMMFont(this);
        }
    }
}
