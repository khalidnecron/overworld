package io.github.overworld;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.overworld.items.InfernalReliquary;

public class OverWorldMod implements ModInitializer {

	public static final String MOD_ID = "overworld";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final InfernalReliquary INFERNAL_RELIQUARY = new InfernalReliquary(null);

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "infernal_reliquary"), INFERNAL_RELIQUARY);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
			content.add(INFERNAL_RELIQUARY);
		});
	}
}
