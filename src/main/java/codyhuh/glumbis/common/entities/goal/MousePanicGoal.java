package codyhuh.glumbis.common.entities.goal;

import codyhuh.glumbis.common.entities.Mouse;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class MousePanicGoal extends PanicGoal {
    private final Mouse mouse;

    public MousePanicGoal(Mouse goalOwner, double speed) {
        super(goalOwner, speed);
        this.mouse = goalOwner;
    }

    @Override
    protected boolean shouldPanic() {
        return !this.mouse.isBaby() && (this.mouse.isFleeing() || this.mouse.getLastHurtByMob() != null || this.mouse.isFreezing() || this.mouse.isOnFire());
    }
}