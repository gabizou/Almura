/**
 * This file is part of Almura, All Rights Reserved.
 *
 * Copyright (c) 2014 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almura.pack.node.property;

import net.minecraft.block.Block;

public class HydrationProperty extends GameObjectProperty {

    private final int neededProximity;

    public HydrationProperty(Block block, int neededProximity) {
        super(block);
        this.neededProximity = neededProximity;
    }

    public int getNeededProximity() {
        return neededProximity;
    }

    public boolean isInRange(int radius) {
        return radius <= neededProximity;
    }
}
