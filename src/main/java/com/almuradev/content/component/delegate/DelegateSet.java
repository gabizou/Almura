/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.content.component.delegate;

import com.almuradev.content.registry.delegate.CatalogDelegate;
import org.spongepowered.api.CatalogType;

import java.util.HashSet;
import java.util.Set;

public final class DelegateSet<V, R> extends DelegateCollection<V, R> implements Set<R> {
    public DelegateSet(final Class<R> real, final Set<Delegate<V>> delegates) {
        super(real, delegates);
    }

    public static final class Factory<V, R> {
        private final Class<V> virtual;
        private final Class<R> real;
        private final Set<Delegate<V>> delegates;

        public Factory(final Class<V> virtual, final Class<R> real, final int initialCapacity) {
            this.virtual = virtual;
            this.real = real;
            this.delegates = new HashSet<>(initialCapacity);
        }

        public void add(final Delegate<V> delegate) {
            this.delegates.add(delegate);
        }

        public <C extends CatalogType> void catalog(final String id) {
            this.delegates.add((Delegate<V>) CatalogDelegate.create((Class<C>) this.virtual, id));
        }

        public DelegateSet<V, R> build() {
            return new DelegateSet<>(this.real, this.delegates);
        }
    }
}
