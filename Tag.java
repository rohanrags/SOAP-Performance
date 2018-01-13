package soap_performance;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Tag {

   public static int count;

public static int tag(String path)throws Exception {



    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {





	public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {
		count++;



	}


     };

       saxParser.parse(path, handler);

     } catch (Exception e) {
       e.printStackTrace();
     }
    return count;




   }

}
