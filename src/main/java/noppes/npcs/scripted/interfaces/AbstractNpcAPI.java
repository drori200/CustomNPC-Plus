//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package noppes.npcs.scripted.interfaces;

import java.io.File;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.EventBus;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.controllers.*;
import noppes.npcs.scripted.ScriptSound;
import noppes.npcs.scripted.gui.ScriptGui;
import noppes.npcs.scripted.interfaces.entity.ICustomNpc;
import noppes.npcs.scripted.interfaces.entity.IEntity;
import noppes.npcs.scripted.interfaces.entity.IPlayer;
import noppes.npcs.scripted.interfaces.gui.ICustomGui;
import noppes.npcs.scripted.interfaces.handler.*;
import noppes.npcs.scripted.interfaces.handler.data.ISound;
import noppes.npcs.scripted.interfaces.item.IItemStack;
import noppes.npcs.scripted.interfaces.overlay.ICustomOverlay;
import noppes.npcs.scripted.overlay.ScriptOverlay;

/**
 * This object stores functions available to all scripting handlers through the "API" keyword.
 *
 */
public abstract class AbstractNpcAPI {
    private static AbstractNpcAPI instance = null;

    public AbstractNpcAPI() {
    }

    public abstract long sizeOfObject(Object obj);

    public abstract void stopServer();

    public abstract IFactionHandler getFactions();

    public abstract IRecipeHandler getRecipes();

    public abstract IQuestHandler getQuests();

    public abstract IDialogHandler getDialogs();

    public abstract ICloneHandler getClones();

    public abstract INaturalSpawnsHandler getNaturalSpawns();

    public abstract ITransportHandler getLocations();

    public abstract String[] getAllBiomeNames();

    public abstract ICustomNpc createNPC(World var1);

    /**
     *
     * Spawns a new NPC in the world at the given coordinates and returns an ICustomNpc object of it.
     */
    public abstract ICustomNpc spawnNPC(World var1, int var2, int var3, int var4);

    public abstract IEntity getIEntity(Entity var1);

    public abstract IEntity[] getLoadedEntities();

    public abstract IBlock getIBlock(World var1, BlockPos var2);

    public abstract IBlock getIBlock(World world, Block block, BlockPos pos);

    public abstract IBlock getIBlock(World world, int x, int y, int z);

    public abstract IContainer getIContainer(IInventory var1);

    public abstract IContainer getIContainer(Container var1);

    public abstract IItemStack getIItemStack(ItemStack var1);

    public abstract IWorld getIWorld(World var1);

    public abstract IWorld getIWorld(int var1);

    public abstract IWorld[] getIWorlds();

    public abstract IDamageSource getIDamageSource(DamageSource var1);

    public abstract IDamageSource getIDamageSource(IEntity entity);

    public abstract EventBus events();

    public abstract File getGlobalDir();

    public abstract File getWorldDir();

    public static boolean IsAvailable() {
        return Loader.isModLoaded("customnpcs");
    }

    public static AbstractNpcAPI Instance() {
        if (instance != null) {
            return instance;
        } else if (!IsAvailable()) {
            return null;
        } else {
            try {
                Class c = Class.forName("noppes.npcs.scripted.NpcAPI");
                instance = (AbstractNpcAPI) c.getMethod("Instance").invoke((Object) null);
            } catch (Exception var1) {
                var1.printStackTrace();
            }

            return instance;
        }
    }

    public abstract void executeCommand(IWorld var1, String var2);

    /**
     * Generates a new name as a String using the Markov name generator.
     *
     * @param dictionary An integer representing which dictionary to use:
     *                   0: Roman
     *                   1: Japanese
     *                   2: Slavic
     *                   3: Welsh
     *                   4: Saami
     *                   5: Old Norse
     *                   6: Ancient Greek
     *                   7: Aztec
     *                   8: CustomNPCs Classic
     *                   9: Spanish
     * @param gender The gender of the name:
     *                   0: Random
     *                   1: Male
     *                   2: Female
     */
    public abstract String getRandomName(int dictionary, int gender);

    public abstract INbt getINbt(NBTTagCompound nbtTagCompound);

    public abstract INbt stringToNbt(String str);

    public abstract IPlayer[] getAllServerPlayers();

    public abstract String[] getPlayerNames();

    public abstract IItemStack createItem(String id, int damage, int size);

    public abstract void playSoundAtEntity(IEntity entity, String sound, float volume, float pitch);

    public abstract void playSoundToNearExcept(IPlayer player, String sound, float volume, float pitch);

    /**
     *
     * @return Returns the server's Message of The Day.
     */
    public abstract String getMOTD();

    /**
     * @param motd The server's new Message of The Day.
     */
    public abstract void setMOTD(String motd);

    /**
     *
     * @return A new IParticle object initialized with the given texture.
     */
    public abstract IParticle createParticle(String directory);

    @Deprecated
    public abstract IParticle createEntityParticle(String directory);

    public abstract ISound createSound(String directory);

    public abstract void playSound(int id, ISound sound);

    public abstract void stopSound(int id);

    public abstract void pauseSounds();

    public abstract void continueSounds();

    public abstract void stopSounds();

    /**
     *
     * @return The uptime of the server in MC ticks.
     */
    public abstract int getServerTime();

    public abstract boolean arePlayerScriptsEnabled();

    public abstract boolean areForgeScriptsEnabled();

    public abstract boolean areGlobalNPCScriptsEnabled();

    public abstract void enablePlayerScripts(boolean enable);

    public abstract void enableForgeScripts(boolean enable);

    public abstract void enableGlobalNPCScripts(boolean enable);

    /**
     *
     * @param id The id of the custom GUI.
     * @param width The width of the GUI in pixels.
     * @param height The height of the GUI in pixels.
     * @param pauseGame Whether the GUI pauses the game or not.
     * @return A new ICustomGui object with the given attributes.
     */
    public abstract ICustomGui createCustomGui(int id, int width, int height, boolean pauseGame);

    /**
     *
     * @return  A new ICustomOverlay overlay object with the given ID.
     */
    public abstract ICustomOverlay createCustomOverlay(int id);

    /**
     *
     * @return A new ISkinOverlay object initialized with the given texture.
     */
    public abstract ISkinOverlay createSkinOverlay(String texture);
}
