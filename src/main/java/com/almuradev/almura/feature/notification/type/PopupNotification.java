/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.almura.feature.notification.type;

import com.google.common.base.MoreObjects;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.api.text.Text;

@SideOnly(Side.CLIENT)
public final class PopupNotification extends Notification {

    private final int secondsToLive;

    public PopupNotification(Text title, Text message, int secondsToLive) {
        super(title, message);

        this.secondsToLive = secondsToLive;
    }

    public int getSecondsToLive() {
        return this.secondsToLive;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("title", this.title)
                .add("message", this.message)
                .add("seconds_to_live", this.secondsToLive)
                .toString();
    }
}
