package dautien.gameversion3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Thuan on 3/23/2017.
 */

public class Bullet {

    int x;
    int y;
    Bitmap bitmap;
    private int vanToc= 20;

    public Bullet(Resources resources, int x, int y){
        this.x= x;
        this.y= y;
        bitmap= BitmapFactory.decodeResource(resources, R.drawable.arrow);
        bitmap= Bitmap.createScaledBitmap(bitmap, 72, 27, true);
    }

    public Bullet (Resources resources, int x, int y, int image){
        this.x= x;
        this.y= y;
        bitmap= BitmapFactory.decodeResource(resources, image);
        bitmap= Bitmap.createScaledBitmap(bitmap, 72, 27, true);
    }

    public void doDraw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, null);
        x+= vanToc;
    }

    public void setVanToc(int vanToc){
        this.vanToc= vanToc;
    }

    public int getWidth(){
        return bitmap.getWidth();
    }

    public int getHeight(){
        return bitmap.getHeight();
    }

    public int getTamX(){
        return (x+(bitmap.getWidth()/2));
    }

    public int getTamY(){
        return (y+(bitmap.getHeight()/2));
    }
}
