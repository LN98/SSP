package client;

import java.net.*;
import java.io.*;
import java.util.*;
 
public class Client {
    //private ServerSocket  server;
    private Socket          connessione;
    private Scanner         dalServer;
    private PrintStream     alServer;
     
    public Client() {
         
        try {
         
            connessione = new Socket ("127.0.0.1",12345);
             
            System.out.println("********************\nConnessione Effettuata\n********************");
            dalServer = new Scanner (connessione.getInputStream() );
            alServer = new PrintStream (connessione.getOutputStream() );
             
        }
        catch (IOException e) {
            System.out.println (e);
        }
    }
     
    public void conversazione () {
        String messaggio =" ";
         
        Scanner tastiera = new Scanner(System.in);
         
        try{
            while (!messaggio.equals("end")){
                messaggio=dalServer.nextLine();
                System.out.println(messaggio);
                if (!messaggio.equals("end")){
                    messaggio=tastiera.next();
                    messaggio = "REQUEST: "+messaggio;
                    alServer.println(messaggio);
                     
                }
            }
            connessione.close();
            tastiera.close();
             
        }
        catch(IOException e){
            System.out.println("Conversazione interrotta");
        }
    }
    
    public static void main(String[] args) {
		Client c = new Client();
		c.conversazione();
	}
}