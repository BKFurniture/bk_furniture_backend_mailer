package com.bkfunirture.mailer.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DesignRequest {
    private List<String> imgUrls;
    private String description;
}
