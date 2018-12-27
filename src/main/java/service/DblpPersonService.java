package service;

import model.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DblpPersonService {

    String apiPath="https://dblp.uni-trier.de/search/author";

    public List<Person> getAuthors(String firstName, String lastName) {
        String pathParam = lastName + ":" + firstName;

        String url = apiPath + "?xauthor=" + pathParam;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document document = null;
        try {
            db = dbf.newDocumentBuilder();
            document = db.parse(new URL(url).openStream());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        if(document == null)
            return null;

        document.getDocumentElement().normalize();
        NodeList authors = document.getElementsByTagName("author");

        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < authors.getLength(); i++) {
            Person p = new Person();
            p.setKey(((Element)authors.item(i)).getAttribute("urlpt"));
            p.setName(authors.item(i).getTextContent());
            persons.add(p);

        }
        for (Person p : persons) {
            System.out.println(p.getName() + " " + p.getKey());
        }
        
        return persons;
    }

    public static void main(String[] args) {
        DblpPersonService dblp =  new DblpPersonService();
        dblp.getAuthors("Ley", "Michael");
    }
}
