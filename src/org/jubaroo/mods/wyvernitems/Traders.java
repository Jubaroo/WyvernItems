
package org.jubaroo.mods.wyvernitems;

import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateCreator;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.items.Item;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.jubaroo.mods.wyvernitems.items.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Level;

public class Traders {

    public static void onServerStarted() {
        if (Initiator.traderAffinityCatcher) {addItemToTrader(AffinityCatcher.getId());}
        if (Initiator.traderAffinityOrb) {addItemToTrader(AffinityOrb.getId());}
        if (Initiator.traderArrowPackHunting) {addItemToTrader(ArrowPackHunting.getId());}
        if (Initiator.traderArrowPackWar) {addItemToTrader(ArrowPackWar.getId());}
        if (Initiator.traderBookOfConversion) {addItemToTrader(BookOfConversion.getId());}
        if (Initiator.traderChaosCrystal) {addItemToTrader(ChaosCrystal.getId());}
        if (Initiator.traderCoinDecoration) {addItemToTrader(CoinDecoration.getId());}
        if (Initiator.traderDepthDrill) {addItemToTrader(DepthDrill.getId());}
        if (Initiator.traderDisintegrationRod) {addItemToTrader(DisintegrationRod.getId());}
        if (Initiator.traderEnchantersCrystal) {addItemToTrader(EnchantersCrystal.getId());}
        if (Initiator.traderEnchantOrb) {addItemToTrader(EnchantOrb.getId());}
        if (Initiator.traderEternalOrb) {addItemToTrader(EternalOrb.getId());}
    }
    
    private static void addItemToTrader(final int itemId) {
        try {
            final String descriptor = Descriptor.ofMethod(CtClass.voidType, new CtClass[] { HookManager.getInstance().getClassPool().get("com.wurmonline.server.creatures.Creature") });
            HookManager.getInstance().registerHook("com.wurmonline.server.economy.Shop", "createShop", descriptor, () -> new InvocationHandler() {
                @Override
                public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                    final Object result = method.invoke(proxy, args);
                    final Item inventory = ((Creature)args[0]).getInventory();
                    for (int x = 0; x < 3; ++x) {
                        final Item item = Creature.createItem(itemId, Initiator.traderQuality);
                        inventory.insertItem(item);
                        Initiator.logger.log(Level.INFO,"Added item ID: " + itemId);
                    }
                    return result;
                }
            });
        }
        catch (NotFoundException e) {
            Initiator.logger.log(Level.SEVERE, "Failed to add item to shop", e);
        }
    }

}
