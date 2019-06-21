package com.gmail.nossr50.util.nbt;


import com.gmail.nossr50.mcMMO;
import net.minecraft.server.v1_13_R2.NBTBase;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_13_R2.util.CraftNBTTagConfigSerializer;
import org.bukkit.inventory.ItemStack;



public class NBTManager {

    private static final String CRAFT_META_ITEM_CLASS_PATH = "org.bukkit.craftbukkit.inventory.CraftMetaItem";
    private Class<?> craftMetaItemClass;

    public NBTManager() {
        init(); //Setup method references etc
    }

    private void init() {
        try {
            Class<?> craftMetaItemClass = Class.forName(CRAFT_META_ITEM_CLASS_PATH); //for type comparisons
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public RawNBT<?> constructNBT(String nbtString) {
        try {
            return new RawNBT<NBTBase>(nbtString, CraftNBTTagConfigSerializer.deserialize(nbtString));
        } catch (Exception e) {
            e.printStackTrace();
            mcMMO.p.getLogger().severe("mcMMO was unable parse the NBT string from your config! Double check that it is proper NBT!");
            return null;
        }
    }

    public void printNBT(ItemStack itemStack) {
        Bukkit.broadcastMessage("Checking NBT for "+itemStack.toString());
        net.minecraft.server.v1_13_R2.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound rootTag = nmsItemStack.getTag();
        for(String key : rootTag.getKeys()) {
            Bukkit.broadcastMessage("NBT Key found: "+key);
        }
    }

}