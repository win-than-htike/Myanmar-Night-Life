package myanmarnightlife.lower.team1.font;

import android.graphics.Typeface;
import java.util.HashMap;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;

/**
 * Created by user on 10/29/16.
 */

public class CustomFont {
  static CustomFont INSTANCE;

  HashMap<String, String> fontMap = new HashMap<>();
  HashMap<String, Typeface> fontCache = new HashMap<>();

  private CustomFont() {

  }

  public static CustomFont getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CustomFont();
    }
    return INSTANCE;
  }

  public void addFont(String alias, String fontName) {
    fontMap.put(alias, fontName);
  }

  public Typeface getFont(String alias) {
    String fontFilename = fontMap.get(alias);
    if (fontFilename == null) {
      throw new UnsupportedOperationException("No Such Font");
    } else {
      if (fontCache.containsKey(alias)) {
        return fontCache.get(alias);
      } else {
        Typeface typeface = Typeface.createFromAsset(MyanmarNightLifeApp.getContext().getAssets(),
            "fonts/" + fontFilename);
        fontCache.put(alias, typeface);
        return typeface;
      }
    }
  }
}

