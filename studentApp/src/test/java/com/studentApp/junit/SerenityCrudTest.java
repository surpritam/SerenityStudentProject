package com.studentApp.junit;

import com.studentApp.cucumber.serenity.StudentInfoStep;
import com.studentApp.testbase.TestBase;
import com.studentApp.utils.ReusableSpecifications;
import com.studentApp.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SerenityCrudTest extends TestBase{

    static String firstName = "BlissPanda" + TestUtils.getRandomValue();
    static String lastName = "Pogo" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "abc@gmail.com";
    static String programme = "Computer Science";
    static int studentId;

    @Steps
    StudentInfoStep steps;

    @Title("Test to create student")
    @WithTag("studentFeature:SMOKE")
    @Test
    public void test001(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C++");

        steps.createStudent(firstName,lastName,email,programme,courses)
                .statusCode(201)
                .spec(ReusableSpecifications.getGenericResponseSpec());


    }

    @Title("Test to verify added student")
    @WithTag("studentFeature:SMOKE")
    @Test
    public void test002(){

        HashMap<String,Object> value = steps.getStudentInfoByName(firstName);
        System.out.println(value);

        assertThat(value,hasValue(firstName));
        studentId = (int) value.get("id");

    }


    @Title("Test to update student")
    @WithTag("studentFeature:SMOKE")
    @Test
    public void test003(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("Machine Learning");
        firstName = firstName + "_updated";
        steps.updateStudent(studentId,firstName,lastName,email,programme,courses)
                .statusCode(200);
        assertThat(steps.getStudentInfoByName(firstName),hasValue(firstName));

    }

    @Title("Test to delete student")
    @WithTags(
            {
                    @WithTag("studentFeature:SMOKE"),
                    @WithTag("studentFeature:NEGATIVE")
            }
    )
    @WithTag("studentFeature:SMOKE")
    @Test
    public void test004(){
        steps.deleteStudentById(studentId);
        steps.getStudentById(studentId).statusCode(404);

    }
}
