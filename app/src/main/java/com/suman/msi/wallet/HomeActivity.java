package com.suman.msi.wallet;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle aDrawer;
    Button addBtn1, addBtn2;

//    Expand List
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> categryList = new ArrayList<GroupInfo>();

    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addBtn1 = (Button) findViewById(R.id.addDepositBtn);
        addBtn2 = (Button) findViewById(R.id.addExpenseBtn);

        addBtn1.setOnClickListener(this);
        addBtn2.setOnClickListener(this);

        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayoutId);
        aDrawer = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);
        mDrawer.addDrawerListener(aDrawer);
        aDrawer.syncState();


//        Expand List
        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(HomeActivity.this, categryList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
//        expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = categryList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo =  headerInfo.getCategoryList().get(childPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Clicked on :: " + headerInfo.getName()
                        + "/" + detailInfo.getNote(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = categryList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        getSupportActionBar().setSubtitle("Accountant");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (aDrawer.onOptionsItemSelected(item)) {

            return true;
        }
        return onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addDepositBtn || v.getId()==R.id.addExpenseBtn) {
//            String mainText = mainEditText.getText().toString();
            Intent intent = new Intent(HomeActivity.this,InputActivity.class);
//            intent.putExtra("Person Name", mainText);
            startActivity(intent);
        }
    }

//        Expand List
//  method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData(){

        addProduct("Deposit",2500,"Elder Brother","2 Feb");
        addProduct("Salary",3000,"Tution","4 Feb");
        addProduct("Varities",25,"Sugar","4 Feb");
        addProduct("Varities",25,"Tea Leaf","4 Feb");
        addProduct("Eating out",35,"Breakfast","6 Feb");
        addProduct("Car",20,"Rent","8 Feb");
        addProduct("Study material",100,"Pen, Khata, Pencil","12 Feb");
        addProduct("Study material",1500,"Form fill up","14 Feb");
        addProduct("Varities",200,"Visit","15 Feb");
        addProduct("Communication",25,"Flexiload","15 Feb");
        addProduct("Bills",2000,"Seat rent","8 Feb");
        addProduct("Bills",250,"Net Bill","8 Feb");
        addProduct("Varities",125,"Roaming","17 Feb");
        addProduct("Communication",25,"Flexiload","18 Feb");
        addProduct("Mess",1500,"Lodge","7 Feb");
        addProduct("Mess",1000,"Lodge","24 Feb");
        addProduct("Varities",50,"Biscuits","24 Feb");
        addProduct("Varities",25,"Sugar","24 Feb");
        addProduct("Varities",25,"Sugar","24 Feb");
        addProduct("Eating out",20,"Chips","25 Feb");
        addProduct("Varities",25,"Sugar","24 Feb");
        addProduct("Varities",25,"Sugar","24 Feb");
        addProduct("Varities",25,"Sugar","24 Feb");
        addProduct("Health",300,"Medicine","25 Feb");
    }



    //here we maintain our products in various departments
    private int addProduct(String category,double amount, String note, String date){

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(category);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(category);
            subjects.put(category, headerInfo);
            categryList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> categoryList = headerInfo.getCategoryList();
        //size of the children list
        int listSize = categoryList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setAmount(amount);
        detailInfo.setNote(note);
        detailInfo.setDate(date);
        categoryList.add(detailInfo);
        headerInfo.setCategoryList(categoryList);

        //find the group position inside the list
        groupPosition = categryList.indexOf(headerInfo);
        return groupPosition;
    }





//    Snackbar bar = Snackbar.make(null, "Weclome to SwA", Snackbar.LENGTH_LONG)
//            .setAction("Dismiss", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Handle user action
//                }
//            });
//
//    bar.show();
//}
}