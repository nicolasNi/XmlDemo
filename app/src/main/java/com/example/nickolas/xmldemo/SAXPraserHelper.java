package com.example.nickolas.xmldemo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickolas on 2017/5/15.
 */

public class SAXPraserHelper extends DefaultHandler{
    final int ITEM = 0x0005;

    List<channel> list;
    channel chann;
    int currentState = 0;

    public List<channel> getList() {
        return list;
    }

    @Override
    public void startDocument() throws SAXException {
        list = new ArrayList<channel>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        chann = new channel();
        if(localName.equals("item")){
            for(int i = 0 ;i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("id")){
                    chann.setId(attributes.getValue(i));
                }else if(attributes.getLocalName(i).equals("url")){
                    chann.setUrl(attributes.getValue(i));
                }
            }
            currentState = ITEM;
            return;
        }
        currentState = 0;
        return;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(currentState != 0){
            String value = String.valueOf(ch,start,length);
            chann.setName(value);
            currentState = 0;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(localName.equals("item")){
            list.add(chann);
        }
    }
}
