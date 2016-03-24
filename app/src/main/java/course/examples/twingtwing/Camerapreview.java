package course.examples.twingtwing;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by ggnanasekar on 3/15/2016.
 */
public class Camerapreview extends SurfaceView implements SurfaceHolder.Callback {
    private final String TAG = "Camerapreview";
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public Camerapreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;

        mHolder = getHolder();
        mHolder.addCallback(this);

        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Error setting Camera Preview " + e.getMessage());
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

//        check the surface
        if (mHolder.getSurface() == null) {
            Log.d(TAG, "surface is null");
            return;
        }

//        stop the preview

        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            mCamera.setDisplayOrientation(90);
            Log.d(TAG, "landscape orientation");

        } else {
            mCamera.setDisplayOrientation(0);
            Log.d(TAG, "portrait orientation");
        }

        try {


            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Error starting Camera preview" + e.getMessage());
        }


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        mCamera.release();

    }
}
