package BillPay;

import org.testng.annotations.DataProvider;

public class BillpayDataProvider {

    @DataProvider(name = "validBillPayAmounts")
    public Object[][] getBillPayData1() {
        return new Object[][] {
                { "500" },
                { "1000" },
                { "1500" },
                { "2000" }
        };
    }
    @DataProvider(name = "inValidBillPayAmounts")
    public Object[][] getBillPayData2() {
        return new Object[][] {
                { "0" },
                { "-100" },
                { "" },
                { "abc" },
                { "1000000000" }
        };
    }

}
