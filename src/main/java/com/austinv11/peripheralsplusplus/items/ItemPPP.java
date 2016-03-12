package com.austinv11.peripheralsplusplus.items;

import com.austinv11.peripheralsplusplus.reference.Reference;
import net.minecraft.item.Item;

public abstract class ItemPPP extends Item {

	public ItemPPP() {
		this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" + getName());
	}

	public abstract String getName();
}
