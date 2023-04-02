package Week3;

public class Libro extends Catalogo {

	public String autore;
	public String genere;

	public Libro() {
		super();
		this.setAutore();
		this.setGenere();
	}

	public Libro(long ISBN, String Titolo, int AnnoPubb, int numOfPage, String autore, String genere) {
		this.ISBN = ISBN;
		this.Titolo = Titolo;
		this.AnnoPubb = AnnoPubb;
		this.numOfPage = numOfPage;
		this.autore = autore;
		this.genere = genere;
	}

	public String getAutore() {
		return this.autore;
	}

	public String getGenere() {
		return this.genere;
	}

	@Override
	protected void setTitolo() {
		System.out.println("Inserisci il titolo del libro:");
		this.Titolo = Archivio.s.nextLine();
	}

	private void setAutore() {
		System.out.println("Inserisci l'autore del libro:");
		this.autore = Archivio.s.nextLine();
	}

	private void setGenere() {
		System.out.println("Inserisci il genere del libro:");
		this.genere = Archivio.s.nextLine();
	}
}
