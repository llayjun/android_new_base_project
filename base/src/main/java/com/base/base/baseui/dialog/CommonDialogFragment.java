package com.base.base.baseui.dialog;

import android.view.View;
import com.base.base.baseui.dialog.base_dialog.BaseDialogFragment;


/**
 * 普通的dialogfragment，可直接显示view
 */
@Deprecated
public class CommonDialogFragment extends BaseDialogFragment {

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public void bindView(View v) {

    }

}
