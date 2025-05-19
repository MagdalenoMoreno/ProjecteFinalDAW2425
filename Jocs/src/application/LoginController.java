package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.Avatar;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	// Objecte a compartir amb l'altra escena
	private Usuaris usuariActual;

	public void setUsuari(Usuaris usuariActual) {
		this.usuariActual = usuariActual;
	}

	public Usuaris getUsuari() {
		return this.usuariActual;
	}

	// Variables
	@FXML
	private Avatar avatarImg;
	@FXML
	private Label textoBienvenidoLogin;
	@FXML
	private Separator separador;
	@FXML
	private TextField textoEmailLogin;
	@FXML
	private TextField textoContrasenyaLogin;
	@FXML
	private Button botoLogin;
	@FXML
	private Button botoRegistre;

	public void iniciarSesio(ActionEvent event) throws ClassNotFoundException {

		String email = emailValid();
		contrasenyaValid(email);

		try {
			usuariActual = (Usuaris) crearUsuariObj(email);

			if (usuariActual != null) {
				obrirMenu(event);
			} else {
				System.out.println("No sea creat el usuari");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// Boto registre que envia al registre
	@FXML
	public void obrirRegistre(ActionEvent event) {
		try {
			VBox rootRegistre = (VBox) FXMLLoader.load(getClass().getResource("Registre.fxml"));
			Scene pantallaRegistre = new Scene(rootRegistre);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			pantallaRegistre.getStylesheets().add(getClass().getResource("registre.css").toExternalForm());
			window.setScene(pantallaRegistre);
			window.setTitle("Registre de usuari");
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Boto iniciar sesio que envia a la escena menu
	@FXML
	public void obrirMenu(ActionEvent event) {
		try {
			VBox rootMenu = (VBox) FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene pantallaMenu = new Scene(rootMenu);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			pantallaMenu.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			window.setUserData(this.usuariActual);
			window.setScene(pantallaMenu);
			window.setTitle("Menu");
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Funcio de les alertes
	public void alertaError(String camp, String error) {
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Error en el login del usuari ");
		alerta.setHeaderText("Error en el '" + camp + "'. ");
		alerta.setContentText(error);
		alerta.showAndWait();
	}

	/*
	 * public Usuaris crearUsuariObj(ResultSet r) { 
	 * try {
	 * 
	 * String nom = r.getString("nom"); String cognoms = r.getString("cognoms");
	 * String email = r.getString("email"); String poblacio =
	 * r.getString("poblacio"); String contrasenya = r.getString("contrasenya");
	 * 
	 * Usuaris u = new Usuaris(nom, cognoms, email, poblacio, contrasenya);
	 * 
	 * return u;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return null; }
	 * 
	 * }
	 */
	
	// Comprobar que el email esta en la base de datos

	// comprobar email
	public String emailValid() {
		try {
			// Variables

			String email = textoEmailLogin.getText();

			// comprobar si esta en blanc
			if (email.isBlank()) {
				alertaError("Email", "No pot estar en blanc. ");
				return null;
			}

			// Carregar el controlador per a la BD
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establir la connexió
			String urlBaseDades = "jdbc:mysql://localhost:3306/cal";
			String usuari = "root";
			String contrasenya = "";

			Connection c = DriverManager.getConnection(urlBaseDades, usuari, contrasenya);

			String sentencia = "SELECT * FROM usuarios WHERE email = ?";
			PreparedStatement s = c.prepareStatement(sentencia);
			s.setString(1, email);

			ResultSet r = s.executeQuery();

			if (r.next()) {// si no existeix
				System.out.println("Email registrar. ");
				return email;
			} else {
				alertaError("Email ", "El email no esta registrat. ");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Comprobar que la contraseña es la del email anterior
	// comprobar contraseña
	public String contrasenyaValid(String email) {
		try {
			String contrasenya = textoContrasenyaLogin.getText();

			if (contrasenya.isBlank()) {
				alertaError("Email", "No pot estar en blanc. ");
				return null;
			}

			// Carregar el controlador per a la BD
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establir la connexió
			String urlBaseDades = "jdbc:mysql://localhost:3306/cal";
			String usuari = "root";
			String contrasenya1 = "";

			Connection c = DriverManager.getConnection(urlBaseDades, usuari, contrasenya1);

			String sentencia = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ?";
			PreparedStatement s = c.prepareStatement(sentencia);
			s.setString(1, email);
			s.setString(2, contrasenya);
			ResultSet r = s.executeQuery();

			if (r.next()) {
				return contrasenya;
			} else {
				alertaError("Contraseña ", "La contraseña no es correcta. ");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Crear el objecte Usuari per a pasar els datos entre controladors
	// crear el objecte usuari per a poder pasar els datos 
	public Object crearUsuariObj(String email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establir la connexió
			String urlBaseDades = "jdbc:mysql://localhost:3306/cal";
			String usuari = "root";
			String contrasenya1 = "";

			Connection c = DriverManager.getConnection(urlBaseDades, usuari, contrasenya1);

			String sentencia = "SELECT * FROM usuarios WHERE email = ?";
			PreparedStatement s = c.prepareStatement(sentencia);
			s.setString(1, email);
			ResultSet r = s.executeQuery();

			if (r.next()) {
				Usuaris u = new Usuaris(r.getString("nom"), r.getString("cognoms"), r.getString("email"),
						r.getString("poblacio"), r.getString("contrasenya"));
				return u;
			} else {
				System.out.println("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Mostrar login sin Bienvenida
	// Registrar usuari
	// ir al login con el texto de bienvenida añadido
	// textoBienvenido.setText("Bienvenido, " + usuariActual.getNom());
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Platform.runLater(() -> {

		});
	}

}
