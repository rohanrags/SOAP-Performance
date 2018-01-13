package soap_performance;
import java.io.*;
import java.net.*;

public class Registry
{
	public final static int DEF_PORT=9;
	public final static int MAX_SIZE=65507;

	public static void main(String args[])
	{
		//byte[] buffer=new byte[100000];
		try
		{
			 System.out.println("waiting for request");

			ServerSocket ser = new ServerSocket(8020);
			 Socket clientSocket = ser.accept();

			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
			byte[] buffer = (byte[])ois.readObject();
			FileOutputStream fos = new FileOutputStream("\\F:\\Registry1\\encryptedRequest.xml");
			fos.write(buffer);
			System.out.println("request recieved decrypting request ");
			String str1=(String) Decrypt.decrypto("\\F:\\Registry1\\encryptedRequest.xml");

			new File("\\F:\\Registry1\\Request.xml");
			FileWriter fw1 = new FileWriter("\\F:\\Registry1\\Request.xml");
			fw1.write(str1);
			long starttime = System.nanoTime();
			String str=Encrypt.encrypto("\\F:\\Registry1\\response.xml");
			long endtime = System.nanoTime();
			System.out.println("time in encrypting response with "+Tag.tag("\\F:\\Registry1\\response.xml")+" tag "+(endtime-starttime));
			new File("\\F:\\Registry1\\encryptedResponse.xml");
		    FileWriter fw=new FileWriter("\\F:\\Registry1\\encryptedResponse.xml");
		    fw.write(str.toString());

		    fw.close();
		    fw1.close();














		    System.out.println("sending response");
			FileInputStream fis = new FileInputStream("\\F:\\Registry1\\encryptedResponse.xml");
			byte[] buffer2 = new byte[fis.available()];
			fis.read(buffer2);
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream()) ;
			oos.writeObject(buffer2);
			ser.close();
			fis.close();
			fos.close();
			clientSocket.close();
			ois.close();
		}

		catch (Exception e)
		{
			e.printStackTrace();

		 }


	}


}
