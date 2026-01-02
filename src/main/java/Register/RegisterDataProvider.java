package Register;

import org.testng.annotations.DataProvider;

public class RegisterDataProvider {

    @DataProvider(name = "invalidRegisterData")
    public static Object[][] invalidRegisterData() {
        return new Object[][] {
                {"", "Ahmed", "10 Tahrir Street", "Cairo", "Giza", "12345", "01012345678", "123-45-6789", "sara_test", "Test@123"},
                {"Sara", "", "10 Tahrir Street", "Cairo", "Giza", "12345", "01012345678", "123-45-6789", "sara_test", "Test@123"},
                {"Sara", "Ahmed", "", "Cairo", "Giza", "12345", "01012345678", "123-45-6789", "sara_test", "Test@123"},
                {"Sara", "Ahmed", "10 Tahrir Street", "", "Giza", "12345", "01012345678", "123-45-6789", "sara_test", "Test@123"},
                {"Sara", "Ahmed", "10 Tahrir Street", "Cairo", "", "12345", "01012345678", "123-45-6789", "sara_test", "Test@123"},
                {"123", "Ahmed", "10 Tahrir Street", "Cairo", "Giza", "12345", "01012345678", "123-45-6789", "sara_test", "Test@123"},
        };
    }
}
