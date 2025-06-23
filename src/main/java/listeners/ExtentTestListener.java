package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentManager;

public class ExtentTestListener implements ITestListener 
{
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() 
    {
        return test.get(); // 🔥 Getter for current test
    } 

    @Override
    public void onTestStart(ITestResult result) {
       // System.out.println("Listener started for: " + result.getMethod().getMethodName());
        System.out.println("Listener started for: " + result.getMethod().getMethodName());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ Test failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⚠️ Test skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Listener finished tests, flushing ExtentReports");
        extent.flush();  // very important!
    }
}
