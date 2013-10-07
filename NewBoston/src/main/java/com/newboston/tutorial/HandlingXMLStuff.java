package com.newboston.tutorial;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Chris on 01.10.13.
 */
public class HandlingXMLStuff extends DefaultHandler {

    XMLDataCollected info = new XMLDataCollected();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("city")) {
            info.setCity(attributes.getValue("name"));
        } else if (localName.equals("temperature")) {
            info.setTemp(Double.parseDouble(attributes.getValue("value")));
        }
    }

    public String getInformation() {
        return info.dataToString();
    }
}
