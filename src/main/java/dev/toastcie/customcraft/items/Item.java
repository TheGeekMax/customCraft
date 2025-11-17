package dev.toastcie.customcraft.items;

import dev.toastcie.customcraft.tile.sprites.ISprite;

public abstract class Item {
    protected boolean isWeapon = false;
    protected boolean isStackable = true;

    public abstract String getName();

    public abstract ISprite getSprite();
}
