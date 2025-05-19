package application;

public class Usuaris {

	// Atributs
	public String nom;
	public String cognoms;
	public String email;
	public String poblacio;
	public String contrasenya;
	
	// constructor
	public Usuaris(String nom, String cognoms, String email, String poblacio, String contrasenya) {
		super();

		this.nom = nom;
		this.cognoms = cognoms;
		this.email = email;
		this.poblacio = poblacio;
		this.contrasenya = contrasenya;
	}

	// getters i setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPoblacio() {
		return poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contraseña) {
		this.contrasenya = contraseña;
	}

	@Override
	public String toString() {
		return "Usuaris [nom=" + nom + ", cognoms=" + cognoms + ", email=" + email + ", poblacio=" + poblacio
				+ ", contrasenya=" + contrasenya + "]";
	}
	
	
	
}
