package tinsley.service;

import java.util.ResourceBundle;
import java.util.UUID;

/**
 *
 * this is a pojo class that could be wired to a database, message queue, rest API or message stream
 *
 */
public class ValidationEntity {
    /**
     *
     * members
     *
     */
    private boolean isValidLength;
    private boolean isValidMix;
    private boolean isValidSeq;
    private UUID id;
    private String userInput;
    private String lengthRequirementReason;
    private String mixOfCharsReason;
    private String validSequenceReason;
    //i18n compliance
    private ResourceBundle resource;

    /**
     *
     * constructor
     *
     */
    public ValidationEntity(ResourceBundle resource){
        this.resource = resource;
    }

    /**
     * setters & getters for this entity
     * @return pojo info
     *
     */
    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        //set a pojo id when user input is set
        this.id = UUID.randomUUID();
        this.setId(this.id);
    }

    /**
     *
     * Setters and getters for this entity
     * Returns the requested set value
     *
     */
    public void setValidLength(boolean ValidLength) {
        isValidLength = ValidLength;
    }

    public boolean isValidMix() {
        return isValidMix;
    }

    public void setValidMix(boolean validMix) {
        isValidMix = validMix;
    }

    public boolean isValidSeq() {
        return isValidSeq;
    }

    public void setValidSeq(boolean validSeq) {
        isValidSeq = validSeq;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLengthRequirementReason() {
        return lengthRequirementReason;
    }

    public void setLengthRequirementReason(String lengthRequirementReason) {
        this.lengthRequirementReason = lengthRequirementReason;
    }

    public String getMixOfCharsReason() {
        return mixOfCharsReason;
    }

    public void setMixOfCharsReason(String mixOfCharsReason) {
        this.mixOfCharsReason = mixOfCharsReason;
    }

    public String getValidSequenceReason() {
        return validSequenceReason;
    }

    public void setValidSequenceReason(String validSequenceReason) {
        this.validSequenceReason = validSequenceReason;
    }

    public boolean isValidLength() {
        return isValidLength;
    }

    @Override
    public String toString() {
        return "\nValidationResults:{\n" +
                " userInput= '" + getUserInput() + '\'' +
                ",\n isValidLength= " + isValidLength() +
                ",\n lengthReason= " + getLengthRequirementReason() +
                ",\n isValidMixOfCharacters= " + isValidMix() +
                ",\n mixOfCharactersReason= " + getMixOfCharsReason() +
                ",\n isValidSequence= " + isValidSeq() +
                ",\n ValidSeqReason= " + getValidSequenceReason() +
                ",\n id= " + getId() +
                "\n}";
    }

    public String toStringi18(){
        return "\n" + this.resource.getString("ValidationResults") + ":{\n" +
                this.resource.getString("userInput")+"=" + getUserInput() +
                ",\n" + this.resource.getString("isValidLength") + "=" + this.resource.getString(Boolean.toString(isValidLength())) +
                ",\n" + this.resource.getString( "lengthReason") + "=" + this.resource.getString(getLengthRequirementReason()) +
                ",\n" + this.resource.getString( "isValidMixOfCharacters") +"=" + this.resource.getString(Boolean.toString(isValidMix())) +
                ",\n" + this.resource.getString( "mixOfCharactersReason") +"=" + this.resource.getString(getMixOfCharsReason()) +
                ",\n" + this.resource.getString( "isValidSequence") +"=" + this.resource.getString(Boolean.toString(isValidSeq())) +
                ",\n" + this.resource.getString( "ValidSeqReason") +"=" + this.resource.getString(getValidSequenceReason()) +
                ",\n id= " + getId() +
                "\n}";
    }

}
