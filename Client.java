package soap_performance;

import java.io.*;
import java.net.*;

public class Client
{
	public final static int DEF_PORT=9;
	public final static int MAX_SIZE=65507;
	public static void main(String arg[])
	{
		try{

			InetAddress server=InetAddress.getByName("127.0.0.1");
			Socket soc = new Socket(server, 8020);
			long starttime = System.nanoTime();
			String str=Encrypt.encrypto("\\D:\\Client1\\request.xml");
			long endtime = System.nanoTime();
			System.out.println("time in encrypting request with "+Tag.tag("\\D:\\Client1\\request.xml")+" tag  "+(endtime-starttime));
			new File("\\D:\\Client1\\encryptedRequest.xml");
		    FileWriter fw=new FileWriter("\\D:\\Client1\\encryptedRequest.xml");
		    fw.write(str.toString());

		    fw.close();
		    	System.out.println("sending request");
			FileInputStream fis = new FileInputStream("\\D:\\Client1\\encryptedRequest.xml");
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream()) ;
			oos.writeObject(buffer);

			System.out.println("recieving response ");
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			byte[] buffer1 = (byte[])ois.readObject();
			FileOutputStream fos = new FileOutputStream("\\D:\\Client1\\encryptedResponse.xml");
			fos.write(buffer1);

			fos.close();
			System.out.println("decrypting response ");


			String str1=(String) Decrypt.decrypto("\\D:\\Client1\\encryptedResponse.xml");

			new File("\\D:\\Client\\Response.xml");
			FileWriter fw1 = new FileWriter("\\D:\\Client1\\Response.xml");
			fw1.write(str1);
			fw1.close();
			soc.close();
			fis.close();

		}
		catch(Exception e)
		{
			System.out.println("Error : "+e);
		}
	}

}
