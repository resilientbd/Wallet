package com.suman.msi.wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by msi on 2/22/2018.
 */

public class CustomAdapter extends BaseExpandableListAdapter {
    private Context context;
    double amn = 0;
    int num = 0;
    private ArrayList<GroupInfo> categoryList;

    public CustomAdapter(Context context, ArrayList<GroupInfo> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ChildInfo> productList = categoryList.get(groupPosition).getCategoryList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        ChildInfo detailInfo = (ChildInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.child_items, null);
        }

        TextView sequence = (TextView) view.findViewById(R.id.sequence);
        sequence.setText(detailInfo.getSequence().trim() + ". ");
        TextView childItemMoney = (TextView) view.findViewById(R.id.childItemMoney);
        TextView childItemNote = (TextView) view.findViewById(R.id.childItemNote);
        TextView childItemDate = (TextView) view.findViewById(R.id.childItemDate);
        TextView childItemCurrency = (TextView) view.findViewById(R.id.childItemMoneyCurrency);
        childItemCurrency.setText("BDT");
        childItemMoney.setText(String.valueOf(detailInfo.getAmount()));
        childItemNote.setText(detailInfo.getNote().trim());
        childItemDate.setText(detailInfo.getDate().trim());


        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<ChildInfo> productList = categoryList.get(groupPosition).getCategoryList();
        num = productList.size();
        return num;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        GroupInfo headerInfo = (GroupInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.group_items, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        TextView headingNum = (TextView) view.findViewById(R.id.headingNum);
        TextView headingMoney = (TextView) view.findViewById(R.id.headingMoney);
        TextView groupItemCurrency = (TextView) view.findViewById(R.id.groupItemMoneyCurrency);

        heading.setText(headerInfo.getName().trim());
        groupItemCurrency.setText("BDT");
        headingNum.setText("5"); //detailInfo.getSequence().trim() like this
        headingMoney.setText("200");

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
