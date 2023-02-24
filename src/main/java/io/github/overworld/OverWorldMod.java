package io.github.overworld;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.overworld.items.InfernalReliquary;

public class OverWorldMod implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("overworld");
	public static final InfernalReliquary INFERNAL_RELIQUARY = new InfernalReliquary(
		new FabricItemSettings().fireproof().rarity(Rarity.EPIC).maxCount(1)
	);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		Registry.register(Registries.ITEM, new Identifier("overworld", "infernal_reliquary"), INFERNAL_RELIQUARY);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> { 
			content.add(INFERNAL_RELIQUARY);
		});
	}
}
