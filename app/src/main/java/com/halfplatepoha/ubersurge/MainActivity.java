package com.halfplatepoha.ubersurge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.tvSurgePricing)TextView tvSurgePricing;
    @Bind(R.id.btnGetPrice)Button btnGetPrice;
    @Bind(R.id.ivProductImage)ImageView ivProductImage;
    @Bind(R.id.llProductContainer) LinearLayout llProductContainer;

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
        Call<PriceEstimateResponse> priceEstimateCall = uberService.getAllPriceEstimates(12.9555296, 77.7051441, 12.9555296, 77.7051441);

        priceEstimateCall.enqueue(new Callback<PriceEstimateResponse>() {
            @Override
            public void onResponse(Call<PriceEstimateResponse> call, Response<PriceEstimateResponse> response) {
                if(response.isSuccessful()) {
                    estimates = response.body().getPrices();
                    getProductButtonVHObservable().subscribe(buttonViewHolderSubscriber);
                } else {
                    showShortToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<PriceEstimateResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    @RxLogObservable
    private Observable<ProductButtonViewHolder> getProductButtonVHObservable() {
        return Observable.just(estimates)
                .flatMap(new Func1<List<PriceEstimateResponse.Price>, Observable<PriceEstimateResponse.Price>>() {
                    @Override
                    public Observable<PriceEstimateResponse.Price> call(List<PriceEstimateResponse.Price> prices) {
                        return Observable.from(prices);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<PriceEstimateResponse.Price, ProductButtonViewHolder>() {
                    @Override
                    public ProductButtonViewHolder call(PriceEstimateResponse.Price price) {
                        ProductButtonViewHolder vh = new ProductButtonViewHolder();
                        return vh.bindPriceResponse(price);
                    }
                });
    }

    private void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public class ProductButtonViewHolder implements View.OnClickListener{

        @Bind(R.id.btnProductInfo)Button btnProductInfo;
        private View layoutView;

        public ProductButtonViewHolder() {
            layoutView = LayoutInflater.from(MainActivity.this).inflate(R.layout.product_button, null, false);
            ButterKnife.bind(this, layoutView);

            btnProductInfo.setOnClickListener(this);
        }

        public View getLayoutView() {
            return this.layoutView;
        }

        public ProductButtonViewHolder bindPriceResponse(PriceEstimateResponse.Price price) {
            btnProductInfo.setText(price.getDisplay_name());
            btnProductInfo.setTag(price.getProduct_id());
            return this;
        }

        @Override
        public void onClick(View v) {
            String productId = (String)v.getTag();
            callProductImageAPIs(productId);
        }
    }

    private void callProductImageAPIs(String productId) {
        Call<ProductResponse> productResponse = uberService.getProduct(productId, 12.9555296, 77.7051441);

        productResponse.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()) {
                    Picasso.with(MainActivity.this)
                            .load(response.body().getImage())
                            .into(ivProductImage);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private Subscriber<ProductButtonViewHolder> buttonViewHolderSubscriber = new Subscriber<ProductButtonViewHolder>() {
        @Override
        public void onCompleted() {}

        @Override
        public void onError(Throwable e) {}

        @Override
        public void onNext(ProductButtonViewHolder productButtonViewHolder) {
            llProductContainer.addView(productButtonViewHolder.getLayoutView());
        }
    };

}
