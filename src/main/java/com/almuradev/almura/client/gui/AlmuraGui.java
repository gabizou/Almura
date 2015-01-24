/**
 * This file is part of Almura, All Rights Reserved.
 *
 * Copyright (c) 2014 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almura.client.gui;

import com.almuradev.almura.Almura;
import com.almuradev.almura.Filesystem;
import com.google.common.base.Optional;
import net.malisis.core.client.gui.GuiTexture;
import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.UIComponent;
import net.malisis.core.client.gui.icon.GuiIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public abstract class AlmuraGui extends MalisisGui {

    protected static final GuiTexture TEXTURE_DEFAULT;
    protected final Optional<AlmuraGui> parent;

    static {
        try {
            final ResourceLocation loc = Filesystem.registerTexture(Almura.MOD_ID, "textures/gui/gui.png", Filesystem.CONFIG_GUI_SPRITESHEET_PATH);
            final Dimension dim = Filesystem.getImageDimension(Filesystem.CONFIG_GUI_SPRITESHEET_PATH);
            TEXTURE_DEFAULT = new GuiTexture(loc, dim.width, dim.height);
        } catch (IOException e) {
            throw new RuntimeException("Failed load gui sprite sheet.", e);
        }
    }

    /**
     * Creates an gui with a parent screen and calls {@link AlmuraGui#setup}, if the parent is null then no background will be added
     * @param parent the {@link AlmuraGui} that we came from
     */
    public AlmuraGui(AlmuraGui parent) {
        renderer.setDefaultTexture(TEXTURE_DEFAULT);
        this.parent = Optional.fromNullable(parent);
        mc = Minecraft.getMinecraft();
    }

    /**
     * Snips out a {@link net.malisis.core.client.gui.icon.GuiIcon} based on the texture coordinates and size
     * @param x in pixels
     * @param y in pixels
     * @param width in pixels
     * @param height in pixels
     * @return the icon
     */
    protected static GuiIcon getIcon(int x, int y, int width, int height) {
        return TEXTURE_DEFAULT.getIcon(x, y, width, height);
    }

    protected abstract void setup();

    /**
     * Closes the current screen and displays the parent screen
     */
    @Override
    public void close() {
        Keyboard.enableRepeatEvents(false);
        if (mc.thePlayer != null) {
            mc.thePlayer.closeScreen();
        }
        mc.displayGuiScreen(parent.isPresent() ? parent.get() : null);
        mc.setIngameFocus();
    }

    protected int getPaddedX(UIComponent component, int padding) {
        if (component == null) {
            return 0;
        }
        return component.getX() + component.getWidth() + padding;
    }

    protected int getPaddedY(UIComponent component, int padding) {
        if (component == null) {
            return 0;
        }
        return component.getY() + component.getHeight() + padding;
    }
}
