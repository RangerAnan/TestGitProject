package com.test.mi.testproject.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.test.mi.testproject.R;
import com.test.mi.testproject.base.BaseActivity;

public class TestColorMatrixActivity extends BaseActivity implements View.OnClickListener {


    private Button btn_switch_color;
    private ImageView iv_origin;
    private ImageView iv_change;

    private Bitmap afterBitmap;
    private Paint paint;
    private Canvas canvas;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_color_matrix;
    }

    @Override
    protected void initView() {
        btn_switch_color = (Button)findViewById(R.id.btn_switch_color);
        iv_origin =  (ImageView)findViewById(R.id.iv_origin);
        iv_change =  (ImageView)findViewById(R.id.iv_change);

        btn_switch_color.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        //1.改变图片颜色
        iv_change.setImageBitmap(getAnotherColorBitmap());
    }

    public Bitmap getAnotherColorBitmap() {
        Bitmap baseBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tab_msg1);

        // 1.获取一个与baseBitmap大小一致的可编辑的空图片
        afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), baseBitmap.getConfig());

        // 2.使用Bitmap对象创建画布Canvas, 然后创建画笔Paint。
        canvas = new Canvas(afterBitmap);
        paint = new Paint();

        float[] src = new float[]{
                254, 0, 0, 0, 0,
                0, 225, 0, 0, 0,
                0, 0, 225, 0, 0,
                0, 0, 0, 1, 0};

        // 3.定义ColorMatrix，并指定RGBA矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(src);
        // 4.使用ColorMatrix创建一个ColorMatrixColorFilter对象, 作为画笔的滤镜, 设置Paint的颜色
        paint.setColorFilter(new ColorMatrixColorFilter(src));
        // 5.通过指定了RGBA矩阵的Paint把原图画到空白图片上
        canvas.drawBitmap(baseBitmap, new Matrix(), paint);

        return afterBitmap;
    }
}
