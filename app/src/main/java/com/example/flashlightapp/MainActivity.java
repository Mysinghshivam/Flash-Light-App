package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.flashlightapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.button.getText().toString().equals("ON FLASH LIGHT")){
                    binding.button.setText("OFF FLASH LIGHT");
                    binding.flashimg.setImageResource(R.drawable.onnlight);
                    //call function
                    changeLightstate(true);
                }else{
                    binding.button.setText("ON FLASH LIGHT");
                    binding.flashimg.setImageResource(R.drawable.offlightnew);
                    changeLightstate(false);
                }
            }
        });
    }

    private void changeLightstate(boolean state) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
          CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
          String camId = null;

          try {
              camId = cameraManager.getCameraIdList()[0];
              cameraManager.setTorchMode(camId, state);
          } catch (CameraAccessException e) {
              e.printStackTrace();
          }
      }
    }
}