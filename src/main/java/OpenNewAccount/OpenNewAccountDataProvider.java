package OpenNewAccount;

import org.testng.annotations.DataProvider;

public class OpenNewAccountDataProvider {
    @DataProvider(name = "Checking")
    public Object[][] getData()
    {
        return new Object[][]{
                {"CHECKING"}

        };
    }
    @DataProvider(name = "Savings")
    public Object[][] getData2() {
        return new Object[][]{
                {"SAVINGS"}

        };
    }
    @DataProvider(name = "InvalidAccountType")
    public Object[][] getInvalidAccountType() {
        return new Object[][] {
                {"InvalidType1"},
                {""},
                {"12345"}
        };
    }

}
