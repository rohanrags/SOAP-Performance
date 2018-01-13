package soap_performance;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Encrypt {

   public static int count;

public static String encrypto(String path)throws Exception {
	  final StringBuffer str=new StringBuffer();


    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {
		boolean boo = false;
		 public void startDocument() throws SAXException {
		       // System.out.println("start document   : ");
		        try {
					str.append("<?xml version='1.0'?>\n");
					str.append("<ra>");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }

		    public void endDocument() throws SAXException {
		       // System.out.println("end document     : ");
		        str.append("</ra>");

		    }



	public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {

		//System.out.println("Start Element :" + qName);
try {

			int length = attributes.getLength();
				if(length!=0){
					// process each attribute
					String name, value;
					str.append("<cipher>"+(Encryption.encrypt("<" + qName))+ "</cipher>");
					for (int i=0; i<length; i++) {
						// get qualified (prefixed) name by index
						name = attributes.getQName(i);

						// get attribute's value by index.
						value = attributes.getValue(i);
						//System.out.println("Value:" + value);
						str.append("<cipher>"+(Encryption.encrypt(" "+name + "=\"" + value +"\""))+"</cipher>");
						}
						str.append("<cipher>"+(Encryption.encrypt(">"))+ "</cipher>");
					}
					else
					{
						str.append("<cipher>"+(Encryption.encrypt("<" + qName+ ">"))+"</cipher>");
					}

		//	str.append("<cipher>"+(Encryption.encrypt(name + "=\"" + value +" \""))+"</cipher>");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boo=true;


	}

	public void endElement(String uri, String localName,
		String qName) throws SAXException {

		//System.out.println("End Element :" + qName);
		try {
			str.append("<cipher>"+(Encryption.encrypt("</" + qName+">"))+"</cipher>\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void characters(char ch[], int start, int length) throws SAXException {
		if (boo) {

			//System.out.println("character : " + new String(ch, start, length));
			try {

				str.append("<cipher>"+Encryption.encrypt(new String(ch, start, length))+"</cipher>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boo = false;
		}





	}

     };

       saxParser.parse(path, handler);

     } catch (Exception e) {
       e.printStackTrace();
     }
    return str.toString();




   }

}
