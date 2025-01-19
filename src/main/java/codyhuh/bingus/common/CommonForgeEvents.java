package codyhuh.bingus.common;

import codyhuh.bingus.BingusMod;
import codyhuh.bingus.registry.CatVariantRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BingusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void shearCat(PlayerInteractEvent.EntityInteract e) {
        Entity entity = e.getTarget();
        Player player = e.getEntity();
        ItemStack stack = player.getItemInHand(e.getHand());

        if (entity instanceof Cat cat && !cat.getVariant().equals(CatVariantRegistry.BINGUS.get()) && stack.is(Tags.Items.SHEARS)) {
            cat.level().playSound(null, cat, SoundEvents.SHEEP_SHEAR, SoundSource.NEUTRAL, 1.0F, 1.0F);
            cat.level().playSound(null, cat, SoundEvents.CAT_HISS, SoundSource.NEUTRAL, 1.0F, 1.0F);

            cat.setVariant(CatVariantRegistry.BINGUS.get());

            e.setCanceled(true);
            e.setCancellationResult(InteractionResult.CONSUME);

            int i = 1 + cat.getRandom().nextInt(3);

            for(int j = 0; j < i; ++j) {
                ItemEntity itementity = cat.spawnAtLocation(Items.STRING, 1);
                if (itementity != null) {
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double)((cat.getRandom().nextFloat() - cat.getRandom().nextFloat()) * 0.1F), (double)(cat.getRandom().nextFloat() * 0.05F), (double)((cat.getRandom().nextFloat() - cat.getRandom().nextFloat()) * 0.1F)));
                }
            }

            if (!player.level().isClientSide) {
                stack.hurtAndBreak(1, player, (p_28927_) -> {
                    p_28927_.broadcastBreakEvent(e.getHand());
                });
            }
        }
    }
}
