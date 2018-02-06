/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.content.registry.finder;

import com.almuradev.content.component.delegate.DelegateSet;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Set;

final class ResourceLocationRegistrySearch<T> implements RegistrySearch<T> {
    private final Set<ResourceLocation> keys;

    ResourceLocationRegistrySearch(final Set<ResourceLocation> keys) {
        this.keys = keys;
    }

    @Override
    public <F extends IForgeRegistryEntry<F>> void search(final IForgeRegistry<F> registry, final SearchResult<F> values) {
        for (final ResourceLocation key : this.keys) {
            values.add(key, registry.getValue(key));
        }
    }

    @Override
    public <V, R> void search(final DelegateSet.Factory<V, R> values) {
        for (final ResourceLocation key : this.keys) {
            values.catalog(key.toString());
        }
    }
}
