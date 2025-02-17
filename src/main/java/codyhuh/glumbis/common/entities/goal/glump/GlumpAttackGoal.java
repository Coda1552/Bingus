package codyhuh.glumbis.common.entities.goal.glump;

import codyhuh.glumbis.common.entities.Glump;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class GlumpAttackGoal extends Goal {
    protected final Glump entity;
    private int cooldownTimer;

    public GlumpAttackGoal(Glump entity) {
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return entity.getTarget() != null;
    }

    @Override
    public void start() {
        super.start();
        this.cooldownTimer = 0;
    }

    @Override
    public void tick() {
        if (this.cooldownTimer < 20) {
            cooldownTimer++;
        } else {
            LivingEntity livingEntity = entity.getTarget();
            if (this.entity.distanceToSqr(livingEntity) < 1.0f) {
                this.entity.doHurtTarget(livingEntity);
                this.entity.setExploding(true);
                this.cooldownTimer = 0;
            }
        }
    }
}
