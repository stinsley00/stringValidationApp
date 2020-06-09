package tinsley;


import tinsley.service.ValidationService;
import java.util.ResourceBundle;

public class ThreadController implements Runnable {
    /**
     * members
     */
    private final String argFromCmdLine;
    private Thread thread;
    private final String nameOfThread;
    private final ResourceBundle resource;
    /**
     * Constructor
     * @param name - name of thread
     * @param argFromCmdLine - argument from commandline
     * @param resource - i18 resource
     */
    public ThreadController(String name, String argFromCmdLine, ResourceBundle resource) {
        this.argFromCmdLine = argFromCmdLine;
        this.nameOfThread = name;
        this.resource = resource;
    }

    /**
     * run method
     */
    public void run() {
        //System.out.println("Running " + this.nameOfThread + " thread for argument named: " + this.argFromCmdLine );
        try {
            ValidationService validationService = new ValidationService(this.argFromCmdLine, this.resource);
            validationService.validation();
            validationService.validateLength(this.argFromCmdLine.length());
            validationService.validateSequence();
            validationService.displayResult();

        } catch (Exception e) {
            System.out.println(resource.getString("except"));
        }

    }

    /**
     * Start method
     */
    public void start () {

        if (thread == null) {
            thread = new Thread (this, this.nameOfThread);
            thread.start ();
        }
    }
}

