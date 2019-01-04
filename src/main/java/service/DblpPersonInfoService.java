package service;

import model.Person;
import model.Publication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DblpPersonInfoService {

    //get all publications and the afilliation of the person

    String apiPath = "https://dblp.uni-trier.de/pers/xx";

    public List<Publication> getPublications(String key) {

        String url = apiPath + "/" + key;

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

        //parser

        document.getDocumentElement().normalize();
        NodeList publications = document.getElementsByTagName("r");
        List<Publication> listofpub = new ArrayList<>();
        for(int i =0; i < publications.getLength(); i++) {
            Publication p = new Publication();
            p.setKey(((Element)publications.item(i).getChildNodes().item(0)).getAttribute("key"));
            p.setTitle(((Element) publications.item(i).getChildNodes().item(0)).
                    getElementsByTagName("title").item(0).getTextContent());
            listofpub.add(p);
        }
        for(Publication p: listofpub) {
            System.out.println(p.getKey() + " " + p.getTitle());
        }
        //parser
        return listofpub;
    }

//    public static void main(String[] args) {
//        DblpPersonInfoService dblp = new DblpPersonInfoService();
//        dblp.getPublications("l/Leyer:Michael");
//    }


}
