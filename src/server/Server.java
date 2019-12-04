package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

import generator.Generator;

public class Server {

	private ServerSocket server;
	private Socket con;
	private Scanner dalClient;
	private PrintStream alClient;
	private Generator g = new Generator();

	public Server() {

		try {
			server = new ServerSocket(12345);
			System.out.println("********************\n* Server Attivo\n********************");
			con = server.accept();
			System.out.println("Connessione effettuata\n" + con.toString());
			dalClient = new Scanner(con.getInputStream());
			alClient = new PrintStream(con.getOutputStream());
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public boolean isLeap(int year) {
		return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
	}

	public boolean isValidDate(int d, int m, int y) {

		if (y > LocalDate.now().getYear() || y < 1800)
			return false;
		if (m < 1 || m > 12)
			return false;
		if (d < 1 || d > 31)
			return false;

		if (m == 2) {
			if (isLeap(y))
				return (d <= 29);
			else
				return (d <= 28);
		}

		if (m == 4 || m == 6 || m == 9 || m == 11)
			return (d <= 30);

		return true;
	}

	public void conversazione() {
		alClient.println(
				"Benvenuto nella sibilla, per ricevere una divina rivelazione manda la tua data di nascita nel formato: dd/MM/yyyy");
		String messaggio = "";
		try {
			while (!messaggio.equals("end")) {
				messaggio = dalClient.nextLine().split(" ", 2)[1];	
				System.out.println("From client: " + messaggio);
				if (!messaggio.equals("end")) {
					try {
						// Parses the date
						if (!messaggio.contains("/")) {
							throw new Exception();
						}
						if (isValidDate(Integer.parseInt(messaggio.split("/")[0]),
								Integer.parseInt(messaggio.split("/")[1]), Integer.parseInt(messaggio.split("/")[2]))) {
							alClient.println("RESPONSE: " + g.generate(messaggio.replace("/", "")));
						} else {
							throw new Exception();
						}

					} catch (Exception e) {
						System.out.println(e.getMessage());
						alClient.println("BAD REQUEST: Formattazione errata data.");
					}
				}
			}
			con.close();

		} catch (IOException e) {
			System.out.println("Conversazione interrotta");
		}
	}

}
