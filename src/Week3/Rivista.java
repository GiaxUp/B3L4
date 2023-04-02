package Week3;

import java.util.InputMismatchException;

public class Rivista extends Catalogo {

	enum Uscita {
		Settimanale, Mensile, Semestrale
	}

	public Uscita Periodicità;

	public Rivista() {
		this.setPeriodicità();
	}

	public Rivista(long ISBN, String Titolo, int annoPubb, int numOfPage, Uscita Periodicità) {
		this.ISBN = ISBN;
		this.Titolo = Titolo;
		this.AnnoPubb = annoPubb;
		this.numOfPage = numOfPage;
		this.Periodicità = Periodicità;
	}

	public Uscita getUscitaPeriodicita() {
		return this.Periodicità;
	}

	@Override
	protected void setTitolo() {
		System.out.println("Inserisci il titolo della rivista:");
		this.Titolo = Archivio.s.nextLine();
	}

	private void setPeriodicità() {

		int uscita = 0;
		while (uscita <= 0 || uscita >= 4) {
			System.out
					.println("Seleziona la periodicità della rivista: 1 - Settimanale / 2 - Mensile / 3 - Semestrale");
			try {
				uscita = getIntFromS();
				switch (uscita) {
				case 1:
					this.Periodicità = Uscita.Settimanale;
					break;
				case 2:
					this.Periodicità = Uscita.Mensile;
					break;
				case 3:
					this.Periodicità = Uscita.Semestrale;
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Utilizza un valore valido.");
				Archivio.s.nextLine();
			}

		}

	}
}
