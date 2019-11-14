package com.example.installpack;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> ParentItem;
    private HashMap<String, List<AdSoyadMobil>> ChildItem;


    public ExpandableListAdapter(Context context, List<String> ParentItem,
                                 HashMap<String, List<AdSoyadMobil>> ChildItem) {
        this.context = context;
        this.ParentItem = ParentItem;
        this.ChildItem = ChildItem;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.ChildItem.get(this.ParentItem.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final AdSoyadMobil expandedListText = (AdSoyadMobil) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_item, null);
        }
        TextView text1 = (TextView) convertView.findViewById(R.id.item1);
        TextView text2 = (TextView) convertView.findViewById(R.id.item2);
        //text1.setText(""+expandedListPosition);
        //text2.setText(""+expandedListText);
        text1.setText(expandedListText.getName().trim());
        text2.setText(expandedListText.getCode().trim());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.ChildItem.get(this.ParentItem.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.ParentItem.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.ParentItem.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_item, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


    public static HashMap<String, List<AdSoyadMobil>> getData() {
        HashMap<String, List<AdSoyadMobil>> ParentItem = new HashMap<String, List<AdSoyadMobil>>();

        List<AdSoyadMobil> ColorsList = new ArrayList<AdSoyadMobil>();
        AdSoyadMobil colors = new AdSoyadMobil("Red", "057");
        ColorsList.add(colors);
        colors = new AdSoyadMobil("Green", "053");
        ColorsList.add(colors);
        colors = new AdSoyadMobil("Blue", "054");
        ColorsList.add(colors);
        colors = new AdSoyadMobil("Maroon", "055");
        ColorsList.add(colors);
        colors = new AdSoyadMobil("Yellow", "056");
        ColorsList.add(colors);


        ParentItem.put("Colors", ColorsList);

        Log.i("dikkat", String.valueOf(ParentItem));

        return ParentItem;




    }
}