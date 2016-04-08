package com.halfplatepoha.ubersurge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.tvSurgePricing)TextView tvSurgePricing;
    @Bind(R.id.btnGetPrice)Button btnGetPrice;

    private UberService uberService;
    private List<PriceEstimateResponse.Price> estimates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        uberService = ServiceGenerator.createService(UberService.class);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btnGetPrice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetPrice:{
                callPriceEstimateAPI();
            }
            break;
        }
    }

    private void callPriceEstimateAPI() {
        Call<PriceEstimateResponse> call = uberService.getAllPriceEstimates(12.9555296, 77.7051441, 12.9555296, 77.7051441);

        call.enqueue(new Callback<PriceEstimateResponse>() {
            @Override
            public void onResponse(Call<PriceEstimateResponse> call, Response<PriceEstimateResponse> response) {
                Log.d(TAG, response.body().toString());
                estimates = response.body().getPrices();

                tvSurgePricing.setText(estimates.get(0).getSurge_multiplier() + "");
            }

            @Override
            public void onFailure(Call<PriceEstimateResponse> call, Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        });
    }
}
