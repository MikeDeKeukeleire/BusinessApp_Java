open module SDP2122_G08 {
	// FX
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires org.controlsfx.controls;

	// Persistence
	requires java.sql;
	requires java.persistence;
	requires java.instrument;
	requires java.desktop;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
	requires org.mockito;
	requires org.mockito.junit.jupiter;
}
