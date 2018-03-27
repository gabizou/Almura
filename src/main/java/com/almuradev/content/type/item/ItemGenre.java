/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.content.type.item;

import com.almuradev.content.ContentType;
import com.almuradev.content.type.item.type.food.FoodItem;
import com.almuradev.content.type.item.type.normal.NormalItem;
import com.almuradev.content.type.item.type.seed.SeedItem;
import com.almuradev.content.type.item.type.slab.SlabItem;

/**
 * An enumeration of item types.
 */
@ContentType.MultiType.Name("items")
public enum ItemGenre implements ContentType.MultiType<ContentItem, ContentItem.Builder<ContentItem>> {
    /**
     * An item type representing a normal item.
     */
    NORMAL("normal", NormalItem.Builder.class),
    /**
     * An item type representing a piece of food.
     */
    FOOD("food", FoodItem.Builder.class),
    /**
     * An item type representing a seed.
     */
    SEED("seed", SeedItem.Builder.class),
    /**
     * An item type representing a slab.
     */
    SLAB("slab", SlabItem.Builder.class);

    /**
     * The id of this item type.
     *
     * <p>The id is used for identification and loading.</p>
     */
    private final String id;
    private final Class<? extends ContentItem.Builder<? extends ContentItem>> builder;

    ItemGenre(final String id, final Class<? extends ContentItem.Builder<? extends ContentItem>> builder) {
        this.id = id;
        this.builder = builder;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<ContentItem.Builder<ContentItem>> builder() {
        return (Class<ContentItem.Builder<ContentItem>>) this.builder;
    }
}
