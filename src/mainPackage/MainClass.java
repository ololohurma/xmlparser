package mainPackage;

import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.helpers.XMLReaderFactory;

public class MainClass {

    public static void main(String[] args) {
        try {
            XMLReader reader
                    = XMLReaderFactory.createXMLReader();
            MyHandler handler = new MyHandler();
            reader.setContentHandler(handler);
            //XML reading            
            reader.parse("eventlist.xml");

        } catch (SAXException e) {        
            System.out.print("SAX error");
        } catch (IOException e) {
            System.out.print("IO error");
        }
    }

}
