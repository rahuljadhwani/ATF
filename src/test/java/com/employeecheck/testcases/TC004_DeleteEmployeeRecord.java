package com.employeecheck.testcases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeecheck.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC004_DeleteEmployeeRecord extends TestBase {

    @BeforeClass
    void getAllEmployees() {
        logger.info("**************** Starting DeleteEmployeeRecord *************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employees");

        JsonPath jsonPath = response.jsonPath();
        String empID = jsonPath.get("[3].id");
        response = httpRequest.request(Method.DELETE, "/delete/" + empID);
    }

    @Test
    void checkDeleteReponse() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("deleted records"));
        logger.info("One entry deleted");
    }

    @AfterClass
    void getAllEmployeesClosure() {
        logger.info("****************** Completed DeleteEmployeeRecord **************");
        System.out.println("Deepender");

    }


}
