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

import java.util.Collections;
import java.util.Set;

public interface RegistrySearch<T> {
    static <V> RegistrySearch<V> forResourceLocation(final ResourceLocation key) {
        return forResourceLocations(Collections.singleton(key));
    }

    static <V> RegistrySearch<V> forResourceLocations(final Set<ResourceLocation> keys) {
        return new ResourceLocationRegistrySearch<>(keys);
    }

    static <V> RegistrySearch<V> forString(final String key) {
        return forStrings(Collections.singleton(key));
    }

    static <V> RegistrySearch<V> forStrings(final Set<String> keys) {
        return new StringRegistrySearch<>(keys);
    }

    <F extends IForgeRegistryEntry<F>> void search(final IForgeRegistry<F> registry, final SearchResult<F> values);

    <V, R> void search(final DelegateSet.Factory<V, R> values);
}
