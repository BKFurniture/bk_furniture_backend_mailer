package com.bkfunirture.mailer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayProduct {
    private String name;
    private String color;
    private String size;
    private String imgUrl;
    private double unitPrice;
    private int quantity;
    private double subTotal;
}
