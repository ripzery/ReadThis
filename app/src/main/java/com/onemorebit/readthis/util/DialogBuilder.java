package com.onemorebit.readthis.util;

import android.content.Context;
import com.afollestad.materialdialogs.MaterialDialog;
import com.onemorebit.readthis.R;

/**
 * Created by Euro on 2/9/16 AD.
 */
public class DialogBuilder {
    public static MaterialDialog getSlideFragmentDialog(Context context){
        return new MaterialDialog.Builder(context)
            .title(R.string.dialog_title)
            .content(R.string.dialog_content)
            .positiveText(R.string.clear)
            .negativeText(R.string.delete)
            .build();
    }
}
