package pers.zander.noonresult;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
/**
 * Created by Zander on 2020-06-07.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
public class NoOnResultHelper {
    private static final String TAG = "NoOnResultHelper";


    private NoOnResultHelper() {
    }

    /**
     * 设置 requestCode 的使用范围
     *
     * @param start 默认 65000
     * @param end   默认 65535
     */
    public static void setRequestCodeRange(@IntRange(from = 0, to = 65535) int start, @IntRange(from = 0, to = 65535) int end) {
        if (start >= end) {
            Log.e(TAG, "start(" + start + ") must less than end(" + end + ") , Now use the default values !");
            return;
        }
        NoOnResultFragment.setRequestCodeRange(start, end);
    }

    private static NoOnResultFragment getAvoidOnResultFragment(FragmentActivity activity) {
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        NoOnResultFragment avoidOnResultFragment = (NoOnResultFragment) supportFragmentManager.findFragmentByTag(TAG);
        if (avoidOnResultFragment == null) {
            avoidOnResultFragment = new NoOnResultFragment();
            supportFragmentManager.beginTransaction()
                    .add(avoidOnResultFragment, TAG)
                    .commitNowAllowingStateLoss();
        }
        return avoidOnResultFragment;
    }

    /**
     * 打开 activity
     *
     * @param activity
     * @param intent
     * @param callback
     */
    public static void startActivityForResult(@NonNull FragmentActivity activity, @NonNull Intent intent, @NonNull ActivityCallback callback) {
        startActivityForResult(activity, intent, null, callback);
    }

    public static void startActivityForResult(@NonNull FragmentActivity activity, @NonNull Intent intent, Bundle options, @NonNull ActivityCallback callback) {
        NoOnResultFragment avoidOnResultFragment = getAvoidOnResultFragment(activity);
        avoidOnResultFragment.startActivityForResult(intent, options, callback);
    }

    public static void finishWithResult(FragmentActivity activity, int resultCode, Intent intent) {
        activity.setResult(resultCode, intent);
        activity.finish();
    }

    public static void finishWithResult(FragmentActivity activity, int resultCode, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        activity.setResult(resultCode, intent);
        activity.finish();
    }

    public interface ActivityCallback {
        void onActivityResult(int resultCode, Intent data);
    }

    /**
     * 请求权限
     *
     * @param activity
     * @param permissions
     * @param permissionsCallBack
     */
    public static void requestPermissions(@NonNull FragmentActivity activity, @NonNull String[] permissions, @NonNull PermissionsCallBack permissionsCallBack) {
        NoOnResultFragment avoidOnResultFragment = getAvoidOnResultFragment(activity);
        avoidOnResultFragment.requestPermissions(permissions, permissionsCallBack);
    }

    public interface PermissionsCallBack {
        void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults);
    }


    /**
     * 生命周期,非粘性,注册的时候不调用回调函数
     */
    public static void addLifecycleListener(@NonNull FragmentActivity activity, @NonNull LifecycleListener listener) {
        addLifecycleListener(activity, listener, false);
    }

    /**
     * 粘性,注册的时候调用回调函数
     *
     * @param activity
     * @param listener
     */
    public static void addLifecycleListener(@NonNull FragmentActivity activity, @NonNull LifecycleListener listener, boolean isSticky) {
        NoOnResultFragment avoidOnResultFragment = getAvoidOnResultFragment(activity);
        avoidOnResultFragment.addLifecycleListener(listener, isSticky);
    }


    public static void removeLifecycleListener(@NonNull FragmentActivity activity, @NonNull LifecycleListener listener) {
        NoOnResultFragment avoidOnResultFragment = getAvoidOnResultFragment(activity);
        avoidOnResultFragment.removeLifecycleListener(listener);
    }
}
