package com.mbh.materialdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;

/**
 * Created By MBH on 2016-10-17.
 */

class Utils {
    static boolean isGoodString(String string){
        return string != null && string.length() > 0;
    }

    static boolean isGoodColor(String string){
        try {
            Color.parseColor(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static boolean isGoodColor(int color){
        return isGoodString("#"+color);
    }

    static Drawable getColorDrawable(Context context, int color){
        GradientDrawable cd = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                new int[]{color, color});
//        cd.setCornerRadius(6);
        final float radii = 8f;
        cd.setCornerRadii(new float[] { radii, radii, radii, radii, 0, 0, 0, 0 });
        return getLLDrawable(context, cd);
    }

    static Drawable getLLDrawable(Context context, Drawable drawable){
        try {
            LayerDrawable ld;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ld = (LayerDrawable) context.getResources().getDrawable(R.drawable.material_card, context.getTheme());
            } else {
                ld = (LayerDrawable) context.getResources().getDrawable(R.drawable.material_card);
            }

            ld.setDrawableByLayerId(R.id.layerlist_drawable, drawable);
            return ld;
        }catch (Exception e){
            return drawable;
        }
    }
}
