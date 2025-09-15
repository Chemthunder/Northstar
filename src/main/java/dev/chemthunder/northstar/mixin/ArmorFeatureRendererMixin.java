package dev.chemthunder.northstar.mixin;

import dev.chemthunder.northstar.cca.HarbingerComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity> {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRenderArmor(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            T entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float customAngle,
            float headYaw,
            float headPitch,
            CallbackInfo ci
    ) {
        if (entity instanceof PlayerEntity player && !(player instanceof net.minecraft.client.network.ClientPlayerEntity)) {
           HarbingerComponent component = HarbingerComponent.KEY.get(player);

            if (component.isActive()) {
                boolean shouldBeVisible = player.getWorld().getPlayers().stream()
                        .filter(other -> other != player)
                        .anyMatch(other -> other.squaredDistanceTo(player) <= 25);

                if (!shouldBeVisible) {
                    ci.cancel(); // Cancel armor rendering
                }
            }
        }
    }
}
