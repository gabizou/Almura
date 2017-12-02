/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.almura.feature.hud;

import com.almuradev.almura.core.common.WorldPermission;
import com.almuradev.almura.feature.hud.network.ClientboundPlayerCountPacket;
import com.almuradev.almura.feature.hud.network.ClientboundPlayerCountPacketHandler;
import com.almuradev.almura.feature.hud.network.ClientboundWorldNamePacket;
import com.almuradev.almura.feature.hud.network.ClientboundWorldNamePacketHandler;
import com.almuradev.shared.inject.CommonBinder;
import net.kyori.violet.AbstractModule;
import org.spongepowered.api.Platform;

public class HeadUpDisplayModule extends AbstractModule implements CommonBinder {

    @Override
    protected void configure() {
        this.packet()
                .bind(ClientboundWorldNamePacket.class, binder -> {
                    binder.channel(0);
                    binder.handler(ClientboundWorldNamePacketHandler.class, Platform.Type.CLIENT);
                })
                .bind(ClientboundPlayerCountPacket.class, binder -> {
                    binder.channel(1);
                    binder.handler(ClientboundPlayerCountPacketHandler.class, Platform.Type.CLIENT);
                });
        this.facet()
                .add(ServerHeadUpDisplay.class)
                .add(WorldPermission.class);
    }
}
