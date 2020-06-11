package com.bookstore.customendpoint;

import com.bookstore.bean.custom.CustomInfo;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


@Component
@Endpoint(id = "custom-info")
public class CustomEndPoint{

    @ReadOperation
    public CustomInfo getCustomInfo(){
        Map<String, String> details = new LinkedHashMap<>();
        details.put("status","UP");
        details.put("Url","/custom-info");

        CustomInfo customInfo = new CustomInfo();
        customInfo.setDetails(details);
        return customInfo;
    }
}

