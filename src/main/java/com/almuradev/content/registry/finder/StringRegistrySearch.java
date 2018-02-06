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

import java.util.Map;
import java.util.Set;

final class StringRegistrySearch<T> implements RegistrySearch<T> {
    private final Set<String> keys;

    StringRegistrySearch(final Set<String> keys) {
        this.keys = keys;
    }

    @Override
    public <F extends IForgeRegistryEntry<F>> void search(final IForgeRegistry<F> registry, final SearchResult<F> values) {
        for (final String key : this.keys) {
            // Assume namespace search if no path specified
            if (key.indexOf(':') == -1) {
                for (final Map.Entry<ResourceLocation, F> entry : registry.getEntries()) {
                    if (entry.getKey().getResourceDomain().equals(key)) {
                        values.add(entry.getKey(), entry.getValue());
                    }
                }
            } else {
                values.add(key, registry.getValue(new ResourceLocation(key)));
            }
        }
    }

    @Override
    public <V, R> void search(final DelegateSet.Factory<V, R> values) {
        for (final String key : this.keys) {
            values.catalog(key);
        }
    }
}
