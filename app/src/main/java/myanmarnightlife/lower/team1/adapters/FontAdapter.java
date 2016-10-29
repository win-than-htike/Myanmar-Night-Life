package myanmarnightlife.lower.team1.adapters;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.TextView;
import myanmarnightlife.lower.team1.font.CustomFont;

/**
 * Created by user on 10/29/16.
 */

public class FontAdapter {

  @BindingAdapter("app:bind_font") public static void bindText(TextView view, String fontName) {
    Log.d("Data", fontName);
    if (view instanceof TextView) {
      ((TextView) view).setTypeface(CustomFont.getInstance().getFont(fontName));
    }
  }
}
