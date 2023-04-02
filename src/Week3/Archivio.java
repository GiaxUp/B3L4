package Week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import Week3.Rivista.Uscita;
// import Week3.Prestito;
// import Week3.Utente;

public class Archivio {

	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		List<Catalogo> Catalogo = new ArrayList<Catalogo>();
		GestioneCatalogo(Catalogo);
		// List<Prestito> prestiti = new ArrayList<>();
	}

	public static void GestioneCatalogo(List<Catalogo> arr) {

		while (true) {
			System.out.println("Seleziona cosa vuoi fare:\n" + "1. Aggiungi elemento al catalogo.\n"
					+ "2. Elimina elemento tramite ISBN.\n" + "3. Ricerca un elemento.\n"
					+ "4. Salva il file del catalogo.\n" + "5. Carica il file del catalogo.\n"
					+ "6. Visualizza il catalogo.");
			int scelta = s.nextInt();
			s.nextLine();

			switch (scelta) {
			case 1:
				AggiungiElemento(arr);
				break;
			case 2:
				if (arr.isEmpty()) {
					System.out.println("Catalogo vuoto, prima di eliminare aggiungi qualcosa.\n");
				} else {
					arr = removeByISBN(arr);
				}
				break;
			case 3:
				if (arr.isEmpty()) {
					System.out.println("Nessun risultato trovato, prima di cercare aggiungi qualcosa.\n");
				} else {
					ricerca(arr, s);
				}
				break;
			case 4:

				if (arr.isEmpty()) {
					System.out.println("Nessun elemento da salvare, aggiungine prima qualcuno.\n");
				} else {
					try {
						SalvaCatalogo(arr);
						System.out.println("Catalogo salvato correttamente.");
					} catch (IOException e) {
						System.out.println("Errore imprevisto durante il salvataggio del catalogo.");
					}

				}
				break;
			case 5:
				try {
					File file = new File("C:/Users/giax9/Documents/Epicode/Backend/B3L5/catalogo.txt");
					if (!file.exists()) {
						System.out.println(
								"Il file del catalogo è vuoto o non esiste. Prova a aggiungere un catalogo prima di caricarlo.\n");
					} else {
						arr = caricaCatalogo();
						System.out.println("Catalogo caricato correttamente.\n");
					}
				} catch (FileNotFoundException e) {
					System.out.println("File del catalogo non trovato.\n");
				} catch (@SuppressWarnings("hiding") IOException e) {
					System.out.println("Errore durante la lettura del catalogo.\n");
				} catch (IllegalArgumentException e) {
					System.out.println("Errore durante la lettura del catalogo.\n");
				}
				break;
			case 6:
				if (arr.isEmpty()) {
					System.out.println("Carica o aggiungi un elemento per visualizzarlo.\n");
				} else {
					PrintArray(arr);
				}
				break;
			default:
				System.out.println("Scelta non valida. Riprova.\n");
			}
		}
	}

	public static void AggiungiElemento(List<Catalogo> arr) {

		String exit = "0";
		while (exit.equals("0")) {
			System.out.println("Seleziona l'elemento da aggiungere: L - Libro / R - Rivista / 0 - Torna indietro");
			String agg = s.nextLine().toUpperCase();
			if (agg.equals("0")) {
				exit = "Esci";
			} else if (agg.equals("L")) {
				arr.add(new Libro());
			} else if (agg.equals("R")) {
				arr.add(new Rivista());
			}
		}
	}

	public static List<Catalogo> removeByISBN(List<Catalogo> arr) {

		List<Catalogo> newarr = new ArrayList<Catalogo>();

		boolean exit = false;
		while (!exit) {
			System.out.println("Inserisci il codice ISBN dell'elemento da eliminare. Altrimenti premi 0 per uscire.");
			try {
				long ISBNtoRemove = s.nextLong();
				if (ISBNtoRemove == 0) {
					return arr;
				}
				exit = true;
				if (arr.stream().anyMatch(e -> e.ISBN == ISBNtoRemove)) {
					newarr = arr.stream().filter(e -> e.ISBN != ISBNtoRemove).collect(Collectors.toList());
					PrintArray(newarr);
				} else {
					System.out.println("Il codice inserito non corrisponde a nessun elemento.\n");
					return arr;
				}
			} catch (InputMismatchException e) {
				System.out.println("Inserisci un numero.\n");
				s.nextLine();
			}
		}
		return newarr;
	}

	public static void ricerca(List<Catalogo> arr, Scanner input) {
		System.out.println(
				"Inserisci la parola chiave con cui vuoi cercare il libro o la rivista\n Formato: Autore Anno ISBN");
		Object query = null;

		if (input.hasNextLong()) {
			query = input.nextLong();
		} else if (input.hasNextLine()) {
			query = input.nextLine().toUpperCase();
		} else if (input.hasNextInt()) {
			query = input.nextInt();
		} else {
			System.out.println("Errore nella ricerca.\n");
		}

		Predicate<Catalogo> filtro = null;

		if (query instanceof String) {
			String Nome = (String) query;
			filtro = e -> {
				if (e instanceof Libro) {
					Libro libro = (Libro) e;
					return libro.getAutore().equals(Nome);
				} else {
					return false;
				}
			};
			arr.stream().filter(filtro).forEach(res -> System.out.println(Catalogo.toString(res)));
		} else if (query instanceof Long) {
			Long CodiceLibro = (Long) query;
			arr.stream().filter(e -> e.ISBN == CodiceLibro).forEach(res -> System.out.println(Catalogo.toString(res)));
		} else if (query instanceof Integer) {
			Integer Anno = (Integer) query;
			arr.stream().filter(e -> e.AnnoPubb == Anno).forEach(res -> System.out.println(Catalogo.toString(res)));
		} else {
			System.out.println("Qualcosa è andato storto. Riprova.\n");
			return;
		}
	}

	public static void SalvaCatalogo(List<Catalogo> arr) throws IOException {
		if (arr == null || arr.isEmpty()) {
			throw new IllegalStateException("La lista degli elementi è vuota.\n");
		}
		List<String> righeCatalogo = new ArrayList<String>();
		for (Catalogo elemento : arr) {
			if (elemento instanceof Libro) {
				Libro libro = (Libro) elemento;
				righeCatalogo.add("Libro | ISBN: " + libro.getISBN() + " / Titolo: " + libro.getTitolo()
						+ " / Anno di pubblicazione: " + libro.getAnnoPubb() + " / Numero di pagine: "
						+ libro.getNumOfPage() + " / Autore: " + libro.getAutore() + " / Genere: " + libro.getGenere());
			} else if (elemento instanceof Rivista) {
				Rivista rivista = (Rivista) elemento;
				righeCatalogo.add("Rivista | ISBN: " + rivista.getISBN() + " / Titolo: " + rivista.getTitolo()
						+ " / Anno di pubblicazione: " + rivista.getAnnoPubb() + " / Numero di pagine: "
						+ rivista.getNumOfPage() + " / Periodicità: " + rivista.getUscitaPeriodicita());
			}
		}

		File file = new File("catalogo.txt");
		FileUtils.writeLines(file, righeCatalogo, System.getProperty("line.separator"));

	}

	public static List<Catalogo> caricaCatalogo() throws FileNotFoundException {
		List<Catalogo> catalogo = new ArrayList<Catalogo>();
		File file = new File("catalogo.txt");
		if (!file.exists()) {
			throw new FileNotFoundException("File del catalogo non trovato.\n");
		}

		Path path = Paths.get(file.getPath());
		List<String> righeCatalogo;
		try {
			righeCatalogo = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Errore durante la lettura del file del catalogo.\n");
		}
		for (String riga : righeCatalogo) {
			String[] campi = riga.split("/");
			if (campi.length < 6) {
				throw new IllegalArgumentException("Errore riga.");
			}

			String tipoElemento = campi[0];
			long ISBN = Long.parseLong(campi[1]);
			String titolo = campi[2];
			int annoPubb = Integer.parseInt(campi[3]);
			int numOfPage = Integer.parseInt(campi[4]);

			if (tipoElemento.equals("Libro")) {
				if (campi.length < 7) {
					throw new IllegalArgumentException("Errore nella riga del libro.");
				}
				String autore = campi[5];
				String genere = campi[6];
				catalogo.add(new Libro(ISBN, titolo, annoPubb, numOfPage, autore, genere));
			} else if (tipoElemento.equals("Rivista")) {
				if (campi.length < 6) {
					throw new IllegalArgumentException("Errore nella riga della rivista.");
				}
				String uscitaPeriodicita = campi[5];
				catalogo.add(new Rivista(ISBN, titolo, annoPubb, numOfPage, Uscita.valueOf(uscitaPeriodicita)));
			}
		}

		return catalogo;
	}

	public static void PrintArray(List<Catalogo> arr) {
		System.out
				.println("\nIl catalogo comprende: " + arr.size() + (arr.size() > 1 ? " Elementi \n" : " Elemento \n"));
		ToArray(arr);
	}

	public static void ToArray(List<Catalogo> arr) {
		for (Catalogo i : arr) {
			System.out.println(Catalogo.toString(i));
		}
	}

	// Parte da fixare successivamente (?)
//	public List<Catalogo> getElementiInPrestito(String numeroTessera) {
//		List<Catalogo> elementiInPrestito = new ArrayList<>();
//		for (Prestito prestito : prestiti) {
//			if (prestito.getUtente().getNumTessera().equals(numTessera)) {
//				elementiInPrestito.add(prestito.getElemento());
//			}
//		}
//		return elementiInPrestito;
//	}
//
//	public List<Prestito> getPrestitiScaduti() {
//		List<Prestito> prestitiScaduti = new ArrayList<>();
//		for (Prestito prestito : prestiti) {
//			if (prestito.isScaduto()) {
//				prestitiScaduti.add(prestito);
//			}
//		}
//		return prestitiScaduti;
//	}
}
