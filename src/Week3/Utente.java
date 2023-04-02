package Week3;

import java.time.LocalDate;

public class Utente {
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private int numTessera;

	public Utente(String nome, String cognome, LocalDate dataNascita, int numTessera) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.numTessera = numTessera;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public int getNumTessera() {
		return numTessera;
	}
}
