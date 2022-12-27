package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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

//Extract the All Ids

    @Test
    public void test001() {
        List<Integer> listofproducts = response.extract().path("id");
        System.out.println("all ids " + listofproducts);
    }

    //Extract the all Names
    @Test
    public void test002() {

        List<Integer> listOfNames = response.extract().path("name");
        System.out.println("all names" + listOfNames);
    }

//Extract the name of 5th Object

    @Test
    public void test003() {
        String Name = response.extract().path("[4].name");
        System.out.println("list of products are " + Name);
    }

    //Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> inActive = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("Name of inactive " + inActive);
    }

    //Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<?> listActive = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("Name of inactive " + listActive);
    }

    //Print the names of the object whose gender = female
    @Test
    public void test006() {

        List<?> listAll = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("Name of femal " + listAll);
    }

    //Get all the emails of the object where status = inactive

    @Test
    public void test007(){

        List<?> listAll = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("email of object "+ listAll);
    }
//Get the ids of the object where gender = male
    @Test
    public void test008(){

        List<?> listAll = response.extract().path("findall{it.gender == 'male'}.ids");
        System.out.println("list of ids :" + listAll);

    }

    @Test
    public void test009(){
        List<?> listAllStatus = response.extract().path("status");
        System.out.println("all status "+listAllStatus);
    }
    //Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test10(){
        List<?> listAllEmail = response.extract().path("findAll{it.name=='Aashritha Bhattathiri'}.email");
        System.out.println("all email "+listAllEmail);

    }
    //Get gender of id = 5471
    @Test
    public void test11(){
        List<?> listAllGender = response.extract().path("findAll{it.id==5310}.gender");
        System.out.println("all gender "+listAllGender);
    }
}