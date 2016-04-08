package com.halfplatepoha.ubersurge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by MacboolBro on 08/04/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsResponse {

    private List<Product> products;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Product {
        public int capacity;
        public String product_id;
        public PriceDetails price_details;
        private String image;
        private String short_description;
        private String display_name;
        private String description;

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public PriceDetails getPrice_details() {
            return price_details;
        }

        public void setPrice_details(PriceDetails price_details) {
            this.price_details = price_details;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class PriceDetails {
            private List<Service> services_fee;
            private float cost_per_minute;
            private String distance_unit;
            private float minimum;
            private float cost_per_distance;
            private float base;
            private float cancellation_fee;
            private String currency_code;

            public float getCost_per_minute() {
                return cost_per_minute;
            }

            public void setCost_per_minute(float cost_per_minute) {
                this.cost_per_minute = cost_per_minute;
            }

            public String getDistance_unit() {
                return distance_unit;
            }

            public void setDistance_unit(String distance_unit) {
                this.distance_unit = distance_unit;
            }

            public float getMinimum() {
                return minimum;
            }

            public void setMinimum(float minimum) {
                this.minimum = minimum;
            }

            public float getCost_per_distance() {
                return cost_per_distance;
            }

            public void setCost_per_distance(float cost_per_distance) {
                this.cost_per_distance = cost_per_distance;
            }

            public float getBase() {
                return base;
            }

            public void setBase(float base) {
                this.base = base;
            }

            public float getCancellation_fee() {
                return cancellation_fee;
            }

            public void setCancellation_fee(float cancellation_fee) {
                this.cancellation_fee = cancellation_fee;
            }

            public String getCurrency_code() {
                return currency_code;
            }

            public void setCurrency_code(String currency_code) {
                this.currency_code = currency_code;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Service {
                private String name;
                private float fee;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public float getFee() {
                    return fee;
                }

                public void setFee(float fee) {
                    this.fee = fee;
                }
            }
        }
    }

}
