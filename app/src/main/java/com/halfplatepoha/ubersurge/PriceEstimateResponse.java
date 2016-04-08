package com.halfplatepoha.ubersurge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by MacboolBro on 08/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceEstimateResponse {

    private List<Price> prices;

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> priceEstimates) {
        this.prices = priceEstimates;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Price {

        private String localized_display_name;
        private String product_id;
        private String currency_code;
        private String display_name;
        private String estimate;
        private int low_estimate;
        private int high_estimate;
        private float surge_multiplier;
        private int duration;
        private float distance;

        public String getLocalized_display_name() {
            return localized_display_name;
        }

        public void setLocalized_display_name(String localized_display_name) {
            this.localized_display_name = localized_display_name;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getCurrency_code() {
            return currency_code;
        }

        public void setCurrency_code(String currency_code) {
            this.currency_code = currency_code;
        }

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getEstimate() {
            return estimate;
        }

        public void setEstimate(String estimate) {
            this.estimate = estimate;
        }

        public int getLow_estimate() {
            return low_estimate;
        }

        public void setLow_estimate(int low_estimate) {
            this.low_estimate = low_estimate;
        }

        public int getHigh_estimate() {
            return high_estimate;
        }

        public void setHigh_estimate(int high_estimate) {
            this.high_estimate = high_estimate;
        }

        public float getSurge_multiplier() {
            return surge_multiplier;
        }

        public void setSurge_multiplier(float surge_multiplier) {
            this.surge_multiplier = surge_multiplier;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public float getDistance() {
            return distance;
        }

        public void setDistance(float distance) {
            this.distance = distance;
        }
    }
}
