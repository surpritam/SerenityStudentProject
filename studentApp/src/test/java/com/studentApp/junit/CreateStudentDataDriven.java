package com.studentApp.junit;

import com.studentApp.cucumber.serenity.StudentInfoStep;
import com.studentApp.testbase.TestBase;
import com.studentApp.utils.ReusableSpecifications;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

//@Concurrent(threads="4x")
@UseTestDataFrom("testdata/studentData.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDriven extends TestBase {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Steps
    StudentInfoStep steps;

    @Test
    public void createMultipleStudents(){

        ArrayList<String> courses = new ArrayList<>();
        courses.add(course);

        steps.createStudent(firstName,lastName,email,programme,courses)
                .statusCode(201)
                .spec(ReusableSpecifications.getGenericResponseSpec());
    }
}
