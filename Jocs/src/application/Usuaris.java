package application;

public class Usuaris {

	// Atributs
	public String nom;
	public String cognoms;
	public String email;
	public String poblacio;
	public String contrasenyaHash;
	public String salt;
	public String img;
	
	// constructor
	public Usuaris(String nom, String cognoms, String email, String poblacio, String contrasenyaHash, String salt, String img) {
		super();

		this.nom = nom;
		this.cognoms = cognoms;
		this.email = email;
		this.poblacio = poblacio;
        this.contrasenyaHash = contrasenyaHash;
        this.salt = salt;
        this.img = img;
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

	public String getContrasenyaHash() {
		return contrasenyaHash;
	}

	public void setContrasenyaHash(String contrasenyaHash) {
		this.contrasenyaHash = contrasenyaHash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
	


	
	
	
}
