package com.studentApp.junit;

import com.studentApp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest extends TestBase {


    @Title("This test will get all students")
    @Test
    public void getAllStudents(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void thisIsFailingTest(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(500);
    }

    @Pending
    @Test
    public void thisIsPendingTest(){

    }

    @Ignore
    @Test
    public void thisIsSkippedTest(){

    }

    @Test
    public void thisIsErrorTest(){
        System.out.println("This is error test:" + (5/0));
    }

    @Test
    public void fileDoesNotExist() throws FileNotFoundException{
        File file = new File("/Users/pritamsur/file.txt");
        FileReader fr = new FileReader(file);
    }

    @Manual
    @Test
    public void thisIsManualTest() {

    }
}
