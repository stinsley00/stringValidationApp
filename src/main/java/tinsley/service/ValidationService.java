package tinsley.service;

import java.util.ResourceBundle;

public class ValidationService {
    /***
     * members
     */
    private String userInput;
    private final ValidationEntity validationEntity;

    /***
     * constructor
     * @param userInput - the input passed to the program when it is run
     */
    public ValidationService(String userInput, ResourceBundle resource) {
        this.userInput = userInput;
        this.validationEntity = new ValidationEntity(resource);  //POJO instance created when this class is called
        this.validationEntity.setUserInput(this.userInput); //user data is set on class call

    }

    /***
     *
     * service logic
     *
     */
    public Boolean validation() {
        this.userInput = validationEntity.getUserInput();
        char c;

        int stringLength = this.userInput.length();
        int charCnt = 0;
        int numCnt = 0;


        for (int x = 0; x < stringLength; x++) {
            c = this.userInput.charAt(x);
            //criteria 1
            if (Character.isAlphabetic(c)) {
                charCnt++;
            } else if (Character.isDigit(c)) {
                numCnt++;
            }
        }
            if (charCnt > 0 && numCnt > 0) {
                this.validationEntity.setValidMix(true);
                this.validationEntity.setMixOfCharsReason("charMixOk");
                return true;
            } else {
                this.validationEntity.setMixOfCharsReason("charmixError");
                this.validationEntity.setValidMix(false);
                return false;
            }

    }

    /**
     * helper - validate length
     *
     * @param charLength - takes the length of a string for validation
     * @return a boolean indicating the string meets criteria
     */
    public Boolean validateLength(int charLength) {
        int minLength = 5;
        int maxLength = 12;

        if (charLength >= minLength && charLength <= maxLength) {
            this.validationEntity.setValidLength(true);
            this.validationEntity.setLengthRequirementReason("rm");
            return true;
        } else {
            this.validationEntity.setLengthRequirementReason("noRm");
            this.validationEntity.setValidLength(false);
            return false;
        }
    }

    public Boolean validateSequence() {
        //String must not contain any sequence of characters immediately followed by the same sequence.

        this.userInput = validationEntity.getUserInput();
        String tempString;
        String[] charSet = this.userInput.split("");
        int matchCnt=0;

        for(int i=0; i<charSet.length-2; i++){

            tempString = charSet[i] + charSet[i + 1];

            if (this.userInput.subSequence(i+1,i+3).toString().toLowerCase().equalsIgnoreCase(tempString)) {
                        matchCnt++;

                        if(matchCnt == 1) {
                            this.validationEntity.setValidSequenceReason("seqBad");
                            this.validationEntity.setValidSeq(false);
                            return false;
                        }
            }else{
                if (i == charSet.length-3){
                    //System.out.println("no sequences of previous sequences found for: " + this.userInput );
                    this.validationEntity.setValidSequenceReason("seqGood");
                    this.validationEntity.setValidSeq(true);
                    return true;
                }
            }
        }
        return false;
    }

    public void displayResult(){

        System.out.println(this.validationEntity.toStringi18());
    }

}
