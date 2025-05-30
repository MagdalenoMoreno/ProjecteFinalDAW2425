package application;

public class finestraOberta {
	
	// Singelton
	private static finestraOberta instancia; 

	// Atributo
	private boolean oberta = false;

	// Constructor
	private finestraOberta() {

	}

	// funcion statica
	public static finestraOberta getInstancia() {
		if (instancia == null) {
			instancia = new finestraOberta();
		}
		return instancia;
	}
	
	// Funcion boolean
	public boolean isOberta() {
		return oberta;
	}

	// seter
	public void setOberta(boolean oberta) {
		this.oberta = oberta;
	}

}
