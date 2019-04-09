package com.base.base.baseui.scan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.base.ImageConst;
import com.base.base.R;
import com.base.base.RouterCommonPath;
import com.base.base.baseui.base.BaseActivity;
import com.base.base.baseui.base.BasePresenter;
import com.base.base.baseui.scan.fragment.CaptureFragment;
import com.base.base.baseui.scan.util.CodeUtils;
import com.base.base.baseui.widget.album.AlbumUtil;
import com.base.base.utilcode.util.FileUtils;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterCommonPath.ROUTER_SCAN)
public class ScanCodeActivity extends BaseActivity implements View.OnClickListener {

    public static final int SCAN_REQUEST_CODE = 100;

    public static final String SCAN_DATA = "SCAN_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaptureFragment captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.view_scan);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent intent = new Intent();
            intent.putExtra(SCAN_DATA, result);
            setResult(RESULT_OK, intent);
            finish();
        }

        @Override
        public void onAnalyzeFailed() {
        }
    };


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TextView tvAlbum = findViewById(R.id.tv_album);
        tvAlbum.setOnClickListener(this);
        ImageView ivClose = findViewById(R.id.iv_close);
        ivClose.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_album) {
            AlbumUtil.ChooseAlbum(this, 1);
        } else if (id == R.id.iv_close) {
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case ImageConst.REQUEST_CODE_CHOOSE:
                    List<Uri> mSelected = Matisse.obtainResult(data);
                    List<String> albumList = new ArrayList<>();
                    for (Uri uri : mSelected) {
                        albumList.add(FileUtils.getRealPath(uri));
                    }
                    if (albumList.size() == 1) {
                        CodeUtils.analyzeBitmap(albumList.get(0), new CodeUtils.AnalyzeCallback() {
                            @Override
                            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                Intent intent = new Intent();
                                intent.putExtra(SCAN_DATA, result);
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onAnalyzeFailed() {

                            }
                        });
                    }
                    break;
            }
        }
    }

}
