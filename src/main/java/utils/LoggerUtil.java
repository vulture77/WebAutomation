package utils;

import com.aventstack.extentreports.Status;
import listeners.ExtentTestListener;

public class LoggerUtil {
    public static void logInfo(String message) {
        System.out.println(message);  // Optional: still logs to console
        ExtentTestListener.getTest().log(Status.INFO, message);
    }

    public static void logPass(String message) {
        System.out.println(message);
        ExtentTestListener.getTest().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        System.out.println(message);
        ExtentTestListener.getTest().log(Status.FAIL, message);
    }
}