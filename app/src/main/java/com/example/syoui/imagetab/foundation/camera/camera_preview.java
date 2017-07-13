package com.example.syoui.imagetab.foundation.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.syoui.imagetab.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class camera_preview extends AppCompatActivity implements SurfaceHolder.Callback ,Camera.PreviewCallback,Camera.PictureCallback {

    private SurfaceView mySurfaceView;
    private Camera myCamera; //hardware

    private static int PREVIEW_WIDTH = 1600;
    private static int PREVIEW_HEIGHT = 1200;
    private SurfaceHolder mHolder = null;
    private SurfaceTexture mSurface = null;
    private int[] mGrayImg = null;
    private Bitmap mBitmap = null;
    private String picSavedPath;


    private static final String SDCARD_FOLDER = "/sdcard/CameraSample/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);


        SurfaceView preview = (SurfaceView) findViewById(R.id.preview_id);
        mHolder = preview.getHolder();
        mHolder.addCallback(this);

        File dirs = new File(SDCARD_FOLDER);
        if(!dirs.exists()) {
            dirs.mkdir();
        }
    }


    public void takePictureOnClick(View v){
        Log.d("takePic","pic");
        myCamera.takePicture(null, null,this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        int cameraId = 0;
        myCamera = Camera.open(cameraId);

        setCameraDisplayOrientation(cameraId);

        Camera.Parameters params = myCamera.getParameters();
        int width = params.getPictureSize().width;
        int height = params.getPictureSize().height;

        //3120 4160
        // 4032 3024

        Camera.Parameters mParam = myCamera.getParameters();
        List<Size> sizes = mParam.getSupportedPreviewSizes();
        for (Size size : sizes) {
            Log.d("CHECK", "w: " + size.width + ", h: " + size.height);
        }

        Log.d("ak47","width:"+width+",height:"+height);


        try {

            Camera.Size prevSize = mParam.getSupportedPreviewSizes().get(1);
            //prevSize should be still in the camera's orientation. In your and my cases - landscape
//            params.setPreviewSize(prevSize.width, prevSize.height);
//            myCamera.setParameters(params);



            mySurfaceView = (SurfaceView)findViewById(R.id.preview_id);
            ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mySurfaceView.getLayoutParams();

            lp.width = (int)(prevSize.height/2); //横幅
            lp.height = (int)(prevSize.width/2); //縦幅

            Log.d("preSizeWidth",""+ (int)(prevSize.height/2));
            Log.d("preSizeHeight",""+ (int)(prevSize.width/2));

            mySurfaceView.setLayoutParams(lp);
            mySurfaceView.getHolder().setFixedSize(lp.height, lp.width);



            myCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //プレビュースタート（Changedは最初にも1度は呼ばれる）
        myCamera.startPreview();

        myCamera.stopPreview();
        // プレビュー画面のサイズ設定
        Camera.Parameters params = myCamera.getParameters();
        List<Size> previewSizes = params.getSupportedPreviewSizes();
        Size size = previewSizes.get(1);
        params.setPreviewSize(size.width, size.height);

        myCamera.setParameters(params);
        // プレビュー開始
        myCamera.startPreview();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        myCamera.stopPreview();
        myCamera.release();
        myCamera = null;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        int p;

        // グレースケル画像に変換
        for (int i=0; i<mGrayImg.length; i++) {
            p = data[i] & 0xff;
            mGrayImg[i] = 0xff000000 | p << 16 | p << 8 | p;
        }
        mBitmap.setPixels(mGrayImg, 0, PREVIEW_WIDTH, 0, 0, PREVIEW_WIDTH, PREVIEW_HEIGHT);
        // 描画処理
        Canvas canvas = mHolder.lockCanvas();
        if(canvas != null){
            canvas.drawBitmap(mBitmap, 0, 0, null);
            mHolder.unlockCanvasAndPost(canvas);
        }
    }

            // ディスプレイの向き設定
    public void setCameraDisplayOrientation(int cameraId) {
        // カメラの情報取得
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, cameraInfo);
        // ディスプレイの向き取得
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
        case Surface.ROTATION_0:
            degrees = 0; break;
        case Surface.ROTATION_90:
            degrees = 90; break;
        case Surface.ROTATION_180:
            degrees = 180; break;
        case Surface.ROTATION_270:
            degrees = 270; break;
        }
        // プレビューの向き計算
        int result;
        if (cameraInfo.facing == cameraInfo.CAMERA_FACING_FRONT) {
            result = (cameraInfo.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        }
        else {
        // back-facing
            result = (cameraInfo.orientation - degrees + 360) % 360;
        }
        // ディスプレイの向き設定
            myCamera.setDisplayOrientation(result);
        }


    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd_kkmmss");
        String datName = "P" + date.format(new Date()) + ".jpg";

        try {
            // データ保存
            picSavedPath = SDCARD_FOLDER + datName;
            savePhotoData(datName, bytes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if(myCamera != null) {
                myCamera.release();
                myCamera = null;
            }
        }
        // プレビュー再開
        myCamera.startPreview();
    }


    private void savePhotoData(String datName, byte[] data) throws Exception {
        // TODO Auto-generated method stub
        FileOutputStream outStream = null;

        try {
            Log.d("savePath",SDCARD_FOLDER + datName);
            outStream = new FileOutputStream(SDCARD_FOLDER + datName);
            outStream.write(data);

            outStream.close();
            showPicture();
        } catch (Exception e) {
            if(outStream != null) {
                outStream.close();
        }
            throw e;
        }
    }


    public void showPicture(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap b = BitmapFactory.decodeFile(picSavedPath, options);

        ImageView image = (ImageView) findViewById(R.id.afterShow);



        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        Bitmap rotated = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(),
                matrix, true);

        //image.setImageBitmap(rotated);

        ///////////////////////////////////////////////////////////////////////////

        Bitmap bitmap1 = rotated;
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.rufi);


        int backgroundWidth = bitmap1.getWidth();
        int backgroundHeight = bitmap1.getHeight();


        Bitmap newBitmap = Bitmap.createBitmap(backgroundWidth, backgroundHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);


        ImageView imageView = (ImageView) findViewById(R.id.imageView3);

        Log.d("view......",imageView.getWidth()+":"+imageView.getHeight());
//        int stampWidth = bitmap2.getWidth()/8;
//        int stampHeight = bitmap2.getHeight()/8;


        int stampWidth = 135;
        int stampHeight = 135;

        Bitmap bs = getResizedBitmap(bitmap2,stampWidth,stampHeight);

        canvas.drawBitmap(bitmap1,0,0,null);
        canvas.drawBitmap(bs,backgroundWidth/2-stampWidth/2,(int)(backgroundHeight-stampHeight-30),null);
        //newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        image.setImageBitmap(newBitmap);

    }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


}
