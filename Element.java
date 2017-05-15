package dautien.gameversion3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Thuan on 3/22/2017.
 */

public class Element {

    Bitmap bitmap;
    Bitmap show;
    Bitmap[] array;
    int mX;
    int mY;
    private int width;
    private int height;
    private int COLUMN_COUNT = 4;
    private int columnUsing;
    private int khoangThoiGianChuyenAnh;

    public Element(Resources resources, int x, int y){
        show = BitmapFactory.decodeResource(resources, R.drawable.archer);
        //bitmap= Bitmap.createScaledBitmap(bitmap, 216, 162, true);
        //mX= x - bitmap.getWidth() / 2;
        //mY= y - bitmap.getHeight() / 2;
        this.mX = x;
        this.mY = y;
    }

    private Bitmap createSubImage(int column){
        width = show.getWidth()/COLUMN_COUNT;
        height = show.getHeight();
        Bitmap subImage = Bitmap.createBitmap(show, column*width, 0, width, height);
        return subImage;
    }

    public void doDraw(Canvas canvas){
        khoangThoiGianChuyenAnh++;
        if(khoangThoiGianChuyenAnh >= 5) {
            columnUsing++;
            khoangThoiGianChuyenAnh = 0;
        }
        if(columnUsing >= COLUMN_COUNT){columnUsing = 0;}
        array = new Bitmap[COLUMN_COUNT];
        this.array[columnUsing] = this.createSubImage(columnUsing);
        bitmap = array[columnUsing];
        bitmap= Bitmap.createScaledBitmap(bitmap, 216, 162, true);
        canvas.drawBitmap(bitmap, mX, mY, null);
    }


}
