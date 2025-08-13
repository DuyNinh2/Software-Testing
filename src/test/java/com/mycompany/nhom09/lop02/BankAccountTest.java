/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.nhom09.lop02;

import com.mycompany.nhom09.lop02.BankAccountTest.ConstructorTest;
import com.mycompany.nhom09.lop02.BankAccountTest.GettersSettersAccountHolderTest;
import com.mycompany.nhom09.lop02.BankAccountTest.GettersSettersAccountNumberTest;
import com.mycompany.nhom09.lop02.BankAccountTest.GettersSettersBalanceTest;
import com.mycompany.nhom09.lop02.BankAccountTest.DepositTest;
import com.mycompany.nhom09.lop02.BankAccountTest.TransferTest;
import com.mycompany.nhom09.lop02.BankAccountTest.WithdrawTest;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import static org.junit.Assert.*;

/**
 *
 * @author duy
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ConstructorTest.class, GettersSettersAccountHolderTest.class, GettersSettersAccountNumberTest.class,
    GettersSettersBalanceTest.class, DepositTest.class, WithdrawTest.class, TransferTest.class})
public class BankAccountTest {

    private static final String CSV_FILE_PATH = "C:\\Users\\hieub\\Downloads\\Nhom09-Lop02\\JUnitTestResult.csv";

    @BeforeClass
    public static void setUpClass() {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            writer.append("Test case ID,Result\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logTestResultToCSV(String testCaseID, Boolean testResult) {
        String resultMessage = testResult ? "PASS" : "FAILED";
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true)) {
            writer.append(testCaseID)
                    .append(',')
                    .append(resultMessage)
                    .append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RunWith(Parameterized.class)
    public static class ConstructorTest {

        private String testCaseID;
        private String accountNumber;
        private String accountHolder;
        private double initialBalance;
        private String expectedExceptionMessage;

        public ConstructorTest(String testCaseID, String accountNumber, String accountHolder, double initialBalance,
                String expectedExceptionMessage) {
            this.testCaseID = testCaseID;
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.initialBalance = initialBalance;
            this.expectedExceptionMessage = expectedExceptionMessage;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> testData() {
            return Arrays.asList(new Object[][]{
                {"CT009", "123456789012", "Johny", -300.0, "Số dư ban đầu không thể là số âm."},
                {"CT010", "789012", "Johnny Deep", 330.0, "Số tài khoản phải có đúng 12 chữ số."},
                {"CT011", "123456789012", "", 330.0, "Tên chủ tài khoản không được để trống."},
                {"CT012", "123456789012", " ", 1000.0, null}
            });
        }

        @BeforeClass
        public static void setUpClass() {
            System.out.println("ConstructorTest\n");
        }

        @AfterClass
        public static void tearDownClass() {
            System.out.println("ConstructorTest completed\n");
        }

        @Before
        public void setUp() {
        }

        @After
        public void tearDown() {
        }

        @Test
        public void testConstructor() {
            boolean isTestPassed = false;
            try {
                BankAccount actResult = new BankAccount(accountNumber, accountHolder, initialBalance);
                isTestPassed = expectedExceptionMessage == null
                        && actResult.getAccountNumber().equals(accountNumber)
                        && actResult.getAccountHolder().equals(accountHolder)
                        && actResult.getBalance() == initialBalance;
                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);
                assertTrue("Constructor không khởi tạo đúng giá trị thuộc tính.", isTestPassed);
            } catch (IllegalArgumentException e) {
                isTestPassed = e.getMessage().equals(expectedExceptionMessage);
                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);
                assertTrue("Thông báo ngoại lệ mong đợi: \"" + expectedExceptionMessage
                        + "\", nhưng nhận được: \"" + e.getMessage() + "\".", isTestPassed);
            }
        }
    }

    @RunWith(Parameterized.class)
    public static class GettersSettersAccountHolderTest {

        private String testCaseID;
        private String accountNumber;
        private String accountHolder;
        private double initialBalance;
        private String newAccountHolder;
        private Class<? extends Exception> expException;
        private String expMessage;
        private BankAccount actResult;

        public GettersSettersAccountHolderTest(String testCaseID, String accountNumber, String accountHolder, double initialBalance, String newAccountHolder, Class<? extends Exception> expException, String expMessage) {
            this.testCaseID = testCaseID;
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.initialBalance = initialBalance;
            this.newAccountHolder = newAccountHolder;
            this.expException = expException;
            this.expMessage = expMessage;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> testData() {
            return Arrays.asList(new Object[][]{
                {"SH005", "123456321222", "Alice", 1000.0, null, IllegalArgumentException.class, "Tên chủ tài khoản không được để trống."},
                {"SH006", "123456321222", "Alice", 1000.0, " ", IllegalArgumentException.class, "Tên chủ tài khoản không được để trống."},
                {"SH007", "123456321222", "Alice", 1000.0, "Bob123", IllegalArgumentException.class, "Tên chủ tài khoản không hợp lệ. Nó chỉ được phép chứa chữ cái và khoảng trắng."},
                {"SH008", "123456321222", "Alice", 1000.0, "Bob", null, null},});
        }

        @Before
        public void setUp() {
            actResult = new BankAccount(accountNumber, accountHolder, initialBalance);
        }

        @After
        public void tearDown() {
        }

        @Test
        public void testGettersSettersAccountHolder() {
            boolean isTestPassed;
            try {
                actResult.setAccountHolder(newAccountHolder);
                isTestPassed = expException == null;
                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);
                assertTrue("Không mong đợi ngoại lệ, nhưng đã xảy ra ngoại lệ.", isTestPassed);
            } catch (Exception e) {
                isTestPassed = expException != null
                        && expException.isInstance(e)
                        && e.getMessage().equals(expMessage);
                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);
                assertTrue("Ngoại lệ mong đợi: " + (expException != null ? expException.getName() : "null")
                        + ", nhưng nhận được: " + e.getClass().getName()
                        + " với thông báo: " + e.getMessage(),
                        isTestPassed);
            }

        }
    }

    @RunWith(Parameterized.class)
    public static class GettersSettersAccountNumberTest {

        private String testCaseID;
        private String accountNumber;
        private String expectedAccountNumber;
        boolean testResult;
        private BankAccount account;

        public GettersSettersAccountNumberTest(String testCaseID, String accountNumber, String expectedAccountNumber) {
            this.testCaseID = testCaseID;
            this.accountNumber = accountNumber;
            this.expectedAccountNumber = expectedAccountNumber;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> testData() {
            return Arrays.asList(new Object[][]{
                {"SN005", "123456789012", null},
                {"SN006", " ", null},
                {"SN007", "12345678901234", null},
                {"SN008", "987654321012", "987654321012"}
            });
        }

        @BeforeClass
        public static void setUpClass() {
            System.out.println("AccountNumberTest\n");
        }

        @AfterClass
        public static void tearDownClass() {
            System.out.println("AccountNumberTest completed\n");
        }

        @Before
        public void setUp() {
            if (expectedAccountNumber != null) {
                account = new BankAccount(accountNumber, "John Doe", 1000.0);
            } else {
                account = null;
            }
            testResult = false;
        }

        @After
        public void tearDown() {
            logTestResultToCSV(testCaseID, testResult);
        }

        @Test
        public void testGettersSettersAccountNumber() {
            try {
                if (expectedAccountNumber != null) {
                    assertEquals(expectedAccountNumber, account.getAccountNumber());
                    account.setAccountNumber(accountNumber);
                    assertEquals(expectedAccountNumber, account.getAccountNumber());
                    testResult = true;
                } else {
                    assertThrows(IllegalArgumentException.class, () -> new BankAccount(accountNumber, "John Doe", 1000.0));
                    if (account != null) {
                        assertThrows(IllegalArgumentException.class, () -> account.setAccountNumber(accountNumber));
                    }
                    testResult = true;
                }
            } catch (AssertionError | IllegalArgumentException e) {
                testResult = false;
            }
        }
    }

    @RunWith(Parameterized.class)
    public static class GettersSettersBalanceTest {

        private String testCaseID;
        private String accountNumber;
        private String accountHolder;
        private double initialBalance;
        private double newBalance;
        private Class<? extends Exception> expException;
        private String expMessage;
        private BankAccount actResult;

        public GettersSettersBalanceTest(String testCaseID, String accountNumber, String accountHolder,
                double initialBalance, double newBalance,
                Class<? extends Exception> expException, String expMessage) {
            this.testCaseID = testCaseID;
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.initialBalance = initialBalance;
            this.newBalance = newBalance;
            this.expException = expException;
            this.expMessage = expMessage;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> testData() {
            return Arrays.asList(new Object[][]{
                {"BL001", "121365856254", "Dave", 500.0, 600.0, null, null},
                {"BL002", "352265478963", "Alice", 400.0, -100.0, IllegalArgumentException.class, "Số dư không thể là số âm."},
                {"BL003", "121365856254", "Dave", 500.0, "sdsd", IllegalArgumentException.class, null}
            });
        }

        @BeforeClass
        public static void setUpClass() {
            System.out.println("GettersSettersBalance\n");
        }

        @AfterClass
        public static void tearDownClass() {
            System.out.println("GettersSettersBalance completed\n");
        }

        @Before
        public void setUp() {
            actResult = new BankAccount(accountNumber, accountHolder, initialBalance);
        }

        @After
        public void tearDown() {
        }

        @Test
        public void testGettersSettersBalance() {
            boolean isTestPassed;
            try {
                actResult.setBalance(newBalance);

                isTestPassed = expException == null;

                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);

                assertTrue("Không mong đợi ngoại lệ, nhưng đã xảy ra ngoại lệ.", isTestPassed);
            } catch (Exception e) {
                isTestPassed = expException != null
                        && expException.isInstance(e)
                        && e.getMessage().equals(expMessage);

                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);

                assertTrue("Ngoại lệ mong đợi: " + (expException != null ? expException.getName() : "null")
                        + ", nhưng nhận được: " + e.getClass().getName()
                        + " với thông báo: " + e.getMessage(),
                        isTestPassed);
            }
        }
    }

    @RunWith(Parameterized.class)
    public static class DepositTest {

        private String testCaseID;
        private String accountNumber;
        private String accountHolder;
        private double initialBalance;
        private double depositAmount;
        private Class<? extends Exception> expException;
        private String expMessage;
        private BankAccount actResult;

        public DepositTest(String testCaseID, String accountNumber, String accountHolder, double initialBalance, double depositAmount, Class<? extends Exception> expException, String expMessage) {
            this.testCaseID = testCaseID;
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.initialBalance = initialBalance;
            this.depositAmount = depositAmount;
            this.expException = expException;
            this.expMessage = expMessage;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> testData() {
            return Arrays.asList(new Object[][]{
                {"DP005", "123456321222", "Alice", 1000.0, -100.0, IllegalArgumentException.class, "Số tiền gửi phải là số dương."},
                {"DP006", "123456321222", "Alice", 1000.0, 0.0, IllegalArgumentException.class, "Số tiền gửi phải là số dương."},
                {"DP007", "123456321222", "Alice", 1000.0, 500.0, null, null},});
        }

        @Before
        public void setUp() {
            actResult = new BankAccount(accountNumber, accountHolder, initialBalance);
        }

        @After
        public void tearDown() {
        }

        @Test
        public void testDeposit() {
            boolean isTestPassed;
            try {
                actResult.deposit(depositAmount);

                isTestPassed = expException == null;

                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);

                assertTrue("Không mong đợi ngoại lệ, nhưng đã xảy ra ngoại lệ.", isTestPassed);
            } catch (Exception e) {
                isTestPassed = expException != null
                        && expException.isInstance(e)
                        && e.getMessage().equals(expMessage);

                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);

                assertTrue("Ngoại lệ mong đợi: " + (expException != null ? expException.getName() : "null")
                        + ", nhưng nhận được: " + e.getClass().getName()
                        + " với thông báo: " + e.getMessage(),
                        isTestPassed);
            }
        }
    }

    @RunWith(Parameterized.class)
    public static class WithdrawTest {

        private String testCaseID;
        private double initialBalance;
        private double withdrawAmount;
        private double expectedBalance;
        private String expMessage;
        boolean testResult;
        private BankAccount account;

        public WithdrawTest(String testCaseID, double initialBalance, double withdrawAmount, double expectedBalance, String expMessage) {
            this.testCaseID = testCaseID;
            this.initialBalance = initialBalance;
            this.withdrawAmount = withdrawAmount;
            this.expectedBalance = expectedBalance;
            this.expMessage = expMessage;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                {"WD001", 1000, 500, 500, ""},
                {"WD002", 2000, -100, 2000, "Số tiền rút phải là số dương."},
                {"WD003", 1800, 2200, 1800, "Số dư không đủ."},
                {"WD004", 3000, 0, 3000, "Số tiền rút phải là số dương."},
                {"WD005", 199, 200, 199, "Số dư không đủ."},
                {"WD006", 370, 369, 1, ""},
                {"WD007", 500, -1, 500, "Số tiền rút phải là số dương."},
                {"WD008", 900, 1000, 900, "Số dư không đủ."},
                {"WD009", 782, 527, 255, ""}
            });
        }

        @Before
        public void setUp() {
            account = new BankAccount("123456789012", "John Doe", initialBalance);
            testResult = false;
        }

        @After
        public void tearDown() {
        }

        @Test
        public void testWithdraw() {
            boolean isTestPassed;
            try {
                double actualBalance = account.withdraw(withdrawAmount);
                assertEquals("Số dư sau khi rút không đúng với kỳ vọng", expectedBalance, actualBalance, 0.001);
                assertTrue("Thông điệp ngoại lệ không đúng", expMessage.isEmpty());
                isTestPassed = true;
                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);
            } catch (Exception e) {

                assertEquals("Kết quả thực tế khác với kỳ vọng", expMessage, e.getMessage());

                isTestPassed = e.getMessage().equals(expMessage);
                BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);
            }
        }
    }

    @RunWith(Parameterized.class)
    public static class TransferTest {

        private String testCaseID;
        private BankAccount sender;
        private BankAccount recipient;
        private double transferAmount;
        private boolean expResult;
        public TransferTest(String testCaseID, String accountNumberSender, String accountHolderSender, double initialBalanceSender,
                String accountNumberRecipient, String accountHolderRecipient, double initialBalanceRecipient,
                double transferAmount, boolean expResult) {
            this.testCaseID = testCaseID;
            this.sender = new BankAccount(accountNumberSender, accountHolderSender, initialBalanceSender);
            this.transferAmount = transferAmount;
            this.expResult = expResult;
            if (accountNumberRecipient != null && accountHolderRecipient != null) {
                this.recipient = new BankAccount(accountNumberRecipient, accountHolderRecipient, initialBalanceRecipient);
            } else {
                this.recipient = null;  
            }
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> testData() {
            return Arrays.asList(new Object[][]{
                {"TF001", "145254745785", "Alice", 1000.0, "120023587589", "Bob", 500.0, 200.0, true},
                {"TF002", "145254745785", "Alice", 1000.0, null, null, 0.0, 200.0, true},
                {"TF003", "145254745785", "Alice", 1000.0, "120023587589", "Bob", 500.0, 1500.0, false},
                {"TF004", "145254745785", "Alice", 1000.0, "120023587589", "Bob", 500.0, -100.0, false}
            });
        }

        @BeforeClass
        public static void setUpClass() {
            System.out.println("Transfer\n");
        }

        @AfterClass
        public static void tearDownClass() {
            System.out.println("Transfer completed\n");
        }

        @Before
        public void setUp() {
        }

        @After
        public void tearDown() {
        }

        @Test
        public void testTransfer() {
            boolean result;
            boolean isTestPassed;
            if (recipient == null) {
                result = false;
                isTestPassed = result == expResult;
            } else {
                result = sender.transfer(recipient, transferAmount);
                isTestPassed = result == expResult
                        && sender.getBalance() == (expResult ? 1000.0 - transferAmount : sender.getBalance())
                        && recipient.getBalance() == (expResult ? 500.0 + transferAmount : recipient.getBalance());
            }
            BankAccountTest.logTestResultToCSV(testCaseID, isTestPassed);
            assertEquals("Kết quả chuyển khoản không khớp", expResult, result);
            assertEquals("Số dư tài khoản gửi không khớp", expResult ? 1000.0 - transferAmount : sender.getBalance(), sender.getBalance(), 0.01);
            if (recipient != null) {
                assertEquals("Số dư tài khoản nhận không khớp", expResult ? 500.0 + transferAmount : recipient.getBalance(), recipient.getBalance(), 0.01);
            }
        }
    }
}
