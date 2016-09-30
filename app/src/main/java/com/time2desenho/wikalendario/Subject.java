package com.time2desenho.wikalendario;

/**
 * Created by joao on 30/09/16.
 */
public class Subject {

    private String name;
    private String code;
    private int numberOfCredits;

    public Subject(String code, String name, int numberOfCredits){
        setCode(code);
        setName(name);
        setNumberOfCredits(numberOfCredits);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        assert name != "";

        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        assert numberOfCredits > 0;

        this.numberOfCredits = numberOfCredits;
    }
}
