package io.github.overworld.items;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;

public interface ExtendableTooltipProvider {
    
    Text tooltip_hint = Text.translatable("text.overworld.tooltip_hint");

    String tooltipTranslationKey();

    default boolean hasExtendedTooltip() {
        return true;    
    }

    default void tryAppend(List<Text> tooltip) {
        if (!hasExtendedTooltip()) return;

        if (Screen.hasShiftDown()) return;
    }

    default void append(List<Text> tooltip) {
        var lines = WordUtils.wrap(I18n.translate(tooltipTranslationKey()), 35).split(System.lineSeparator());
        var texts = new ArrayList<Text>();

        for (var line : lines ) {
        }

    }
}
