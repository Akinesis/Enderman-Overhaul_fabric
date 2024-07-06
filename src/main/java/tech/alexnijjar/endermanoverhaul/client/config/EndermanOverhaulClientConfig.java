package tech.alexnijjar.endermanoverhaul.client.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.Config;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigInfo;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;
import tech.alexnijjar.endermanoverhaul.client.config.info.EndermanOverhaulConfigInfo;

@Config("endermanoverhaul-client")
@ConfigInfo.Provider(EndermanOverhaulConfigInfo.class)
public final class EndermanOverhaulClientConfig {

    @ConfigEntry(
        id = "replaceDefaultEnderman",
        type = EntryType.BOOLEAN,
        translation = "config.endermanoverhaul.replaceDefaultEnderman"
    )
    @Comment("Replaces the default Enderman model with a better-looking animated one.")
    public static boolean replaceDefaultEnderman = true;

    @ConfigEntry(
        id = "replaceMekanismBabyEnderman",
        type = EntryType.BOOLEAN,
        translation = "config.endermanoverhaul.replaceMekanismBabyEnderman"
    )
    @Comment("Replaces the Mekanism Baby Enderman model with a better-looking animated one when installed.")
    public static boolean replaceMekanismBabyEnderman = true;

    @ConfigEntry(
        id = "flashScreen",
        type = EntryType.BOOLEAN,
        translation = "config.endermanoverhaul.flashScreen"
    )
    @Comment("Flashes the screen when getting teleported by an End Enderman.")
    public static boolean flashScreen = true;
}
