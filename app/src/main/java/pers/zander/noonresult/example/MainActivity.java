package pers.zander.noonresult.example;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Arrays;

import pers.zander.noonresult.NoOnResultHelper;

public class MainActivity extends AppCompatActivity {

    private NoOnResultHelper.ActivityCallback activityCallback = new NoOnResultHelper.ActivityCallback() {
        @Override
        public void onActivityResult(int resultCode, Intent data) {
            //新界面
            Log.d("MainActivity", "resultCode:" + resultCode);
            Log.d("MainActivity", "Intent data:" + data.getStringExtra("text"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoOnResultHelper.setRequestCodeRange(65535, 200);


        Button mButton = findViewById(R.id.mButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, OneActivity.class);
                NoOnResultHelper.startActivityForResult(MainActivity.this, intent, activityCallback);
            }
        });

        Button mButtonPermission = findViewById(R.id.mButtonPermission);
        mButtonPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] permissions = {Manifest.permission.CAMERA};
                NoOnResultHelper.requestPermissions(MainActivity.this, permissions, new NoOnResultHelper.PermissionsCallBack() {
                    @Override
                    public void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
                        Log.d("MainActivity", "permissions:" + Arrays.toString(permissions) + "  grantResults :" + Arrays.toString(grantResults));
                    }
                });
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        OneFragment fragment = new OneFragment();
        fragmentTransaction.replace(R.id.mFrameLayout, fragment, "OneFragment");
        fragmentTransaction.commit();
    }

    private void test() {
        String[] permissions = {};
        NoOnResultHelper.requestPermissions(this, permissions, new NoOnResultHelper.PermissionsCallBack() {
            @Override
            public void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {

            }
        });
    }
}
