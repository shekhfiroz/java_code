import java.io.*;
import java.net.*;
public class MyClient2{

	Socket s;
	DataInputStream din;
	DataoutputStream dout;

	public MyClient2()
	{
		try
		 {
			s=new Socket("localhost",10);
			din=new DataInputStream(s.getInputStream());
			dout =new DataoutputStream(s.getInputStream());
			ClientChat();

		 }
		catch(Exception e)
		{ /*System.out.print e*/}
     }
		public void ClientChat() throws IOException{

			My m =new My(din);
			Thread t1 =new Thread(m);
			t1.start();
			BufferedReader br =new BufferedReader(new InputStreamReader (System.in));
			String s1;
			do{
				s1 = br.readLine();
				dout.writeUtf(s1);
				dout.flush();
			}
			while(!s1.equals("stop"));
		 }

		 public static void main(String args[])
		  {
		 	new MyClient2();
		  }

		 class My implements Runnable{
			DataInputStream din;
			My(DataInputStream din);
			{
				this.din=din;
			}
		}

		public void run(){
			String s2 ="";
			do {
				try{

					s2 = din.readUTF();
					System.out.println(s2);
				}
				catch(Exception e){}
			
		}

		while(!s2.equals("stop"));
	}
}