package io.github.overworld.items;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class InfernalReliquary extends TrinketItem {

    public InfernalReliquary(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        
        StatusEffectInstance effectInstance = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2, 0, false, false);
        entity.addStatusEffect(effectInstance);
    }
}
