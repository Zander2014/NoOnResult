package pers.zander.noonresult.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pers.zander.noonresult.LifecycleListener;
import pers.zander.noonresult.NoOnResultHelper;


public class OneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        Button mButton = findViewById(R.id.mButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("text", "返回数据");
//                setResult(Activity.RESULT_OK, intent);
//                finish();
                NoOnResultHelper.finishWithResult(OneActivity.this, Activity.RESULT_OK, intent);
//                AvoidOnResultHelper.finishWithResult(OneActivity.this, Activity.RESULT_OK, intent.getBundleExtra(""));
            }
        });


        LifecycleListener lifecycleListener = new LifecycleListener() {


            @Override
            public void onStart() {
                Log.e("OneActivity", "onStart");

            }


            @Override
            public void onStop() {
                Log.e("OneActivity", "onStop");

            }

            @Override
            public void onDestroy() {
                Log.e("OneActivity", "onDestroy");
            }
        };


//        AvoidOnResultHelper.addLifecycleListener(this, lifecycleListener);
        NoOnResultHelper.addLifecycleListener(this, lifecycleListener, true);
//        AvoidOnResultHelper.removeLifecycleListener(this, lifecycleListener);
    }


}
