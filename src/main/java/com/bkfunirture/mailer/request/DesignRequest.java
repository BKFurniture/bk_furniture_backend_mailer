package com.bkfunirture.mailer.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DesignRequest {
    private String imgUrl;
    private String description;
}
