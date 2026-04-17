package com.motionsmash;

import android.content.Context;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class BridgeManager {
    private static BridgeManager instance;
    private final Python py;
    private final PyObject detectorModule;

    private BridgeManager(Context context) {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        py = Python.getInstance();
        detectorModule = py.getModule("detector");
    }

    public static synchronized BridgeManager getInstance(Context context) {
        if (instance == null) {
            instance = new BridgeManager(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * Calls the Python detector to find a centroid in the given frame.
     * 
     * @param frameBytes The raw frame data (BGR format)
     * @param width Frame width
     * @param height Frame height
     * @param hsvRange The HSV range [hMin, sMin, vMin, hMax, sMax, vMax]
     * @return int[] {cX, cY} or null if not found
     */
    public int[] getCentroid(byte[] frameBytes, int width, int height, int[] hsvRange) {
        try {
            PyObject result = detectorModule.callAttr("get_centroid", 
                frameBytes, width, height, 
                hsvRange[0], hsvRange[1], hsvRange[2], 
                hsvRange[3], hsvRange[4], hsvRange[5]);
            
            // In Chaquopy, if a Python function returns None, the Java call returns null.
            if (result != null) {
                return result.toJava(int[].class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
