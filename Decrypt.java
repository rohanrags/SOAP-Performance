package soap_performance;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class Decrypt {
	public static String decrypto(String str1) throws Exception {
		final StringBuffer str = new StringBuffer();
		//FileWriter fw = new FileWriter("\\D:\\Client\\Service.xml");

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler1 = new DefaultHandler() {
				boolean boo = false;

				public void startDocument() throws SAXException {

					try {
						str.append("<?xml version=\"1.0\"?>\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}



				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {


					boo = true;

				}



				public void characters(char ch[], int start, int length)
						throws SAXException {
					if (boo) {


						try {
							str.append(Encryption.decrypt(new String(ch, start,
									length)));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						boo = false;
					}
				}

			};

			saxParser.parse(str1, handler1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
		//fw.write(str.toString());
		//fw.close();
	}

}
