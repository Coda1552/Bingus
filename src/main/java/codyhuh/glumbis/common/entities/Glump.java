package codyhuh.glumbis.common.entities;

import codyhuh.glumbis.common.entities.goal.glump.GlumpAttackGoal;
import codyhuh.glumbis.common.entities.goal.glump.GlumpPounceGoal;
import codyhuh.glumbis.registry.ModParticles;
import codyhuh.glumbis.registry.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class Glump extends Monster {
    private static final EntityDataAccessor<Boolean> EXPLODING = SynchedEntityData.defineId(Glump.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Glump.class, EntityDataSerializers.INT);

    public Glump(EntityType<? extends Glump> p_i48567_1_, Level p_i48567_2_) {
        super(p_i48567_1_, p_i48567_2_);
        this.setNoGravity(true);
        //this.noPhysics = true;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new GlumpPounceGoal(this));
        this.goalSelector.addGoal(1, new GlumpAttackGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D).add(Attributes.MOVEMENT_SPEED, 0.265F).add(Attributes.FLYING_SPEED, 0.35F).add(Attributes.ATTACK_DAMAGE, 4.0F).add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    public int getExperienceReward() {
        return 0;
    }

    @Override
    public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return source.is(DamageTypes.ON_FIRE) && super.hurt(source, amount);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EXPLODING, false);
        this.entityData.define(VARIANT, 0);
    }

    @Override
    public void tick() {
        super.tick();
        boolean damageTick = tickCount % 20 == 10;
        boolean soundTick = tickCount % 120 == 10;
        if(tickCount <= 1){int randomVariant = this.getRandom().nextInt(9);
            if(randomVariant == 8){
                if(this.getRandom().nextFloat() < 0.05){
                    this.setVariant(randomVariant);
                }
                else{
                    this.setVariant(randomVariant - 1);
                }
            }
            else {
                this.setVariant(randomVariant);
            }
            this.level().addParticle(ParticleTypes.POOF, this.getRandomX(0.5D), this.getRandomY() + 1.25D, this.getRandomZ(0.5D), 0, 0.08d, 0);
        }
        if (level().isRainingAt(blockPosition())) {
            if (damageTick) {
                hurt(damageSources().drown(), 1.0F);
            }
        }
        if (this.level().isClientSide()) {
            if (this.getRandom().nextFloat() < 0.56f) {
                for(int i = 0; i < 5; i++) {
                    this.level().addParticle(ModParticles.STATIC_LIGHTNING.get(), this.getRandomX(0.5D), this.getRandomY() + 0.25D, this.getRandomZ(0.5D), 0, 0.08d, 0);
                }
            }
        }
        if (getExploding()) {
            if(this.level().isClientSide()) {
                for (int i = 0; i < 20; i++) {
                    this.level().addParticle(ModParticles.STATIC_LIGHTNING.get(), this.getRandomX(3.5D), (this.getPosition(1.0f).y() - 0.5) , this.getRandomZ(3.5D), 0, this.getRandomY() * 2, 0);
                }
            }
            playSound(ModSounds.GLUMP_EXPLODE.get(), 1.0F, (1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.7F);
            this.discard();
        }
        if (soundTick) {
            playSound(ModSounds.GLUMP_FLY.get(), 0.4F, 1.0F);
        }

        if (isOnFire()) {
            setRemainingFireTicks(0);
        }

    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return ModSounds.GLUMP_HURT.get();
    }

    @Override
    public void thunderHit(ServerLevel p_19927_, LightningBolt p_19928_) {
    }

    public void setExploding(boolean exploding){
        this.entityData.set(EXPLODING, exploding);
    }

    public boolean getExploding(){
        return this.entityData.get(EXPLODING);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }
}
