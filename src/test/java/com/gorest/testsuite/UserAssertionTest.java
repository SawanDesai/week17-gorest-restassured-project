package com.gorest.testsuite;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import  io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserAssertionTest {

    static ValidatableResponse response;
    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                // response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);


    }
//Verify the if the total record is 20

    @Test
    public void test001() {
        response.body("total.size", equalTo(20));
    }
//Verify the if the name of id = 5318 is equal to ”Rameshwar Varman"

    @Test
    public void test002(){
        response.body("[0]", hasEntry("name","Rameshwar Varman"));
    }

//Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003(){
        response.body("name", hasItem("Rameshwar Varman"));

    }

//Check the multiple ‘Names’ in the ArrayList (Aashritha Bhattathiri","Ganesh Kaniyar","Meghnad Jain

    @Test
    public void test004(){
        response.body("name", hasItems("Aashritha Bhattathiri","Ganesh Kaniyar","Meghnad Jain"));
    }

//Verify the emai of userid = 5312 is equal “ganesh_kaniyar@white.or”
@Test
    public void test005(){
    response.body("findAll{it.id == 5312}.email",hasItem("ganesh_kaniyar@white.org"));

}

//Verify the status is “Active” of user name is “Goswamee Tandon”
    @Test
    public void test006(){
        response.body("find{it.name == 'Goswamee Tandon'}.status",equalTo("active"));
    }

//Verify the Gender = male of user name is “Nagabhushanam Johar

    @Test
    public void test007(){
        response.body("find{it.name == 'Nagabhushanam Johar'}.gender",equalTo("male"));

    }

    }

