package dev.toastcie.customcraft.entities;

import dev.toastcie.customcraft.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player {

    List<ItemSlot> inventory;

    public Player() {
        inventory = new ArrayList<>();

    }

    public void addItem(Item item, int quantity) {
        for (ItemSlot slot : inventory) {
            if (slot.getItem().getName().equals(item.getName())) {
                slot.changeQuantity(quantity);
                return;
            }
        }
        inventory.add(new ItemSlot(item, quantity));
    }

    public boolean removeItem(Item item, int quantity) {
        for (ItemSlot slot : inventory) {
            if (slot.getItem().getName().equals(item.getName())) {
                if (slot.getQuantity() >= quantity) {
                    slot.changeQuantity(-quantity);
                    if (slot.getQuantity() <= 0) {
                        inventory.remove(slot);
                    }
                    return true;
                } else {
                    return false; // Not enough items to remove
                }
            }
        }
        return false; // Item not found
    }

    public List<ItemSlot> getInventory() {
        return inventory;
    }

    private class ItemSlot {
        Item item;
        int quantity;

        public ItemSlot(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public Item getItem() {
            return item;
        }

        public int getQuantity() {
            return quantity;
        }

        //methods to edit
        public void changeQuantity(int delta) {
            this.quantity += delta;
        }

    }
}
