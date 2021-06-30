package avn;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.Texture;

import avn.animal.AnimalComponent;
import avn.npc.NpcComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;

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
				.collidable()
                .with(ac)
                .build();
	}
	@Spawns("Npc")
	public Entity spawnNpc(SpawnData data) {
		NpcComponent component = data.get("component");
		NpcComponent nc;
		try {
			nc = component.getClass().getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.out.println("new instance error" + e.getCause());
			return null;
		}
		return entityBuilder(data)
				.type(AnimalVsNpcType.NPC)
				.viewWithBBox(nc.getImageName())
				.collidable()
                .with(nc)
                .build();
	}
	@Spawns("BirdEgg")
	public Entity spawnBirdEgg(SpawnData data) {
		return entityBuilder(data)
				.type(AnimalVsNpcType.BIRDEGG)
				.viewWithBBox("BirdEgg.png")
				.onClick(e -> {
					FXGL.inc("eggs", +1);
					e.removeFromWorld();
				})
                .build();
	}
	@Spawns("Bullet")
    public Entity spawnBullet(SpawnData data) {
		Point2D direction = data.get("direction");
        return entityBuilder(data)
                .type(AnimalVsNpcType.BULLET)
                .viewWithBBox(new Rectangle(15, 5, Color.DARKGREY))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
				.with(new ProjectileComponent(direction, 300))
                .build();
    }
}
