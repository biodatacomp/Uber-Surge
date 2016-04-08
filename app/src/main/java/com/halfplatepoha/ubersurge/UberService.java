package com.halfplatepoha.ubersurge;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by MacboolBro on 08/04/16.
 */
public interface UberService {

    //--Products
    @GET("v1/products/{productId}")
    Call<ProductResponse> getProduct(
            @Path("productId") String productId,
            @Query("latitude") double latitude,
            @Query("longitude") double longitude
    );

    //--Price Estimates

    @GET("/v1/estimates/price")
    Observable<PriceEstimateResponse> getAllPriceEstimates(
            @Query("start_latitude") double startLatitude,
            @Query("start_longitude") double startLongitude,
            @Query("end_latitude") double endLatitude,
            @Query("end_longitude") double endLongitude
    );
}
