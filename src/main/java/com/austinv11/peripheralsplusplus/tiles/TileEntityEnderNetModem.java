package com.austinv11.peripheralsplusplus.tiles;

import com.austinv11.peripheralsplusplus.blocks.BlockAntenna;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityEnderNetModem extends MountedNetworkedTileEntity {

	public static String publicName = "enderNetModem";
	private  String name = "tileEntityEnderNetModem";
	private boolean isServer = false;
	private int lastMeta = 0;

	public TileEntityEnderNetModem() {
		super();
	}

	public String getName() {
		return name;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
	}

	@Override
	public String getType() {
		return publicName;
	}

	@Override
	public String[] getMethodNames() {
		return new String[]{"broadcastMessage",/*Broadcasts a message (essentially long range rednet)*/
				"openSocket",/*Opens a server, returns I/O handle for it. Only works if it has a modem connected*/
				"openConnection"/*Connects to server, returns a "secure" I/O handle for it*/};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
		return new Object[0];
	}

	@Override
	public void updateEntity() {
		if (worldObj != null) {
			checkMultiblockStatus();
			if (isServer && lastMeta == 0) {
				lastMeta = 1;
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, lastMeta, 2);
			} else if (!isServer && lastMeta == 1) {
				lastMeta = 0;
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, lastMeta, 2);
			}
		}
	}

	private void checkMultiblockStatus() {
		if (!worldObj.isAirBlock(xCoord, yCoord+1, zCoord))
			if (worldObj.getBlock(xCoord, yCoord+1, zCoord) instanceof BlockAntenna)
				isServer = true;
			else
				isServer = false;
	}

	@Override
	public boolean equals(IPeripheral other) {
		return (other == this);
	}
}
