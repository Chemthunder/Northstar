package dev.chemthunder.northstar.cca;

import dev.chemthunder.northstar.Northstar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.component.ComponentV3;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class HarbingerComponent implements ComponentV3, CommonTickingComponent, AutoSyncedComponent {
    public static final ComponentKey<HarbingerComponent> KEY =
            ComponentRegistryV3.INSTANCE.getOrCreate(Northstar.id("harbinger"), HarbingerComponent.class);

    private final PlayerEntity player;
    private boolean active = false;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public HarbingerComponent(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void tick() {
        if (!active) return;
        boolean shouldBeVisible = player.getWorld().getPlayers().stream()
                .filter(other -> other != player)
                .anyMatch(other -> other.squaredDistanceTo(player) <= 5); // 5^2

        player.setInvisible(!shouldBeVisible);
    }



    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("Active", this.active);
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.active = nbtCompound.getBoolean("Active");
    }
}
