package dautien.gameversion3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.Build;
import android.media.AudioAttributes;
import android.media.AudioManager;
import java.util.ArrayList;

/**
 * Created by Thuan on 3/20/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;
    private Bitmap bitmap;
    private ParallaxBackground background;
    private Element myElenment;
    private DragonAnimation dragon;
    private int thoiGianXuatHienKT;
    private int soundIdExplosion;
    private SoundPool soundPool;
    private boolean soundPoolLoaded;
    private static final int MAX_STREAMS= 100;
    private int widthCanvas;
    private int heightCanvas;

    private ArrayList<Enemies> enemies= new ArrayList<>();
    private ArrayList<Bullet> bullets= new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private int thoiGianNapDan= 0;

    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        thread= new MainThread(getHolder(),this);
        setFocusable(true);
        background= new ParallaxBackground(this.getResources());
        this.initSoundPool();
    }

    @Override
    protected void onDraw(Canvas canvas){
        // Todo Auto-generated method stup
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        widthCanvas = canvas.getWidth();
        heightCanvas = canvas.getHeight();
        background.doDrawRunning(canvas);
        thoiGianNapDan++;
        thoiGianXuatHienKT++;
        if(dragon == null){dragon = new DragonAnimation(getResources(), 1080, 200);}
        if(myElenment == null){myElenment = new Element(getResources(), 10, 600);}
        if(myElenment != null){
            myElenment.doDraw(canvas);
            this.doDrawBullet(canvas);
            this.doDrawEnemies(canvas);
            this.doDrawExplosion(canvas);
            xetVaCham(canvas);
        }
        if(dragon != null){
            dragon.doDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // TODO Auto-generated method stup

        /*if(myElenment == null) {
            myElenment = new Element(getResources(), (int) event.getX(), (int) event.getY());
            return true;
        }
        else {
            myElenment.mX= (int)event.getX() - myElenment.bitmap.getWidth()/2;
            myElenment.mY= (int)event.getY() - myElenment.bitmap.getHeight()/2;
        }*/

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            myElenment.mX= (int)event.getX() - myElenment.bitmap.getWidth()/2;
            myElenment.mY= (int)event.getY() - myElenment.bitmap.getHeight()/2;
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            myElenment.mX= (int)event.getX() - myElenment.bitmap.getWidth()/2;
            myElenment.mY= (int)event.getY() - myElenment.bitmap.getHeight()/2;
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            myElenment.mX= (int)event.getX() - myElenment.bitmap.getWidth()/2;
            myElenment.mY= (int)event.getY() - myElenment.bitmap.getHeight()/2;
        }
        return true;
    }

    public void doDrawBullet(Canvas canvas){
        Paint p= new Paint();
        p.setColor(Color.RED);
        canvas.drawRect(10, 10, thoiGianNapDan * 10, 20, p);
        if(thoiGianNapDan > 30) {
            thoiGianNapDan= 0;
            Bullet muiTen1= new Bullet(getResources(), myElenment.mX, myElenment.mY, R.drawable.arrow);
            Bullet muiTen2= new Bullet(getResources(), myElenment.mX + 30, myElenment.mY + 30, R.drawable.arrow);
            Bullet muiTen3= new Bullet(getResources(), myElenment.mX - 30, myElenment.mY - 30, R.drawable.arrow);
            bullets.add(muiTen1);
            bullets.add(muiTen2);
            bullets.add(muiTen3);
        }

        for(int i= 0; i < bullets.size(); i++){
            bullets.get(i).doDraw(canvas);
        }

        for(int i= 0; i < bullets.size(); i++){
            if(bullets.get(i).x > canvas.getWidth()){bullets.remove(i);}
        }
    }

    public void doDrawEnemies(Canvas canvas){
        if(thoiGianXuatHienKT >= 30){
            thoiGianXuatHienKT= 0;
            Enemies motKeThu = new Enemies(getResources(), canvas.getWidth(), canvas.getHeight());
            enemies.add(motKeThu);
        }
        for(int i= 0; i < enemies.size(); i++){
            enemies.get(i).doDraw(canvas);
        }
        for (int i= 0; i < enemies.size(); i++){
            if(enemies.get(i).x < 0) {enemies.remove(i);}
        }
    }

    public void getExplosion(Bullet bullet){
        int x= bullet.getTamX();
        int y= bullet.getTamY();
        Explosion explosion= new Explosion(getResources(), x, y);
        explosions.add(explosion);
    }

    public void doDrawExplosion(Canvas canvas){
        for(int i= 0; i < explosions.size(); i++){
            explosions.get(i).doDraw(canvas);
        }
        for(int i= 0; i < explosions.size(); i++){
            if(explosions.get(i).count == 8 ){explosions.remove(i);}
        }
    }

    private void initSoundPool()  {
        if (Build.VERSION.SDK_INT >= 21 ) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        else {
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }
        // Sự kiện SoundPool đã tải lên bộ nhớ thành công.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPoolLoaded = true;
            }
        });
        // Tải file nhạc tiếng nổ (explosion.wav) vào SoundPool.
        this.soundIdExplosion = this.soundPool.load(this.getContext(), R.raw.explosion,1);


    }

    public void playSoundExplosion(){
        if(soundPoolLoaded){
            float leftVolumn= 0.8f;
            float rightVolumn= 0.8f;
            int streamId= this.soundPool.play(this.soundIdExplosion, leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public boolean collision_B_E(Bullet bullet, Enemies enemies){
        float nuaChieuRong_B= (float)bullet.getWidth()/2;
        int nuaChieuCao_B= bullet.getHeight()/2;
        float nuaChieuRong_E= (float)enemies.getWidth()/2;
        int nuaChieuCao_E= enemies.getHeight()/2;

        int khoangCachTrucX= Math.abs(bullet.getTamX() - enemies.getTamX());
        int khoangCachTrucY= Math.abs(bullet.getTamY() - enemies.getTamY());

        if((khoangCachTrucX <= nuaChieuRong_B + nuaChieuRong_E) && (khoangCachTrucY <= nuaChieuCao_B + nuaChieuCao_E))
            return true;
        else
            return false;
    }

    public void xetVaCham(Canvas canvas){
        try{
            for (int i= 0; i < bullets.size(); i++)
                for(int j= 0; j < enemies.size(); j++) {
                    if (collision_B_E(bullets.get(i), enemies.get(j)) == true){
                        getExplosion(bullets.get(i));
                        this.playSoundExplosion();
                        bullets.remove(i);
                        enemies.remove(j);
                    }
                }
        }
        catch (Exception e){

        }
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){
        // TODO Auto-generated method stup
    }

    public void surfaceCreated(SurfaceHolder arg0){
        // gan state cho thread va kich cho thread chay
        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder arg0){
        // destroy thread
        if(thread.isAlive())
            thread.setRunning(false);
    }
}
