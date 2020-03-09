package com.example.javaexamples.javaparser;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class JavaVariableDTO {
    private String varName;
    private String varType;
    private String modifier;
    private String annot;

    public JavaVariableDTO(String varName, String varType, String modifier, String annot) {
        this.varName = varName;
        this.varType = varType;
        this.modifier = modifier;
        this.annot = annot;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getAnnot() {
        return annot;
    }

    public void setAnnot(String annot) {
        this.annot = annot;
    }

    @Override
    public String toString() {
        return annot.trim() + "_" + modifier.trim() + "_" + varType.trim() + "_" + varName.trim();
    }
}
