package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.Avatar;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuController implements Initializable {
	// Objecte a compartir amb l'altra escena
	private Usuaris usuariActual;

	public void setUsuari(Usuaris usuariActual) {
		this.usuariActual = usuariActual;
	}

	public Usuaris getUsuari() {
		return this.usuariActual;
	}

	// Atributos Usuaris
	private String emailUsuari;

	// variables
	@FXML
	private VBox root;
	@FXML
	private Avatar avatarImg;
	@FXML
	private Label textoBienvenido;
	@FXML
	private ImageView imgBuscaMines;
	@FXML
	private ImageView imgPixelArt;
	@FXML
	private ImageView imgJocVida;
	@FXML
	private ImageView imgWordles;
	@FXML
	private Button botoBuscaMines;
	@FXML
	private Button botoPixelArt;
	@FXML
	private Button botoJocVida;
	@FXML
	private Button botoWordles;
	@FXML
	private Button botoVolver;
	@FXML
	private Button botoBorrarUsuari;
	
	public void tancarFinestres() {

		// tanca totes les finestres de la aplicacio i torna al login

		// si esta lloguejat se pot borrar tota la informacio del usauri
	}
	
	// Boto per a entrar als jocs
	@FXML
	public void obrirBuscaMines(MouseEvent event) {
		try {
			VBox rootBuscaMines = (VBox) FXMLLoader.load(getClass().getResource("TamanyBuscamines.fxml"));
			Scene pantallaBuscaMines = new Scene(rootBuscaMines); // Probabilitat de fallar ImageView damunt del boto
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			pantallaBuscaMines.getStylesheets().add(getClass().getResource("tamanyBuscamines.css").toExternalForm());
			window.setScene(pantallaBuscaMines);
			window.setTitle("Busca Mines");
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void obrirPixelArt(MouseEvent event) {
		try {
			VBox rootPixelArt = (VBox) FXMLLoader.load(getClass().getResource("PixelArt.fxml"));
			Scene pantallaPixelArt = new Scene(rootPixelArt); // Probabilitat de fallar ImageView damunt del boto
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			pantallaPixelArt.getStylesheets().add(getClass().getResource("pixelArt.css").toExternalForm());
			window.setScene(pantallaPixelArt);
			window.setTitle("Pixel Art");
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void obrirJocVida(MouseEvent event) {
		try {
			VBox rootJocVida = (VBox) FXMLLoader.load(getClass().getResource("JocVida.fxml"));
			Scene pantallaJocVida = new Scene(rootJocVida); // Probabilitat de fallar ImageView damunt del boto
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			pantallaJocVida.getStylesheets().add(getClass().getResource("jocVida.css").toExternalForm());
			window.setScene(pantallaJocVida);
			window.setTitle("Joc de la vida");
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void obrirWordles(MouseEvent event) {
		try {
			VBox rootWordles = (VBox) FXMLLoader.load(getClass().getResource("Wordle.fxml"));
			Scene pantallaWordles = new Scene(rootWordles); // Probabilitat de fallar ImageView damunt del boto
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			pantallaWordles.getStylesheets().add(getClass().getResource("wordle.css").toExternalForm());
			window.setScene(pantallaWordles);
			window.setTitle("Wordle");
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Boton cerrar sesion
	@FXML
	public void logout(MouseEvent event) {
		try {
			VBox rootLogout = (VBox) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene pantallaIrLogin = new Scene(rootLogout);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(pantallaIrLogin);
			window.setTitle("Login");
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Boton borrar usuairo
	@FXML
	public void borrarUsuari(MouseEvent event) throws ClassNotFoundException, IOException {
		try {
			// Carregar el controlador per a la BD
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establir la connexió
			String urlBaseDades = "jdbc:mysql://localhost:3306/cal";
			String usuari = "root";
			String contrasenya = "";

			Connection c = DriverManager.getConnection(urlBaseDades, usuari, contrasenya);
			String sentencia = "DELETE FROM usuarios where email = ?";
			PreparedStatement s = c.prepareStatement(sentencia);
			s.setString(1, emailUsuari);

			int resultat = s.executeUpdate();

			if (resultat > 0) {
				System.out.println("Usuari eliminat. ");
				logout(event);
			} else {
				System.out.println("No existeix ningun email .");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Funcio per a pasar els datos del objecte usuari
	// Datos per a utilizar el email i el nom
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Platform.runLater(() -> {
			Window window = (Stage) root.getScene().getWindow();
			if (window != null) {
				this.usuariActual = (Usuaris) window.getUserData();
				if (usuariActual != null) {
					this.emailUsuari = usuariActual.getEmail();
					textoBienvenido.setText("Bienvenido, " + usuariActual.getNom());
				} else {
					System.out.println("Usuari null");
				}
			} else {
				System.out.println("Window null");
			}

		});
	}

}
