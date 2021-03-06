/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.content.type.item.processor;

import com.almuradev.content.registry.delegate.CatalogDelegate;
import com.almuradev.content.type.item.ContentItem;
import com.almuradev.content.type.item.ItemConfig;
import com.almuradev.content.type.item.ItemContentProcessor;
import com.almuradev.content.type.itemgroup.ItemGroup;
import com.almuradev.toolbox.config.tag.ConfigTag;
import ninja.leaping.configurate.ConfigurationNode;

public final class ItemGroupProcessor implements ItemContentProcessor.AnyTagged {
    private static final ConfigTag TAG = ConfigTag.create(ItemConfig.ITEM_GROUP);

    @Override
    public ConfigTag tag() {
        return TAG;
    }

    @Override
    public boolean required() {
        return false;
    }

    @Override
    public void processTagged(final ConfigurationNode config, final ContentItem.Builder builder) {
        builder.itemGroup(CatalogDelegate.namespaced(ItemGroup.class, config));
    }
}
