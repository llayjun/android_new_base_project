package com.base.base.baseui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import com.base.base.baseui.dialog.LoadingDialog;


public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P presenter;
    protected BaseActivity activity;
    protected LoadingDialog mLoadingDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog.Builder(activity)
                .setMessage("加载中...")
                .setCancelable(false)
                .setCancelableOutSide(false)
                .create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
        findViews(view);
    }

    protected abstract void findViews(View view);

    protected abstract P createPresenter();

    public void showLoadingDialog() {
        if (null != mLoadingDialog && !mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void hideLoadingDialog() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

}

 