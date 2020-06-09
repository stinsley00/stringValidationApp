package unittests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tinsley.service.ValidationService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class ValidatorUnitTest {
    /**
     *
     * members
     *
     */
    private final ByteArrayOutputStream originalOutput = new ByteArrayOutputStream();
    private final PrintStream cmdOut = System.out;

    /**
     *
     * constructor
     */
    public ValidatorUnitTest(){

    }

    /**
     * before and afters for the command line output test
     */


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(originalOutput));
    }

    @After
    public void restoreStreams() {
        System.setOut(cmdOut);
    }
    /**
     *
     * tests
     *
     */

    @Test
    public void testStringLengthMethod(){
        // String must be between 5 and 12 characters in length.
        //12 char test
        String input = "SsJk213";
        String language = "en";
        Locale locale = new Locale(language);
        ResourceBundle resource = ResourceBundle.getBundle("i18N", locale);
        ValidationService validationService = new ValidationService(input, resource);

        Boolean result = validationService.validateLength(12);
        Assert.assertTrue(result);
        //5 char test
        result = validationService.validateLength(5);
        Assert.assertTrue(result);

        //outside allotted length tests
        result = validationService.validateLength(2);
        Assert.assertFalse(result);

        result = validationService.validateLength(15);
        Assert.assertFalse(result);
    }

    @Test
    public void testCharMix(){
        String input = "SsJk213";
        String language = "en";
        Locale locale = new Locale(language);
        ResourceBundle resource = ResourceBundle.getBundle("i18N", locale);

        //true case
        ValidationService validationService = new ValidationService(input, resource);
        Boolean res = validationService.validation();
        Assert.assertTrue(res);

        //false case
        input = "1234456";
        validationService = new ValidationService(input, resource);
        res = validationService.validation();
        Assert.assertFalse(res);

    }

    @Test
    public void testSequence(){
        String validSeq = "SsJk213";
        String invalidSeq = "SSSS1212";
        String language = "en";
        Locale locale = new Locale(language);
        ResourceBundle resource = ResourceBundle.getBundle("i18N", locale);

        ValidationService validationService = new ValidationService(validSeq, resource);
        Boolean res = validationService.validateSequence();
        Assert.assertTrue(res);

        validationService = new ValidationService(invalidSeq, resource);
        res = validationService.validateSequence();
        Assert.assertFalse(res);
    }

    @Test
    public void testDisplay(){
        String validSeq = "aabbaa1";
        String language = "en";
        Locale locale = new Locale(language);
        ResourceBundle resource = ResourceBundle.getBundle("i18N", locale);
        String regex = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
        String validStr = "ValidationResults:{\n" +
                "userInput=aabbaa1,\n" +
                "isValidLength=true,\n" +
                "lengthReason=Requirement Met,\n" +
                "isValidMixOfCharacters=true,\n" +
                "mixOfCharactersReason=Character Mix OK,\n" +
                "isValidSequence=true,\n" +
                "ValidSeqReason=Sequence is valid,\n" +
                " id= myid\n" +
                "}";
        validStr = validStr.replaceAll("\\n", "");
        ValidationService validationService = new ValidationService(validSeq, resource);
        validationService.validateSequence();
        validationService.validateLength(validSeq.length());
        validationService.validation();
        validationService.displayResult();
        //compare to console output with some iD doctoring (id different each time)
        String noIdString = originalOutput.toString().replaceAll(regex, "myid").replaceAll("\\n", "");
        Assert.assertEquals(validStr, noIdString);
    }

    @Test
    public void testI18(){
        String language = "es";
        Locale locale = new Locale(language);
        ResourceBundle resource = ResourceBundle.getBundle("i18N", locale);
        Assert.assertEquals(resource.getString("true"), "verdad");
    }

}
