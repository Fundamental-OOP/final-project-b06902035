package avn;

import com.almasb.fxgl.entity.component.Component;

import javafx.geometry.Point2D;

public class MoveComponent extends Component{
	double speed;
	Point2D dst;
	public MoveComponent(Point2D dst, double speed) {
		this.speed = speed;
		this.dst = dst;
	}
	@Override
    public void onUpdate(double tpf) {
        Point2D velocity = dst.subtract(entity.getPosition())
                .normalize()
                .multiply(speed * tpf);
								
        if (dst.distance(entity.getPosition()) < speed * tpf) {
            entity.setPosition(dst);
			speed = 0;
			return;
        }
		entity.translate(velocity);
    }
}
