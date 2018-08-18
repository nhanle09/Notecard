import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

class Custom_dialog {
	static void message(String title, String message) {
		Alert message_window = new Alert(AlertType.INFORMATION);
		message_window.setTitle(title);
		message_window.setHeaderText(null);
		message_window.setContentText(message);
		
		message_window.showAndWait();
	}
}