package com.qiyu;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.qiyukf.unicorn.api.ImageLoaderListener;
import com.qiyukf.unicorn.api.UnicornImageLoader;

public class Utils {
  private static Handler sHandler;

  public static int dp2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static int px2dp(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (connectivityManager != null) {
      NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
      if (allNetworkInfo != null) {
        for (NetworkInfo networkInfo : allNetworkInfo) {
          if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean inMainProcess(Context context) {
    String packageName = context.getPackageName();
    String processName = Utils.getProcessName(context);
    return packageName.equals(processName);
  }

  /**
   * 获取当前进程名
   *
   * @return 进程名
   */
  private static String getProcessName(Context context) {
    String processName = null;

    // ActivityManager
    ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

    while (true) {
      for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
        if (info.pid == android.os.Process.myPid()) {
          processName = info.processName;
          break;
        }
      }

      // go home
      if (!TextUtils.isEmpty(processName)) {
        return processName;
      }

      // take a rest and again
      try {
        Thread.sleep(100L);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }

  public static void runOnUiThread(Runnable runnable) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      runnable.run();
    } else {
      if (sHandler == null) {
        sHandler = new Handler(Looper.getMainLooper());
      }
      sHandler.post(runnable);
    }
  }
}
