package io.github.overworld.items;

import dev.emi.trinkets.api.TrinketItem;

public class TrinketItemWithOptionalTooltip extends TrinketItem implements ExtendableTooltipProvider {

    public TrinketItemWithOptionalTooltip(Settings settings) {
        super(settings);
    }

    @Override
    public String tooltipTranslationKey() {
        throw new UnsupportedOperationException("Unimplemented method 'tooltipTranslationKey'");
    }
    
}
