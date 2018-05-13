package com.studentApp.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class ReusableSpecifications {

    public static RequestSpecBuilder reqSpec;
    public static RequestSpecification requestSpecification;

    public static ResponseSpecBuilder reSpec;
    public static ResponseSpecification responseSpecification;

    public static RequestSpecification getGenericRequestSpec(){
        reqSpec = new RequestSpecBuilder();
        reqSpec.setContentType(ContentType.JSON);
        requestSpecification = reqSpec.build();
        return requestSpecification;

    }

    public static ResponseSpecification getGenericResponseSpec(){
        reSpec = new ResponseSpecBuilder();
        reSpec.expectHeader("Content-Type","application/json;charset=UTF-8");
        reSpec.expectHeader("Transfer-Encoding","chunked");
        reSpec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
        responseSpecification = reSpec.build();
        return responseSpecification;

    }
}
