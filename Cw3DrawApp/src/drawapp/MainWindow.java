package drawapp;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.GroupBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ToolBarBuilder;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class MainWindow {
	public static final int SCENEWIDTH = 560, SCENEHEIGHT = 500;

	private ImagePanel drawArea;
	private TextArea messageView;
	private Button exit;
	private Button nextStep;
	private Button finishDrawing;
	private Button saveImage;

	public MainWindow(Stage stage) {
		this(stage, SCENEWIDTH, SCENEHEIGHT);
	}

	public MainWindow(final Stage stage, int width, int height) {
		stage.setTitle("DrawApp");
		final Parent root = GroupBuilder.create()
				.children(
						buildGUI(stage, width, height)
				)
				.build();
		Scene scene = new Scene(root, width, height);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
	}

	private GridPane buildGUI(final Stage stage, final int width, final int height) {
		GridPane gridpane = new GridPane();
		
		messageView = TextAreaBuilder.create()
				.wrapText(true)
				.prefRowCount(6)
				.prefWidth(width)
				.editable(false)
				.build();
		
		drawArea = new ImagePanel(width, height);
		drawArea.setPrefWidth(width);
		drawArea.setPrefHeight(height-200);
		
		nextStep = ButtonBuilder.create()
				.text("Next Step")
				.styleClass("first")
				.build();
		
		finishDrawing = ButtonBuilder.create()
				.text("Finish Drawing")
				.build();
		
		exit = ButtonBuilder.create()
				.text("Exit")
				.styleClass("last")
				.onAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Platform.exit();
					}
				})
				.build();
		
		saveImage = ButtonBuilder.create()
				.text("Save Image...")
				.onAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
				            FileChooser imageLocation = new FileChooser();
				            
				            FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
				            imageLocation.getExtensionFilters().add(pngFilter);
				            
				            File file = imageLocation.showSaveDialog(stage);
				            if(file != null){
					            if(!file.toString().endsWith(".png")){
					            	file = new File(file.getAbsolutePath().toString() + ".png");
					            }
					            ImageIO.write(
										SwingFXUtils.fromFXImage(
												drawArea.snapshot(null, null), null),
										"png", file);
				            }
						} catch (IOException ex) {
							postMessage("Saving Failed. Please choose another file");
						}
					}
				})
				.build();
		
		Region spacer = new Region();
        spacer.getStyleClass().setAll("spacer");
        
        ToolBar bottomButtons = ToolBarBuilder.create()
        		.items(
        				spacer,
        				HBoxBuilder.create()
        				.children(
        						nextStep,
        						finishDrawing,
        						saveImage,
        						exit
        						)
        				.padding(new Insets(5, 0, 5, 0))
        				.styleClass("segmented-button-bar")
        				.build()
        				)
        		.build();
		
		gridpane.add(drawArea, 0, 0);
		gridpane.add(messageView, 0, 1);
		gridpane.add(bottomButtons, 0, 2);

		return gridpane;
	}
	
	public void changeSize(int width, int height) {
		drawArea.setPrefHeight(height - 200);
		drawArea.setPrefWidth(width);
		messageView.setPrefWidth(width);
	}

	public ImagePanel getImagePanel() {
		return drawArea;
	}

	public Button nextStepButton() {
		return nextStep;
	}

	public Button finishDrawingButton() {
		return finishDrawing;
	}

	public void postMessage(String s) {
		messageView.setText(s);
	}
}