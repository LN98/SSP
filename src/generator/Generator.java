package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class Generator {
//	ArrayList<String> quando;
//	ArrayList<String> chi;
//	ArrayList<String> fa;
//	ArrayList<String> cosa;

	public String generate(String d) {

		ArrayList<String> quando;
		ArrayList<String> chi;
		ArrayList<String> fa;
		ArrayList<String> cosa;

		quando = new ArrayList<>();
		chi = new ArrayList<>();
		fa = new ArrayList<>();
		cosa = new ArrayList<>();

		try {

			FileReader fr = new FileReader(new File("quando.txt"));
			BufferedReader br = new BufferedReader(fr);
			String riga;
			while ((riga = br.readLine()) != null) {
				quando.add(riga);
			}

			fr = new FileReader(new File("chi.txt"));
			br = new BufferedReader(fr);
			while ((riga = br.readLine()) != null) {
				chi.add(riga);
			}

			fr = new FileReader(new File("fa.txt"));
			br = new BufferedReader(fr);
			while ((riga = br.readLine()) != null) {
				fa.add(riga);
			}

			fr = new FileReader(new File("cosa.txt"));
			br = new BufferedReader(fr);
			while ((riga = br.readLine()) != null) {
				cosa.add(riga);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// carica arrayLista

		// format=ddmmyyyy
		// String d="05091998";

		int q = Integer.parseInt(d.substring(0, 2)) - 1;
		int c = Integer.parseInt(d.substring(2, 4)) - 1;
		int f = Integer.parseInt(d.substring(7, 8));
		int cos = (int)(Math.random() * 20);

		return quando.get(q) + " " + chi.get(c) + " " + fa.get(f) + " " + cosa.get(cos);
	}

}
