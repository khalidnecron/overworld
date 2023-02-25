package io.github.overworld.items;

import java.util.UUID;

import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Rarity;


public class InfernalReliquary extends TrinketItemWithOptionalTooltip {

	public InfernalReliquary(Settings settings) {
		super(new Settings().maxCount(1).fireproof().rarity(Rarity.EPIC));
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return true;
	}

	@Override
	public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
		if(!(entity instanceof ServerPlayerEntity player)) return;

		player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2, 0, true, false, true));
		if(player.isOnFire()) player.setFireTicks(0);
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot,
			LivingEntity entity, UUID uuid) {

		var modifiers = super.getModifiers(stack, slot, entity, uuid);
		
		modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "overworld:max_health", -6, EntityAttributeModifier.Operation.ADDITION));

		return modifiers;
	}

	@Override
	public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
		
		if(!(entity instanceof PlayerEntity player)) return;

		player.damage(DamageSource.GENERIC, 1);

		super.onEquip(stack, slot, entity);
	}
}
