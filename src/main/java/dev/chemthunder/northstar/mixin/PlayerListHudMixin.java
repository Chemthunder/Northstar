package dev.chemthunder.northstar.mixin;

import dev.chemthunder.northstar.cca.HarbingerComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(PlayerListHud.class)
public abstract class PlayerListHudMixin {

    private void hideCloakedPlayers(CallbackInfoReturnable<List<PlayerListEntry>> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();

        if (networkHandler == null) return;

        List<PlayerListEntry> originalList = cir.getReturnValue();

        List<PlayerListEntry> filtered = originalList.stream()
                .filter(entry -> {
                    // Don’t filter yourself
                    if (entry.getProfile().getId().equals(client.player.getUuid())) return true;

                    // Try to get the actual PlayerEntity for this entry
                    PlayerEntity player = client.world.getPlayerByUuid(entry.getProfile().getId());

                    if (player != null) {
                        HarbingerComponent component = HarbingerComponent.KEY.get(player);

                        if (component.isActive()) {
                            boolean shouldBeVisible = client.world.getPlayers().stream()
                                    .filter(other -> other != player)
                                    .anyMatch(other -> other.squaredDistanceTo(player) <= 25);

                            return shouldBeVisible;
                        }
                    }

                    return true; // show all others normally
                })
                .collect(Collectors.toList());

        cir.setReturnValue(filtered);
    }
}