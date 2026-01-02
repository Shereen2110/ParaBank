package Login;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "validLogin")
    public Object[][] getData()
    {
        return new Object[][]{
                {"s_test","Test@123"}

        };
    }
    @DataProvider(name = "inValidLogin")
    public Object[][] getData2() {
        return new Object[][]{
                {"", "123456789"},
                {"sherry_test", ""},
                {"wronguser", "Test@123"},
                {"sherry_test", "wrongpass"},
                {"wronguser", "wrongpass"},
                {"user!@#", "pass$%^"}


        };
    }

}
