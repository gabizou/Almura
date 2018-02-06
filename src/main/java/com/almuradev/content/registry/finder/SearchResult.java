/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.content.registry.finder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nullable;

public interface SearchResult<V> {
    static <V> Set<V> create(final Consumer<SearchResult<V>> consumer) {
        final Impl<V> result = new Impl<>();
        consumer.accept(result);
        return result.values;
    }

    void add(final Object key, @Nullable final V value);

    class Impl<V> implements SearchResult<V> {
        final Set<V> values = new HashSet<>();

        @Override
        public void add(final Object key, @Nullable final V value) {
            this.values.add(Objects.requireNonNull(value, key::toString));
        }
    }
}
