/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.content.registry.finder;

import com.almuradev.content.component.delegate.DelegateSet;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Collections;
import java.util.Set;

public final class RegistryFinderImpl<O extends IForgeRegistryEntry<O>> implements RegistryFinder<O> {
    private final IForgeRegistry<O> registry;

    RegistryFinderImpl(final IForgeRegistry<O> registry) {
        this.registry = registry;
    }

    @Override
    public O one(final RegistrySearch<O> key) {
        return this.all(Collections.singleton(key)).iterator().next();
    }

    @Override
    public Set<O> all(final Set<RegistrySearch<O>> keys) {
        return SearchResult.create(result -> {
            for (final RegistrySearch<O> key : keys) {
                key.search(this.registry, result);
            }
        });
    }

    @Override
    public <V extends O, R> DelegateSet<V, R> delegates(final Class<V> virtual, final Class<R> real, final Set<RegistrySearch<O>> keys) {
        final DelegateSet.Factory<V, R> factory = new DelegateSet.Factory<>(virtual, real, 0);
        for (final RegistrySearch<O> key : keys) {
            key.search(factory);
        }
        return factory.build();
    }
}
