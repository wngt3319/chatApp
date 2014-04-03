//package com.linkett.socket_chat;

import java.net.*;
import java.io.*;

public class Serverside extends Thread
{
   private ServerSocket serverSocket;
   
   public Serverside(int port) throws IOException
   {
      serverSocket = new ServerSocket(port, 0, InetAddress.getByName("0.0.0.0"));
      //serverSocket.setSoTimeout(10000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
						System.out.println("localhost: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            DataInputStream in =
                  new DataInputStream(server.getInputStream());
            System.out.println(in.readLine());
            DataOutputStream out =
                 new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to "
              + server.getLocalSocketAddress() + "\nGoodbye!");
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
         Thread t = new Serverside(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
