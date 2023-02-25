package io.github.overworld.items;

import java.util.List;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class TrinketItemWithOptionalTooltip extends TrinketItem implements ExtendableTooltipProvider {

    public TrinketItemWithOptionalTooltip(Settings settings) {
        super(settings);
    }

    @Override
    public String tooltipTranslationKey() {
        return this.getTranslationKey() + ".tooltip";
    }
    
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }
}
