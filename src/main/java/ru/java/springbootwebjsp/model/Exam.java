package ru.java.springbootwebjsp.model;

public class Exam {
    private String val1;
    private String val2;
    private String val3;
    private String result="";
    private String resultTrue="";
    private String question;
    private boolean error=false;

    public Exam() {
    }


    public Exam(String val1, String val2, String result, String resultTrue, boolean error) {
        this.val1 = val1;
        this.val2 = val2;
        this.result = result;
        this.resultTrue = resultTrue;
        this.error = error;
    }

    public Exam(String question, String val1, String val2, String val3, String resultTrue, boolean error) {
        this.question = question;
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.resultTrue = resultTrue;
        this.error = error;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getVal3() {
        return val3;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }

    public String getResultTrue() {
        return resultTrue;
    }

    public void setResultTrue(String resultTrue) {
        this.resultTrue = resultTrue;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
