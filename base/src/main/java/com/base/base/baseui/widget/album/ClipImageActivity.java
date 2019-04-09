package com.base.base.baseui.widget.album;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.base.ImageConst;
import com.base.base.R;
import com.base.base.RouterCommonPath;
import com.base.base.baseui.base.BaseActivity;
import com.base.base.baseui.base.BasePresenter;
import com.base.base.utilcode.constant.PathConstants;
import com.base.base.utilcode.util.*;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;

import java.io.File;

/**
 * Created by zhangyinlei on 2018/3/30 16:19
 */
@Route(path = RouterCommonPath.ROUTER_COMMON_IMAGE_CLIP)
public class ClipImageActivity extends BaseActivity implements View.OnClickListener {

    public static final String IMAGE_PATH = "IMAGE_PATH";

    public static final int IMAGE_REQUEST_CODE = 100;

    private String mPath;
    private CropImageView mCropImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setNavBarVisibility(this, false);
        BarUtils.setStatusBarVisibility(this, false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_clip_image;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        this.mCropImageView = findViewById(R.id.cropImageView);
        this.mCropImageView.setInitialFrameScale(0.6f);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tv_use).setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        mPath = getIntent().getStringExtra(ImageConst.EXTRA_CLIP_IMAGE);
        GlideUtils.loadImageViewCacheAndDisk(this, mPath, mCropImageView);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.tv_use) {
            showLoadingDialog();
            final String pathName = PathConstants.PIC_PATH + TimeUtils.getNowMills() + ".jpg";
            mCropImageView.crop(Uri.fromFile(new File(mPath)))
                    .execute(new CropCallback() {
                        @Override
                        public void onSuccess(Bitmap cropped) {
                            boolean bo = ImageUtils.save(cropped, pathName, Bitmap.CompressFormat.JPEG, true);
                            if (bo) {
                                setResult(RESULT_OK, new Intent().putExtra(IMAGE_PATH, pathName));
                                finish();
                            }
                            hideLoadingDialog();
                        }

                        @Override
                        public void onError(Throwable e) {
                            hideLoadingDialog();
                        }
                    });
        }
    }

}
