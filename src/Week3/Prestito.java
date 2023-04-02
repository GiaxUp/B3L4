package Week3;

import java.util.Date;

public class Prestito {
	private Utente utente;
	private Catalogo elemento;
	private Date dataInizio;
	private Date dataRestituzionePrevista;
	private Date dataRestituzioneEffettiva;

	public Prestito(Utente utente, Catalogo elemento, Date dataInizio) {
		this.setUtente(utente);
		this.elemento = elemento;
		this.setDataInizio(dataInizio);
		this.dataRestituzionePrevista = new Date(dataInizio.getTime() + 30 * 24 * 60 * 60 * 1000);
	}

	public Catalogo getElemento() {
		return elemento;
	}

	public void setElemento(Catalogo elemento) {
		this.elemento = elemento;
	}

	public Date getDataRestituzionePrevista() {
		return dataRestituzionePrevista;
	}

	public void setDataRestituzionePrevista(Date dataRestituzionePrevista) {
		this.dataRestituzionePrevista = dataRestituzionePrevista;
	}

	public Date getDataRestituzioneEffettiva() {
		return dataRestituzioneEffettiva;
	}

	public void setDataRestituzioneEffettiva(Date dataRestituzioneEffettiva) {
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	public boolean isScaduto() {
		Date oggi = new Date();
		return oggi.after(dataRestituzionePrevista) && dataRestituzioneEffettiva == null;
	}

	public void restituisci(Date dataRestituzioneEffettiva) {
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
		elemento.setDisponibile(true);
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Object getNome() {
		return null;
	}
}
