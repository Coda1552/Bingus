package codyhuh.bingus.common.entities;

import codyhuh.bingus.common.entities.goal.MousePanicGoal;
import codyhuh.bingus.registry.ModEntities;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class Mouse extends Animal {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Mouse.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_FLEEING = SynchedEntityData.defineId(Mouse.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> FLEEING_TICKS = SynchedEntityData.defineId(Mouse.class, EntityDataSerializers.INT);

    public Mouse(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new MousePanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.BREAD), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.25D, 1.25D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this) {
            @Override
            public boolean canUse() {
                return !isPassenger() && super.canUse();
            }
        });
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D, 40));
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.BREAD);
    }

    public static AttributeSupplier.Builder createMouseAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 5.0D).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    public static int chooseVariant(RandomSource rand) {
        if (rand.nextFloat() > 0.95F) {
            return 4;
        }
        else if (rand.nextFloat() > 0.6F) {
            return rand.nextIntBetweenInclusive(1, 3);
        }
        else {
            return 0;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (getVehicle() instanceof Cat cat) {
            lookAt(EntityAnchorArgument.Anchor.EYES, position().add(0.0D, getBbHeight(), 0.0D));
        }

        if (getFleeingTicks() > 0 && isFleeing()) {
            setFleeingTicks(getFleeingTicks() - 1);
        }

        if (getFleeingTicks() == 0 && isFleeing()) {
            setFleeing(false);
        }

    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        for (Mouse nearbyMouse : level().getNearbyEntities(Mouse.class, TargetingConditions.forCombat(), this, getBoundingBox().inflate(8.0D))) {
            nearbyMouse.setFleeing(true);
            nearbyMouse.setFleeingTicks(100 + random.nextInt(50, 100));
        }
        setFleeing(true);
        setFleeingTicks(100 + random.nextInt(50, 100));

        if (source.getDirectEntity() instanceof Cat || source.getDirectEntity() instanceof Ocelot) {
            Mob cat = (Mob) source.getDirectEntity();

            cat.setTarget(null);
            startRiding(cat, true);
        }

        return super.hurt(source, amount);
    }

    // todo - figure out a better way to stop mice from controlling cats they are currently riding, the method below makes (any entity) with a mouse passenger stop their AI entirely
    @Override
    public boolean isEffectiveAi() {
        return super.isEffectiveAi() && !isPassenger();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Mouse mouse = ModEntities.MOUSE.get().create(pLevel);
        mouse.setVariant(chooseVariant(getRandom()));

        return mouse;
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height * 0.8F;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_FLEEING, false);
        this.entityData.define(FLEEING_TICKS, 0);
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public boolean isFleeing() {
        return this.entityData.get(IS_FLEEING);
    }

    public void setFleeing(boolean fleeing) {
        this.entityData.set(IS_FLEEING, fleeing);
    }

    public int getFleeingTicks() {
        return this.entityData.get(FLEEING_TICKS);
    }

    public void setFleeingTicks(int fleeingTicks) {
        this.entityData.set(FLEEING_TICKS, fleeingTicks);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (dataTag == null) {
            setVariant(chooseVariant(getRandom()));
        } else {
            if (dataTag.contains("Variant", 3)) {
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
    }
}
