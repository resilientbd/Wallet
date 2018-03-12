package com.suman.msi.wallet;

/**
 * Created by msi on 2/22/2018.
 */

public class ChildInfo {
    private String sequence = "";
    private double amount = 0.0;
    private String note = "";
    private String date = "";

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
