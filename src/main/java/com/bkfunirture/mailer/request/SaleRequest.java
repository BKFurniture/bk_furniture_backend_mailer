package com.bkfunirture.mailer.request;

import com.bkfunirture.mailer.model.SaleProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class SaleRequest implements Serializable {
    private String title;
    private String imgUrl;
    private String description;
    private List<SaleProduct> saleProducts;
}
