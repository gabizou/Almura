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

import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nullable;

public interface RegistryFinder<O> extends Function<RegistrySearch<O>, O> {
    static <V, O extends IForgeRegistryEntry<O>> RegistryFinder<V> create(final IForgeRegistry<O> registry) {
        return (RegistryFinder<V>) new RegistryFinderImpl<>(registry);
    }

    /**
     * @deprecated this method only exists to implement {@link Function}
     */
    @Deprecated
    @Nullable
    @Override
    default O apply(final RegistrySearch<O> key) {
        return this.one(key);
    }

    O one(final RegistrySearch<O> key);

    Set<O> all(final Set<RegistrySearch<O>> keys);

    <V extends O, R> DelegateSet<V, R> delegates(final Class<V> virtual, final Class<R> real, final Set<RegistrySearch<O>> keys);
}
