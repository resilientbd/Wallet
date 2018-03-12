package com.suman.msi.wallet;

import java.util.ArrayList;

/**
 * Created by msi on 2/22/2018.
 */

public class GroupInfo {
    private String name;
    private ArrayList<ChildInfo> list = new ArrayList<ChildInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildInfo> getCategoryList() {
        return list;
    }

    public void setCategoryList(ArrayList<ChildInfo> categoryList) {
        this.list = categoryList;
    }
}
