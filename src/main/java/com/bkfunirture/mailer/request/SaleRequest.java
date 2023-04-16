package com.bkfunirture.mailer.request;

import com.bkfunirture.mailer.model.SaleProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class SaleRequest implements Serializable {
    private double discountPercentage;
    private String discountCode;
    private int usageLimit;
    private String[] userMails;
}
