package com.bkfunirture.mailer.request;

import com.bkfunirture.mailer.model.PayProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PayRequest implements Serializable {
    private String address;
    private String phone;
    private String payMethod;
    private String shippingOption;
    private String deliveryDate;
    private List<PayProduct> payProducts;
    private double subTotal;
    private double productDiscount;
    private double shippingFee;
    private double shippingDiscount;
    private double total;
}
