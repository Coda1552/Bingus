package codyhuh.glumbis.common.entities.goal.glumboss;

import codyhuh.glumbis.common.entities.Glumboss;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

// todo - figure out why glumboss isnt kicking
public class GlumbossKickGoal extends BaseGlumbossAttackGoal {
    public Glumboss glumboss;
    public int timer;
    public final int timerEnd;
    public int coolDown;
    public final int coolDownEnd;
    public final int frameStart;
    public final int frameEnd;
    public boolean shouldStopMoving;
    public float range;
    public boolean isInRange;

    public GlumbossKickGoal(Glumboss glumboss, int timerEnd, int coolDownEnd, int animation, int frameStart, int frameEnd, boolean shouldStopMoving, float range) {
        super(glumboss, timerEnd, coolDownEnd, animation, frameStart, frameEnd, shouldStopMoving, range);
        this.glumboss = glumboss;
        //how long the animation is in ticks
        this.timerEnd = timerEnd;
        //the minimum cooldown the attack must have to be able to happen again
        this.coolDownEnd = coolDownEnd;
        //the frame where you start doing special things, eg: dealing damage
        this.frameStart = frameStart;
        //the frame where you end doing special things
        this.frameEnd = frameEnd;
        //stops the entity from moving when plays its animation
        this.shouldStopMoving = shouldStopMoving;
    }


    @Override
    public boolean canUse() {
        if (this.glumboss.getTarget() != null && !this.glumboss.getCharged()) {
            return this.glumboss.getTarget().onGround();
        }
        return false;
    }

    @Override
    public void start() {
        super.start();
        coolDown = 0;
    }

    @Override
    public void tick() {
        if(!this.glumboss.getCharged()) {
            super.tick();
        }
    }

    public void attack() {
        LivingEntity target = this.glumboss.getTarget();
        if (target != null && this.glumboss.distanceTo(target) < 3.5f) {
            this.glumboss.doHurtTarget(this.glumboss.getTarget());
            this.glumboss.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 0.4f, 1f);
            target.setDeltaMovement(target.getDeltaMovement().add(0d, 0.2d, 0d));
        }
    }
}





