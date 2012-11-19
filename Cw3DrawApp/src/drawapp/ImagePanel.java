package drawapp;

import java.io.File;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.LinearGradientBuilder;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ImagePanel extends HBox {
  private Group graphics = new Group();
	private Paint colour;
	private Boolean dropShadow;
	private Boolean reflection;
	private Boolean gaussianBlur;

	public ImagePanel(int width, int height) {
		this.colour = Color.BLACK;
		this.dropShadow = false;
		this.reflection = false;
		this.gaussianBlur = false;
		setImageSize(width, height);
	}

	private void setImageSize(int width, int height) {
		this.setMaxSize(width, height);
		this.getChildren().add(graphics);
		clear(Color.WHITE);
	}

	public void setBackgroundColour(Color colour) {
		
		this.setStyle("-fx-background-color: rgb(" + (int) (colour.getRed()*255) + "," + (int) (colour.getGreen()*255) + "," + (int) (colour.getBlue()*255)  + ");");
	}

	public void clear(Color colour) {
		setBackgroundColour(colour);
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	public void setGradient(Color colourStart, Color colourEnd) {
		LinearGradient lg = LinearGradientBuilder.create()
				.stops(new Stop(0, colourStart),
				new Stop(1, colourEnd))
				.cycleMethod(CycleMethod.NO_CYCLE)
				.startX(0)
				.startY(0)
				.endX(1)
				.endY(0)
				.proportional(true)
				.build();
		this.colour = lg;
	}
	
	public void setReflection(boolean on) {
		this.reflection = true;
	}

	public void setDropShadow(boolean on) {
		this.dropShadow = true;
	}
	
	public void setGaussianBlur(boolean on) {
		this.gaussianBlur = on;
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		Line line = new Line(x1, y1, x2, y2);
		line.setStroke(this.colour);
		
		if (gaussianBlur) {
			line.setEffect(new GaussianBlur());
		}
		if (reflection) {
			line.setEffect(new Reflection());
		}
		graphics.getChildren().add(line);

	}

	public void drawRect(int x1, int y1, int x2, int y2) {
		Rectangle rect = new Rectangle(x1, y1, x2, y2);
		rect.setStroke(this.colour);
		rect.setFill(this.colour);
		
		if (gaussianBlur) {
			rect.setEffect(new GaussianBlur());
		}
		if (reflection) {
			rect.setEffect(new Reflection());
		}
		graphics.getChildren().add(rect);

	}

	public void fillRect(int x1, int y1, int x2, int y2) {
		Rectangle rectFill = new Rectangle(x1, y1, x2, y2);
		rectFill.setFill(this.colour);
		
		if (gaussianBlur) {
			rectFill.setEffect(new GaussianBlur());
		}
		if (reflection) {
			rectFill.setEffect(new Reflection());
		}
		graphics.getChildren().add(rectFill);

	}

	public void drawString(int x, int y, String s) {
		Text t = new Text(x, y, s);
		
		if (gaussianBlur) {
			t.setEffect(new GaussianBlur());
		}
		if (reflection) {
			t.setEffect(new Reflection());
		}
		if (dropShadow) {
			t.setEffect(new DropShadow());
		}
		graphics.getChildren().add(t);

	}

	public void drawArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		Arc arc = new Arc(x, y, width / 2, height / 2, startAngle, arcAngle);
		arc.setStroke(this.colour);
		arc.setFill(this.colour);
		this.colour = Color.BLACK;
		
		if (gaussianBlur) {
			arc.setEffect(new GaussianBlur());
		}
		if (reflection) {
			arc.setEffect(new Reflection());
		}
		graphics.getChildren().add(arc);
	}

	public void drawOval(int x, int y, int width, int height) {
		Ellipse oval = new Ellipse(x, y, width, height);
		oval.setStroke(this.colour);
		oval.setFill(this.colour);
		this.colour = Color.BLACK;
		
		if (gaussianBlur) {
			oval.setEffect(new GaussianBlur());
		}
		if (reflection) {
			oval.setEffect(new Reflection());
		}
		graphics.getChildren().add(oval);
	}

	public void drawImage(int x, int y, int width, int height, String path) {
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		ImageView iv = new ImageView(image);
		iv.setFitWidth(width);
		iv.setFitHeight(height);
		iv.setSmooth(true);
		iv.setCache(true);
		iv.setPreserveRatio(true);
		
		if (gaussianBlur) {
			iv.setEffect(new GaussianBlur());
		}
		if (reflection) {
			iv.setEffect(new Reflection());
		}
		graphics.getChildren().add(iv);
	}
}