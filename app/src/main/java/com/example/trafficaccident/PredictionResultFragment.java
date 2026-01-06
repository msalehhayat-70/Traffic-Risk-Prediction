package com.example.trafficaccident;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PredictionResultFragment extends Fragment {

    private static final String ARG_PREDICTION_RESULT = "prediction_result";

    public static PredictionResultFragment newInstance(String predictionResult) {
        PredictionResultFragment fragment = new PredictionResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PREDICTION_RESULT, predictionResult);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prediction_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvPredictionResult = view.findViewById(R.id.tvPredictionResult);
        if (getArguments() != null) {
            String predictionResult = getArguments().getString(ARG_PREDICTION_RESULT);
            tvPredictionResult.setText(predictionResult);
        }
    }
}