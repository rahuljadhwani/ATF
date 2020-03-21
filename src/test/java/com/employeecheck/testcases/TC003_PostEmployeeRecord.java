package com.employeecheck.testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeecheck.base.TestBase;
import com.employeecheck.utils.RESTUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC003_PostEmployeeRecord extends TestBase {

    String empName = RESTUtils.empName();
    String empSalary = RESTUtils.empSalary();
    String empAge = RESTUtils.empAge();
    JsonPath jsonPath;


    @BeforeClass
    void getAllEmployees() {
        logger.info("**************** Starting CreateEmployeeRecord *************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", empName);
        requestParams.put("salary", empSalary);
        requestParams.put("age", empAge);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.POST, "/create");
        jsonPath = response.jsonPath();
        System.out.println(response.getBody().asString());
    }


    @Test
    void checkResponseBody() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(empName));
        Assert.assertEquals(jsonPath.get("name"), empName);
        Assert.assertTrue(responseBody.contains(empSalary));
        Assert.assertEquals(jsonPath.get("salary"), empSalary);

        logger.warn("Response body contains " + empName + " and his/her salary " + empSalary);

    }


    @Test
    void checkStatusCode() {
        logger.info("**************** Checking Status Code **************");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        logger.info("Status Code: " + statusCode);


    }

    @AfterClass
    void getAllEmployeesClosure() {
        logger.info("****************** Completed CreateEmployeeRecord **************");

    }

}
 