package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface NorthSounds {
    Map<SoundEvent, Identifier> SOUNDS = new LinkedHashMap<>();

    SoundEvent TERRAFORMAL = create("music.terraformal");
    SoundEvent BUTCHERVANITYOVERCOOKED = create("music.bvo");
    SoundEvent BIGSHOT = create("music.bigshot");
    SoundEvent SPARK_LOAD = create("item.spark_load");
    SoundEvent STORM_RING = create("event.storm_ring");
    SoundEvent HARBINGER = create("music.harbinger");
    SoundEvent HARBINGER_SUMMON = create("event.harbinger_summon");

    private static SoundEvent create(String name) {
        SoundEvent soundEvent = SoundEvent.of(Northstar.id(name));
        SOUNDS.put(soundEvent, Northstar.id(name));
        return soundEvent;
    }

    static void init() {
        SOUNDS.keySet().forEach(soundEvent -> {
            Registry.register(Registries.SOUND_EVENT, SOUNDS.get(soundEvent), soundEvent);
        });
    }
}
