package com.example.nickolas.xmldemo;

import android.app.ListActivity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nickolas on 2017/5/16.
 */

public class PullPraserDemo extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.list, new String[] { "id", "name" }, new int[] {
                R.id.textId, R.id.textName });
        setListAdapter(adapter);
    }

    private List<Map<String, String>> getData() {
        List<Map<String,String>> list =new  ArrayList<Map<String, String>>();
        XmlResourceParser xrp = getResources().getXml(R.xml.channels);
        try{
            while (xrp.getEventType() != XmlPullParser.END_DOCUMENT){
                if(xrp.getEventType() == XmlPullParser.START_TAG){
                    String tagName = xrp.getName();
                    if(tagName.equals("item"))
                    {
                        Map<String,String> map =new  HashMap<String, String>();
                        String id = xrp.getAttributeValue(null,"id");
                        map.put("id",id);
                        String url = xrp.getAttributeName(1);
                        map.put("url", url);
                        String name = xrp.nextText();
                        map.put("name", name);
                        list.add(map);
                    }
                }
                xrp.next();
            }
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
