package gui;

import java.io.IOException;

import domein.DomeinController;
import domein.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AanmeldSchermController extends GridPane {
	private final LoginController lc;
	private DomeinController d;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnWachtwoordVergeten;
	@FXML
	private Button btnReset;
	@FXML
	private PasswordField psfWachtwoord;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private Label lblWarning;
	@FXML
	private Label lblVulGebruikersnaamIn;
	@FXML
	private Label lblVulWachtwoordIn;

	public AanmeldSchermController(LoginController lc) {
		this.lc = lc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		lblWarning.setVisible(false);
		lblVulGebruikersnaamIn.setVisible(false);
		lblVulWachtwoordIn.setVisible(false);
	}

	// Event Listener on Button[#btnLogin].onAction
	@FXML
	public void LoginOnAction(ActionEvent event) {
		lblWarning.setVisible(false);
		lblVulGebruikersnaamIn.setVisible(false);
		lblVulWachtwoordIn.setVisible(false);
		if (txfGebruikersnaam.getText().equals("")) {
			lblVulGebruikersnaamIn.setVisible(true);
		} else if (psfWachtwoord.getText().equals("")) {
			lblVulWachtwoordIn.setVisible(true);
		} else {
			d = lc.login(txfGebruikersnaam.getText(), psfWachtwoord.getText());
			if (d != null) {
				NavigatieContainerController controller = new NavigatieContainerController();
				Scene s = new Scene(controller, 1000, 600);
				Stage stage = (Stage) this.getScene().getWindow();
				stage.setScene(s);
				stage.show();
				stage.setMaximized(true);
			} else {
				lblWarning.setVisible(true);
				txfGebruikersnaam.clear();
				psfWachtwoord.clear();
			}
		}
	}

	@FXML
	public void ResetOnAction(ActionEvent event) {
		lblWarning.setVisible(false);
		lblVulGebruikersnaamIn.setVisible(false);
		lblVulWachtwoordIn.setVisible(false);
		txfGebruikersnaam.clear();
		psfWachtwoord.clear();
	}
}