package com.studentApp.cucumber.serenity;

import com.studentApp.model.Student;
import com.studentApp.testbase.TestBase;
import com.studentApp.utils.ReusableSpecifications;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentInfoStep extends TestBase {

    @Step("To create a student with First name:{0}, Last Name:{1},Email:{2},Programme:{3},Courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, ArrayList<String> courses){

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .post()
                .then();
    }

    @Step
    public HashMap<String,Object> getStudentInfoByName(String firstName){
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";
        return SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
    }

    @Step
    public ValidatableResponse updateStudent(int id,String firstName, String lastName, String email, String programme, ArrayList<String> courses){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .put("/" + id)
                .then();
    }

    @Step
    public void deleteStudentById(int studentId){

        SerenityRest.rest().given()
                .when()
                .delete("/"+studentId);

    }

    @Step
    public ValidatableResponse getStudentById(int studentId){
        return SerenityRest.rest().given()
                .when()
                .get("/"+studentId).then();
    }

}
