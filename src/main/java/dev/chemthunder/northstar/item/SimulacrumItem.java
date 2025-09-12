package dev.chemthunder.northstar.item;

import dev.chemthunder.northstar.init.NorthDamageTypes;
import dev.chemthunder.northstar.init.NorthSounds;
import net.acoyt.acornlib.api.item.AdvBurningItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SimulacrumItem extends Item {
    public SimulacrumItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        Entity owner = entity.getOwner();
        World world = owner.getWorld();
        BlockPos pos = entity.getBlockPos();

        owner.sendMessage(Text.translatable("text.simulacrum.destroy").formatted(Formatting.YELLOW));

        if (entity.getOwner() instanceof ServerPlayerEntity player) {
            player.playSoundToPlayer(NorthSounds.HARBINGER_SUMMON, SoundCategory.MASTER, 1, 1);
            player.sendMessage(Text.translatable("text.simulacrum.user").formatted(Formatting.YELLOW), true);
        }

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.setTimeOfDay(18000);
            serverWorld.setWeather(0, 1200, true, false);
            serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, entity.getX(), entity.getY() + 0.5, entity.getZ(), 75, 0.05, 1, 0.05, 0.2);
            serverWorld.playSound(entity, pos, SoundEvents.BLOCK_TRIAL_SPAWNER_BREAK, SoundCategory.MASTER, 1, 0);
        }
        super.onItemEntityDestroyed(entity);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
user.damage(NorthDamageTypes.enraptured(user), 5f);
        return super.use(world, user, hand);
    }
}
