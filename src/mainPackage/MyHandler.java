package mainPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;

enum TagEnum {
    DATETIME, URL, NAME, CITY , COUNTRY, ARTISTS, REGION, VENUE
}

public class MyHandler implements ContentHandler {

    TagEnum currentEnum = null;
    boolean isInVenue = false;

    public void startDocument() {
  }

    public void startElement(String uri, String localName,
            String qName, Attributes attrs) {
//
        if ("datetime".equals(qName) || "city".equals(qName) || "url".equals(qName) || "country".equals(qName)|| "region".equals(qName)) {
            currentEnum
                    = TagEnum.valueOf(qName.toUpperCase());           
        }
        
        if ("venue".equals(qName)) {
            isInVenue = true;
         }
        
        if ("name".equals(qName)) {
           if(isInVenue == true) 
               currentEnum
                    = TagEnum.valueOf(qName.toUpperCase());
         }
        
        if ("artist".equals(qName)) {
            isInVenue = false;
         }
    }
        

    public void endElement(String uri, String localName,
            String qName) {
        currentEnum = null;
    }

    public void characters(char[] ch, int start,
            int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum == null) {
            return;
        }

        if (currentEnum == TagEnum.DATETIME) {
            try {
                SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                isoFormat.setTimeZone(TimeZone.getDefault());
                Date date = isoFormat.parse(s);
                System.out.print("*************************** " + "\n");
            System.out.print(date + "\n");
            } catch (ParseException ex) {
                Logger.getLogger(MyHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
        }
        if (currentEnum == TagEnum.CITY ||currentEnum == TagEnum.REGION) {
           System.out.print(s + ", ");
        }    
        
         if (currentEnum == TagEnum.COUNTRY || currentEnum == TagEnum.NAME)
         {
          System.out.print(s + "\n");
         }

    }

    public void skippedEntity(String name) {
    }

    public void processingInstruction(String target,
            String data) {
    }

    public void ignorableWhitespace(char[] ch,
            int start,
            int length) {
    }

    public void endPrefixMapping(String prefix) {
    }

    public void startPrefixMapping(String prefix,
            String uri) {
    }

    public void endDocument() {

    }

    public void setDocumentLocator(Locator locator) {
    }
}
