package dautien.gameversion3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.Random;

/**
 * Created by Thuan on 3/23/2017.
 */

public class Enemies {

    int x;
    int y;
    Bitmap [] bitmap;
    Bitmap image;
    Bitmap show;
    int vanToc= 10;
    //int [] screen= {R.drawable.enemies1, R.drawable.enemies2, R.drawable.enemies3};
    //int eRandom;
    private int width;
    private int height;
    private int COLUMN_COUNT= 6;
    private int columnUsing;

    public Enemies(Resources resources, int chieuRong, int chieuCao){
        //Random random= new Random();
        //eRandom= random.nextInt(3);
        this.x= chieuRong;
        int a= 2*chieuCao/3 - 5;
        //int a= 0 + (int)(Math.random() * ((chieuCao - 0) + 1));
        this.y= a;
        Bitmap bitmapEnemies1= BitmapFactory.decodeResource(resources, R.drawable.enemies1);
        //bitmapEnemies1= Bitmap.createScaledBitmap(bitmapEnemies1, 144, 108, true);
        image= bitmapEnemies1;
    }

    private Bitmap createSubImage(int column){
        width= image.getWidth()/COLUMN_COUNT;
        height= image.getHeight();
        Bitmap subImage= Bitmap.createBitmap(image, column*width, 0, width, height);
        return subImage;
    }

    public void doDraw(Canvas canvas){
        columnUsing++;
        if(columnUsing >= COLUMN_COUNT) columnUsing= 0;
        bitmap= new Bitmap[COLUMN_COUNT];
        this.bitmap[columnUsing]= this.createSubImage(columnUsing);
        show= bitmap[columnUsing];

        canvas.drawBitmap(show, x, y, null);
        x -= vanToc;

    }

    public void setXY(int x, int y){
        this.x= x;
        this.y= y;
    }

    public int getWidth(){
        return show.getWidth();
    }

    public int getHeight(){
        return show.getHeight();
    }

    public int getTamX(){
        return (x + (show.getWidth()/2));
    }

    public int getTamY(){
        return (y + (show.getHeight()/2));
    }
}