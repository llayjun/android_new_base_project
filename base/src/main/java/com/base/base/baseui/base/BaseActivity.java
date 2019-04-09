package com.base.base.baseui.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.base.base.baseui.dialog.LoadingDialog;
import com.base.base.utilcode.util.AdaptScreenUtils;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;
    public FragmentManager fragmentManager;
    protected P presenter;
    protected LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initInfo();
        initData();
        initView();
        loadData();
    }

    private void initInfo() {
        fragmentManager = getSupportFragmentManager();
        presenter = createPresenter();
        mLoadingDialog = new LoadingDialog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(false)
                .setCancelableOutSide(false)
                .create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        /*InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); //得到InputMethodManager的实例
        if (imm.isActive()) {//如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,InputMethodManager.HIDE_NOT_ALWAYS);//关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }*/
    }

    protected abstract P createPresenter();

    protected abstract int initLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void loadData();

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

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080);
    }

}
