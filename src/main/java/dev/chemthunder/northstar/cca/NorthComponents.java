package dev.chemthunder.northstar.cca;

import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class NorthComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
      //  registry.beginRegistration(PlayerEntity.class, JetpackComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(JetpackComponent::new);
    }
}
