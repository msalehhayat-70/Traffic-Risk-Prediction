package com.example.trafficaccident;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class HomeActivity extends AppCompatActivity {

    private EditText etTraffic, etWeather, etTime, etRoadScore, etRisk;
    private Spinner spRoadCategory;
    private Button btnPredict;
    private Interpreter tflite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etTraffic = findViewById(R.id.etTrafficScore);
        etWeather = findViewById(R.id.etWeatherScore);
        etTime = findViewById(R.id.etTimeScore);
        etRoadScore = findViewById(R.id.etRoadTypeScore);
        etRisk = findViewById(R.id.etRiskFactor);
        spRoadCategory = findViewById(R.id.spRoadCategory);
        btnPredict = findViewById(R.id.btnPredict);

        // Load TFLite model
        try {
            tflite = new Interpreter(loadModelFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trafficStr = etTraffic.getText().toString();
                String weatherStr = etWeather.getText().toString();
                String timeStr = etTime.getText().toString();
                String roadScoreStr = etRoadScore.getText().toString();
                String riskFactorStr = etRisk.getText().toString();

                if (TextUtils.isEmpty(trafficStr) || TextUtils.isEmpty(weatherStr) ||
                        TextUtils.isEmpty(timeStr) || TextUtils.isEmpty(roadScoreStr) ||
                        TextUtils.isEmpty(riskFactorStr)) {
                    Toast.makeText(HomeActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                float traffic = Float.parseFloat(trafficStr);
                float weather = Float.parseFloat(weatherStr);
                float time = Float.parseFloat(timeStr);
                float roadScore = Float.parseFloat(roadScoreStr);
                float riskFactor = Float.parseFloat(riskFactorStr);

                // One-hot encode Road Category
                float[] roadCat = new float[5]; // 5 categories
                roadCat[spRoadCategory.getSelectedItemPosition()] = 1;

                // Combine all inputs
                float[] input = new float[]{traffic, weather, time, roadScore, riskFactor,
                        roadCat[0], roadCat[1], roadCat[2], roadCat[3], roadCat[4]};

                // The model output has 4 values, so we create a 1x4 array.
                float[][] output = new float[1][4];
                tflite.run(input, output);

                // Find the index with the highest score
                int maxIndex = 0;
                float maxConfidence = 0;
                for (int i = 0; i < output[0].length; i++) {
                    if (output[0][i] > maxConfidence) {
                        maxConfidence = output[0][i];
                        maxIndex = i;
                    }
                }

                // Assuming the 4 output values correspond to these risk levels.
                String[] riskLevels = {"Low", "Medium", "High", "Very High"};
                String result = "Predicted Risk: " + riskLevels[maxIndex];

                PredictionResultFragment resultFragment = PredictionResultFragment.newInstance(result);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, resultFragment);
                fragmentTransaction.commit();
            }
        });
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = getAssets().openFd("traffic_model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
}