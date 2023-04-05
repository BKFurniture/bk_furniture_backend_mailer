package com.bkfunirture.mailer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class SaleProduct implements Serializable {
    private String name;
    private String imgUrl;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDate;
    private String dateString;
    private double oldCost;
    private double newCost;
    private double salePercent;
}
