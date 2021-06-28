package avn;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.Texture;

import avn.animal.AnimalComponent;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class AnimalVsNpcFactory implements EntityFactory{
	@Spawns("Animal")
	public Entity spawnAnimal(SpawnData data) {
		AnimalComponent selected = data.get("selected");
		AnimalComponent ac;
		try {
			ac = selected.getClass().getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.out.println("new instance error" + e.getCause());
			return null;
		}
		return entityBuilder(data)
				.type(AnimalVsNpcType.ANIMAL)
				.viewWithBBox(selected.getImageName())
                .with(new CollidableComponent(true))
                .with(ac)
                .build();
	}
}
