package dautien.gameversion3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.content.Context;
import android.view.SurfaceHolder;

/**
 * Created by Thuan on 3/20/2017.
 */

public class ParallaxBackground {

    private int toaDoNen1_X= 0;
    private Bitmap hinhNen1;

    public ParallaxBackground(Resources r){
        hinhNen1= BitmapFactory.decodeResource(r, R.drawable.background);
        hinhNen1= Bitmap.createScaledBitmap(hinhNen1, 2880, 1080, true);
    }

    public void doDrawRunning(Canvas canvas){

        toaDoNen1_X= toaDoNen1_X - 1;

        int toaDoNen1_phu_X= hinhNen1.getWidth() - (-toaDoNen1_X);

        if(toaDoNen1_phu_X <= 0){
            toaDoNen1_X= 0;
            canvas.drawBitmap(hinhNen1, 0, 0, null);
        }
        else {
            canvas.drawBitmap(hinhNen1, toaDoNen1_X, 0, null);
            canvas.drawBitmap(hinhNen1, toaDoNen1_phu_X, 0, null);
        }
    }
}
