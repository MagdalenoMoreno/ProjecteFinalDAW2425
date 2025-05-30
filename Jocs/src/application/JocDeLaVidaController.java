package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class JocDeLaVidaController implements Initializable {

	@FXML
	private VBox pagina;

	
	@FXML
	private GridPane taula;
	
	public final int celulas = 0;
	public int celdas = 2;
	public double tamanyo = 10;
	public Label [][] matriu;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void inicializarTablero(String grandaria) {
		

		if (grandaria.equals("peq")) {
			celdas = 17;
			tamanyo = 10;
			taula.setMaxSize(170, 170);
			taula.setMinSize(170, 170);
			taula.setPrefSize(170, 170);
		} else if (grandaria.equals("med")) {
			celdas = 25;
			tamanyo = 10;
			taula.setMaxSize(250, 250);
			taula.setMinSize(250, 250);
			taula.setPrefSize(250, 250);
		} else if (grandaria.equals("grande")) {
			celdas = 33;
			tamanyo = 10;
			taula.setMaxSize(330, 330);
			taula.setMinSize(330, 330);
			taula.setPrefSize(330, 330);
		} else if (grandaria.equals("pers")) {
			double raiz = Math.sqrt(celdas);
			celdas = (int) raiz;
			tamanyo = 350 / celdas;
			double gridPaneLado = celdas * tamanyo;
			taula.setMaxSize(gridPaneLado, gridPaneLado);
			taula.setMinSize(gridPaneLado, gridPaneLado);
			taula.setPrefSize(gridPaneLado, gridPaneLado);
		}

		
		taula.getRowConstraints().clear();
		taula.getColumnConstraints().clear();
		
		for (int i = 0; i < celdas; i++) {
		    taula.getColumnConstraints().add(new ColumnConstraints(tamanyo));
		    taula.getRowConstraints().add(new RowConstraints(tamanyo));
		}
		

		matriu = new Label[celdas][celdas];
		for (int i = 0; i < matriu.length; i++) {
			for (int j = 0; j < matriu[i].length; j++) {
				Label lb = new Label();
				matriu[i][j] = lb;
				lb.setMinSize(tamanyo, tamanyo);
				lb.setMaxSize(tamanyo, tamanyo);
				lb.setPrefSize(tamanyo, tamanyo);
				lb.setStyle("-fx-border-color: blue");
				taula.setStyle("-fx-border-color: black; -fx-alignment: center;");
				taula.add(lb, j, i);
			}
		}

		System.out.println(grandaria);
	}

}
