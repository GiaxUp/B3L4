package Week3;

import java.util.Random;

public class Catalogo {

	public long ISBN;
	public String Titolo;
	public int AnnoPubb;
	public int numOfPage;
	
	public Catalogo() {
		this.setISBN();
		this.setTitolo();
		this.setAnnoPubb();
		this.setNumOfPage();
		
	}
	
	public int getNumOfPage() {
		return this.numOfPage;
	}
	
	public int getAnnoPubb() {
		return this.AnnoPubb;
	}
	
	public long getISBN() {
		return this.ISBN;
	}
	
	public String getTitolo() {
		return this.Titolo;
	}
	
	private void setNumOfPage() {
		Random num = new Random();
		int max=200 , min= 10;
		this.numOfPage = num.nextInt(max - min + 1) + min; 
	}
	
	private void setISBN () {
		
		long num = System.currentTimeMillis();
		Random rnum = new Random(num);
		long isbn = Math.abs(rnum.nextLong()) % 9000000000000L + 1000000000000L;
		isbn *= 10L;
		this.ISBN = isbn;
	}
	
	protected void setTitolo() {
			System.out.println("Inserisci il titolo:");
			this.Titolo = Archivio.s.nextLine().toUpperCase();
	}
	
	private void setAnnoPubb() {
		
		System.out.println("Inserisci l'anno di pubblicazione:");
		String anno = Archivio.s.nextLine();
		
		
		while(true) {
			if(anno == null)
				break;
			try {
				this.AnnoPubb = Integer.parseInt(anno);
				break;
			}catch(NumberFormatException e) {
				System.out.println("Utilizza un valore valido (esempio: 1974)");
			 anno = Archivio.s.nextLine();
			}
		}	
	}
	
	public static String toString(Catalogo e) {
		if (e instanceof Libro ) {
			Libro l = (Libro) e;
			return "Tipologia : Libro" + " \n Titolo: " + l.Titolo + " \n Autore: " + l.autore + " \n Anno di pubblicazione: " + l.AnnoPubb + " \n Genere: " + l.genere + " \n Numero di pagine: " + l.numOfPage + " \n Codice ISBN: " + l.ISBN + "\n";
		} else if (e instanceof Rivista) {
			Rivista r = (Rivista) e;
			return "Tipologia : Rivista" +  " \n Titolo: " + r.Titolo + " \n Anno di pubblicazione: " + r.AnnoPubb + " \n Numero di pagine: " + r.numOfPage + " \n Periodicità: " + r.Periodicità + " \n Codice ISBN: " + r.ISBN + "\n";
		} else return "Inserisci un Libro o una Rivista";
	}
	
	public static int getIntFromS() {
		int num = Archivio.s.nextInt();
		Archivio.s.nextLine();
		return num;
	}

	public void setDisponibile(boolean b) {
		// TODO Auto-generated method stub
	}
}
