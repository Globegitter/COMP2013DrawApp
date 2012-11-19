package drawapp;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		final MainWindow main = new MainWindow(stage);
		ImagePanel imagePanel = main.getImagePanel();
		InputStream inputFile = new FileInputStream("C:\\Users\\Markus\\EclipseWorkspace\\Java\\Year2\\Cw3DrawAppTina\\test.out");		
		InputStream is = new ByteArrayInputStream( "TL 30\nTF 100".getBytes() );
		Reader reader = new InputStreamReader(is);//System.in);
		Parser parser = new Parser(reader, imagePanel, main, stage);
		Button nextStep = main.nextStepButton();
		Button finishDrawing = main.finishDrawingButton();
		parser.parseButton(nextStep, finishDrawing);
		stage.show();
	}

}