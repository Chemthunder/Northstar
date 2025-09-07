package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface NorthJukeboxSongs {
    RegistryKey<JukeboxSong> TERRAFORMAL = of("terraformal");
    RegistryKey<JukeboxSong> BUTCHERVANITYOVERCOOKED = of("bvo");
    RegistryKey<JukeboxSong> BIGSHOT = of("bigshot");
    RegistryKey<JukeboxSong> HARBINGER = of("harbinger");


    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Northstar.id(id));
    }
}
