package com.nader.chat.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;


public class ThemeUtils {

    public static int getThemeAttributeValue(int attr, Context context) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }
}
