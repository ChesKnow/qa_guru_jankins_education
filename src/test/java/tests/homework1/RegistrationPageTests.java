package tests.homework1;

import org.junit.jupiter.api.Test;

public class RegistrationPageTests extends RegistrationForm{

    RegistrationForm registrationPage = new RegistrationForm();

    @Test
    void apfTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(studentEmail)
                .setUserNumber(studentNumber)
                .setCurrentAddress(currentAddress);
        registrationPage.setGender(gender)
                .setHobby(hobbies1)
                .setHobby(hobbies2);
        registrationPage.setBirthDate(day, month, year);
        registrationPage.enterSubject(subject);
        registrationPage.uploadPicture(pictureName);
        registrationPage.closeFixedBanner();
        registrationPage.setState(state);
        registrationPage.setCity(city);
        registrationPage.submitForm();
        registrationPage.checkSubmitOk();
        registrationPage.checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", studentEmail)
                .checkForm("Gender", gender)
                .checkForm("Mobile", studentNumber)
                .checkForm("Date of Birth", day + " " + month + "," + year)
                .checkForm("Subjects", subject)
                .checkForm("Hobbies", hobbies1 + ", " + hobbies2)
                .checkForm("Picture", pictureName)
                .checkForm("Address", currentAddress)
                .checkForm("State and City", state + " " + city);

    }
}
