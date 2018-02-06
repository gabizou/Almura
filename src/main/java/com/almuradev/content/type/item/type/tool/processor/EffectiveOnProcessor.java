/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.content.type.item.type.tool.processor;

import com.almuradev.content.registry.finder.RegistryFinder;
import com.almuradev.content.registry.finder.RegistrySearch;
import com.almuradev.content.type.item.type.tool.ToolItem;
import com.almuradev.content.type.item.type.tool.ToolItemConfig;
import com.almuradev.content.type.item.type.tool.ToolItemProcessor;
import com.almuradev.toolbox.config.tag.ConfigTag;
import net.minecraft.block.Block;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.Types;
import org.spongepowered.api.block.BlockType;

import java.util.Collections;
import java.util.HashSet;

import javax.inject.Inject;

public abstract class EffectiveOnProcessor implements ToolItemProcessor.AnyTagged<ToolItem, ToolItem.Builder<ToolItem>> {
    private static final ConfigTag TAG = ConfigTag.create(ToolItemConfig.EFFECTIVE_ON);
    private final RegistryFinder<BlockType> finder;

    EffectiveOnProcessor(final RegistryFinder<BlockType> finder) {
        this.finder = finder;
    }

    @Override
    public ConfigTag tag() {
        return TAG;
    }

    @Override
    public abstract boolean required();

    @Override
    public void processTagged(final ConfigurationNode config, final ToolItem.Builder builder) {
        builder.effectiveOn(this.finder.delegates(BlockType.class, Block.class, Collections.singleton(RegistrySearch.forStrings(new HashSet<>(config.getList(Types::asString))))));
    }

    public static final class Required extends EffectiveOnProcessor {
        @Inject
        private Required(final RegistryFinder<BlockType> finder) {
            super(finder);
        }

        @Override
        public boolean required() {
            return true;
        }
    }
}
