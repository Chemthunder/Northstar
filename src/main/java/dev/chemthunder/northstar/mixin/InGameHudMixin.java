package dev.chemthunder.northstar.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.chemthunder.northstar.Northstar;
import dev.chemthunder.northstar.init.NorthItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow @Final private static Identifier CROSSHAIR_TEXTURE;
    @Unique private static final Identifier SPARK_CROSSHAIR = Northstar.id("hud/spark_crosshair");

    @WrapOperation(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"))
    private void customCrosshair(DrawContext instance, Identifier sprite, int x, int y, int width, int height, Operation<Void> original) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (sprite == CROSSHAIR_TEXTURE && client.player != null && (client.player.getMainHandStack().isOf(NorthItems.LUMIUM_SPARK) || client.player.getOffHandStack().isOf(NorthItems.LUMIUM_SPARK))) {
            original.call(instance, SPARK_CROSSHAIR, x, y, width, height);
        } else {
            original.call(instance, sprite, x, y, width, height);
        }
    }
}