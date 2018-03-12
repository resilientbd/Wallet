package com.suman.msi.wallet;

/**
 * Created by msi on 2/26/2018.
 */

public class Records {
    private int rec_id;
    private double amount;
    private String note;
    private String category;
    private String date;
    public Records() {
    }

    public Records(int rec_id, double amount, String note, String category,String date) {
        this.rec_id = rec_id;
        this.amount = amount;
        this.note = note;
        this.category = category;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
