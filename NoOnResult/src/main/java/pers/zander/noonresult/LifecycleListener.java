package pers.zander.noonresult;

/**
 * Created by Zander on 2020-06-07.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
public interface LifecycleListener {

    /**
     * 开始
     */
    void onStart();

    /**
     * 结束
     */
    void onStop();

    /**
     * 销毁
     */
    void onDestroy();

    class LifecycleListenerWrapper implements LifecycleListener {

        @Override
        public void onStart() {

        }

        @Override
        public void onStop() {

        }

        @Override
        public void onDestroy() {

        }
    }
}
