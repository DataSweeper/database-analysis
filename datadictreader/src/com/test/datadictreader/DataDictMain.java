package com.test.datadictreader;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by siva-2356 on 17/6/15.
 */

public class DataDictMain {

    File fXmlFile = null;
    DocumentBuilderFactory dbFactory = null;
    DocumentBuilder dBuilder = null;
    Document doc = null;

    public DataDictMain() {
        try {
            fXmlFile = new File("/home/likewise-open/ZOHOCORP/siva-2356/IdeaProjects/test/datadictreader/resource/data-dictionary.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public JSONObject xmlReader() {
        JSONObject jo = new JSONObject();
        try {
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("table");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    jo.put("tableName" + String.valueOf(temp), eElement.getAttribute("name"));
                }
            }
        }
        catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return jo;
    }

    public JSONObject tableReader(String tableName) {
        JSONObject jsonObject = new JSONObject();
        Node node = getTableNode(tableName);
        NamedNodeMap map = node.getAttributes();
        System.out.println(map.getLength());

        for(int i = 0; i < map.getLength(); i ++) {
            System.out.println("check");
            System.out.println(map.item(i));
        }

        Element eElement = (Element) node;

        eElement.getElementsByTagName("name");
        return jsonObject;
    }

    private Node getTableNode(String tableName) {
        Node nNode = null;
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("table");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("name").equals(tableName))
                       return nNode;
                }
            }
        return nNode;
    }


    public static void main(String[] args) {
        DataDictMain testObj = new DataDictMain();
        JSONObject jsonObject = testObj.tableReader("AC_USER_ROLES_MAPPER");
        JSONObject jsonObject1 = testObj.xmlReader();
        System.out.println(jsonObject);
        System.out.println(jsonObject1);
    }
}
