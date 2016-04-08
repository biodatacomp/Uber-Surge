package com.halfplatepoha.ubersurge;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MacboolBro on 08/04/16.
 */
public interface UberService {

    //--Products
    Call<ProductsResponse> getAllProducts(
            @Query("latitude") float latitude,
            @Query("longitude") float longitude
    );

    //--Price Estimates

    @GET("/v1/estimates/price")
    Call<PriceEstimateResponse> getAllPriceEstimates(
            @Query("start_latitude") double startLatitude,
            @Query("start_longitude") double startLongitude,
            @Query("end_latitude") double endLatitude,
            @Query("end_longitude") double endLongitude
    );
}
