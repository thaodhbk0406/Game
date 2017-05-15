package dautien.gameversion3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Thuan on 4/18/2017.
 */

public class DragonAnimation {
    int x;
    int y;
    int mX;
    Bitmap bitmap;
    Bitmap bitmapEnemies1;
    Bitmap[] array;
    int VANTOC= 5;
    private int COLUMN_COUNT = 4;
    private int ROW_COUNT = 4;
    private int width;
    private int height;
    private int columnUsing;
    private int rowUsing;
    private int khoangThoiGianVoCanh;
    private int checkVecTo;
    private int toaDoCu_x;

    public DragonAnimation(Resources resources, int chieuRong, int chieuCao){
        //Random random= new Random();
        //eRandom= random.nextInt(3);
        this.x = chieuRong;
        this.mX = chieuRong;
        int a = chieuCao/3 - 5;
        this.y = a;
        bitmapEnemies1 = BitmapFactory.decodeResource(resources, R.drawable.dragonknight);
        //bitmapEnemies1= Bitmap.createScaledBitmap(bitmapEnemies1, 144, 108, true);
    }

    private Bitmap createSubImage(int column, int row){
        width = bitmapEnemies1.getWidth()/COLUMN_COUNT;
        height = bitmapEnemies1.getHeight()/ROW_COUNT;
        Bitmap subImage = Bitmap.createBitmap(bitmapEnemies1, column*width, row*height, width, height);
        return subImage;
    }

    public void doDraw(Canvas canvas){
        khoangThoiGianVoCanh++;
        if(khoangThoiGianVoCanh >= 5){
            columnUsing++;
            khoangThoiGianVoCanh = 0;
        }
        if(columnUsing >= COLUMN_COUNT){columnUsing = 0;}
        array = new Bitmap[COLUMN_COUNT];
        this.array[columnUsing] = this.createSubImage(columnUsing, 0);
        bitmap = array[columnUsing];
        canvas.drawBitmap(bitmap, x, y, null);

        toaDoCu_x = x;
        if((x >= mX) || (checkVecTo < 0)){
            if (x > 30) x -= VANTOC;
        }
        if((x <= 30) || (checkVecTo > 0)){
            if (x < mX) x += 2*VANTOC;
        }
        checkVecTo = x - toaDoCu_x;
    }

    public void setXY(int x, int y){
        this.x= x;
        this.y= y;
    }

    public int getWidth(){
        return bitmap.getWidth();
    }

    public int getHeight(){
        return bitmap.getHeight();
    }

}
