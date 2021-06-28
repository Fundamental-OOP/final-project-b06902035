package avn.animal;

import static com.almasb.fxgl.dsl.FXGL.*;
import javafx.scene.layout.Pane;

public class AnimalIcon extends Pane{
	public AnimalIcon(String name) {
		getChildren().add(getAssetLoader().loadTexture(name));
	}
}
