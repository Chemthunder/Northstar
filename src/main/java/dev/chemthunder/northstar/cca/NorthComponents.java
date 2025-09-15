package dev.chemthunder.northstar.cca;

import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class NorthComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
      //  registry.beginRegistration(PlayerEntity.class, JetpackComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(JetpackComponent::new);
        registry.beginRegistration(PlayerEntity.class, HarbingerComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(HarbingerComponent::new);
    }
}
