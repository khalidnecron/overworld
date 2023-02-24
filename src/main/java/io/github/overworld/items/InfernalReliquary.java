package io.github.overworld.items;

import java.util.UUID;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import io.github.overworld.OverWorldMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.event.GameEvent;

public class InfernalReliquary extends TrinketItem {

    public InfernalReliquary(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {

        StatusEffectInstance effectInstance = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2, 0, false, false);
        entity.addStatusEffect(effectInstance);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference ref,
            LivingEntity entity, UUID uuid) {
        
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.getModifiers(stack, ref, entity, uuid);
        
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "overworld:max_health", -6,  EntityAttributeModifier.Operation.ADDITION));

        return modifiers;
    }

   
    public static boolean equipItem(PlayerEntity player, ItemStack stack) {
        var optional = TrinketsApi.getTrinketComponent(player);
		if (optional.isPresent()) {
			TrinketComponent comp = optional.get();
			for (var group : comp.getInventory().values()) {
				for (TrinketInventory inv : group.values()) {
					for (int i = 0; i < inv.size(); i++) {
						if (inv.getStack(i).isEmpty()) {
							SlotReference ref = new SlotReference(inv, i);
							if (TrinketSlot.canInsert(stack, ref, player)) {
								ItemStack newStack = stack.copy();
								inv.setStack(i, newStack);
								SoundEvent soundEvent = stack.getEquipSound();
								OverWorldMod.LOGGER.debug("SoundEvent = " + soundEvent.toString());
								if (!stack.isEmpty() && soundEvent != null) {
								   player.emitGameEvent(GameEvent.EQUIP);
								   player.playSound(soundEvent, 1.0F, 1.0F);
                                   player.damage(DamageSource.GENERIC, 1);
								   OverWorldMod.LOGGER.info("Player was damaged");
								}
								stack.setCount(0);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
