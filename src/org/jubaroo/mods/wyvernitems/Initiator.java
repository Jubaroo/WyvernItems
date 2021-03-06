
package org.jubaroo.mods.wyvernitems;

import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.interfaces.*;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;
import org.jubaroo.mods.wyvernitems.actions.*;
import org.jubaroo.mods.wyvernitems.items.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Initiator implements WurmServerMod, ItemTemplatesCreatedListener, Configurable, ServerStartedListener, PreInitable {
    public static Logger logger;
    public static int depleteAmount;
    static float traderQuality;
    static boolean traderAffinityCatcher;
    static boolean traderAffinityOrb;
    static boolean traderArrowPackHunting;
    static boolean traderArrowPackWar;
    static boolean traderBookOfConversion;
    static boolean traderChaosCrystal;
    static boolean traderCoinDecoration;
    static boolean traderDepthDrill;
    static boolean traderDisintegrationRod;
    static boolean traderEnchantersCrystal;
    static boolean traderEnchantOrb;
    static boolean traderEternalOrb;
    public static int valueAffinityCatcher;
    public static int valueAffinityOrb;
    public static int valueArrowPackHunting;
    public static int valueArrowPackWar;
    public static int valueBookOfConversion;
    public static int valueChaosCrystal;
    public static int valueCoinDecoration;
    public static int valueDepthDrill;
    public static int valueDisintegrationRod;
    public static int valueEnchantersCrystal;
    public static int valueEnchantOrb;
    public static int valueEternalOrb;

    static {
        Initiator.logger = Logger.getLogger(Initiator.class.getName());
        Initiator.depleteAmount = 10000; // ore removed per rod
        Initiator.traderQuality = 99.0f;
        Initiator.traderAffinityCatcher = true;
        Initiator.traderAffinityOrb = true;
        Initiator.traderArrowPackHunting = true;
        Initiator.traderArrowPackWar = true;
        Initiator.traderBookOfConversion = true;
        Initiator.traderChaosCrystal = true;
        Initiator.traderCoinDecoration = true;
        Initiator.traderDepthDrill = true;
        Initiator.traderDisintegrationRod = true;
        Initiator.traderEnchantersCrystal = true;
        Initiator.traderEnchantOrb = true;
        Initiator.traderEternalOrb = true;
        Initiator.valueAffinityCatcher = 10000;
        Initiator.valueAffinityOrb = 5000;
        Initiator.valueArrowPackHunting = 1000;
        Initiator.valueArrowPackWar = 1000;
        Initiator.valueBookOfConversion = 50000;
        Initiator.valueChaosCrystal = 5000;
        Initiator.valueCoinDecoration = 100;
        Initiator.valueDepthDrill = 100;
        Initiator.valueDisintegrationRod = 1000;
        Initiator.valueEnchantersCrystal = 5000;
        Initiator.valueEnchantOrb = 50000;
        Initiator.valueEternalOrb = 20000;
    }

    public void configure(Properties p) {
        double copper = 100;
        double silver = 10000;
        double gold = 1000000;
        DecimalFormat comma = new DecimalFormat("#,###,###,###");
        DecimalFormat decimal = new DecimalFormat("#.############");
        Initiator.logger.info("===================== Wyvern Items Mod =====================");
        Initiator.logger.log(Level.INFO, "configure called");
        Initiator.depleteAmount = Integer.valueOf( p.getProperty("Disintegration_Rod_Deplete_Amount", String.valueOf(Initiator.depleteAmount)));
        Initiator.traderQuality = Float.valueOf(p.getProperty("Trader_Item_Quality", String.valueOf(Initiator.traderQuality)));
        // Trader items
        Initiator.traderAffinityCatcher = Boolean.valueOf(p.getProperty("Affinity_Catcher", String.valueOf(Initiator.traderAffinityCatcher)));
        Initiator.traderAffinityOrb = Boolean.valueOf(p.getProperty("Affinity_Orb", String.valueOf(Initiator.traderAffinityOrb)));
        Initiator.traderArrowPackHunting = Boolean.valueOf(p.getProperty("Arrow_Pack_Hunting", String.valueOf(Initiator.traderArrowPackHunting)));
        Initiator.traderArrowPackWar = Boolean.valueOf(p.getProperty("Arrow_Pack_War", String.valueOf(Initiator.traderArrowPackWar)));
        Initiator.traderBookOfConversion = Boolean.valueOf(p.getProperty("Book_Of_Conversion", String.valueOf(Initiator.traderBookOfConversion)));
        Initiator.traderChaosCrystal = Boolean.valueOf(p.getProperty("Chaos_Crystal", String.valueOf(Initiator.traderChaosCrystal)));
        Initiator.traderCoinDecoration = Boolean.valueOf(p.getProperty("Coin_Decoration", String.valueOf(Initiator.traderCoinDecoration)));
        Initiator.traderDepthDrill = Boolean.valueOf(p.getProperty("Depth_Drill", String.valueOf(Initiator.traderDepthDrill)));
        Initiator.traderDisintegrationRod = Boolean.valueOf(p.getProperty("Disintegration_Rod", String.valueOf(Initiator.traderDisintegrationRod)));
        Initiator.traderEnchantersCrystal = Boolean.valueOf(p.getProperty("Enchanters_Crystal", String.valueOf(Initiator.traderEnchantersCrystal)));
        Initiator.traderEnchantOrb = Boolean.valueOf(p.getProperty("Enchant_Orb", String.valueOf(Initiator.traderEnchantOrb)));
        Initiator.traderEternalOrb = Boolean.valueOf(p.getProperty("Eternal_Orb", String.valueOf(Initiator.traderEternalOrb)));
        // Values
        Initiator.valueAffinityCatcher = Integer.valueOf( p.getProperty("Affinity_Catcher_Value", String.valueOf(Initiator.valueAffinityCatcher)));
        Initiator.valueAffinityOrb = Integer.valueOf( p.getProperty("Affinity_Orb_Value", String.valueOf(Initiator.valueAffinityOrb)));
        Initiator.valueArrowPackHunting = Integer.valueOf( p.getProperty("Arrow_Pack_Hunting_Value", String.valueOf(Initiator.valueArrowPackHunting)));
        Initiator.valueArrowPackWar = Integer.valueOf( p.getProperty("Arrow_Pack_War_Value", String.valueOf(Initiator.valueArrowPackWar)));
        Initiator.valueBookOfConversion = Integer.valueOf( p.getProperty("Book_Of_Conversion_Value", String.valueOf(Initiator.valueBookOfConversion)));
        Initiator.valueChaosCrystal = Integer.valueOf( p.getProperty("Chaos_Crystal_Value", String.valueOf(Initiator.valueChaosCrystal)));
        Initiator.valueCoinDecoration = Integer.valueOf( p.getProperty("Coin_Decoration_Value", String.valueOf(Initiator.valueCoinDecoration)));
        Initiator.valueDepthDrill = Integer.valueOf( p.getProperty("Depth_Drill_Value", String.valueOf(Initiator.valueDepthDrill)));
        Initiator.valueDisintegrationRod = Integer.valueOf( p.getProperty("Disintegration_Rod_Value", String.valueOf(Initiator.valueDisintegrationRod)));
        Initiator.valueEnchantersCrystal = Integer.valueOf( p.getProperty("Enchanters_Crystal_Value", String.valueOf(Initiator.valueEnchantersCrystal)));
        Initiator.valueEnchantOrb = Integer.valueOf( p.getProperty("Enchant_Orb_Value", String.valueOf(Initiator.valueEnchantOrb)));
        Initiator.valueEternalOrb = Integer.valueOf( p.getProperty("Eternal_Orb_Value", String.valueOf(Initiator.valueEternalOrb)));
        // Logging
        Initiator.logger.info("====== Settings ======");
        Initiator.logger.info("Disintegration_Rod_Deplete_Amount = " + comma.format(depleteAmount));
        Initiator.logger.info("Trader_Item_Quality = " + traderQuality);
        Initiator.logger.info("====== Items On Traders ======");
        if (Initiator.traderAffinityCatcher) { Initiator.logger.log(Level.INFO, "Affinity_Catcher: Enabled"); }
        if (!Initiator.traderAffinityCatcher) { Initiator.logger.log(Level.INFO, "Affinity_Catcher: Disabled"); }
        if (Initiator.traderAffinityOrb) { Initiator.logger.log(Level.INFO, "Affinity_Orb: Enabled"); }
        if (!Initiator.traderAffinityOrb) { Initiator.logger.log(Level.INFO, "Affinity_Orb: Disabled"); }
        if (Initiator.traderArrowPackHunting) { Initiator.logger.log(Level.INFO, "Arrow_Pack_Hunting: Enabled"); }
        if (!Initiator.traderArrowPackHunting) { Initiator.logger.log(Level.INFO, "Arrow_Pack_Hunting: Disabled"); }
        if (Initiator.traderArrowPackWar) { Initiator.logger.log(Level.INFO, "Arrow_Pack_War: Enabled"); }
        if (!Initiator.traderArrowPackWar) { Initiator.logger.log(Level.INFO, "Arrow_Pack_War: Disabled"); }
        if (Initiator.traderBookOfConversion) { Initiator.logger.log(Level.INFO, "Book_Of_Conversion: Enabled"); }
        if (!Initiator.traderBookOfConversion) { Initiator.logger.log(Level.INFO, "Book_Of_Conversion: Disabled"); }
        if (Initiator.traderChaosCrystal) { Initiator.logger.log(Level.INFO, "Chaos_Crystal: Enabled"); }
        if (!Initiator.traderChaosCrystal) { Initiator.logger.log(Level.INFO, "Chaos_Crystal: Disabled"); }
        if (Initiator.traderCoinDecoration) { Initiator.logger.log(Level.INFO, "Coin_Decoration: Enabled"); }
        if (!Initiator.traderCoinDecoration) { Initiator.logger.log(Level.INFO, "Coin_Decoration: Disabled"); }
        if (Initiator.traderDepthDrill) { Initiator.logger.log(Level.INFO, "Depth_Drill: Enabled"); }
        if (!Initiator.traderDepthDrill) { Initiator.logger.log(Level.INFO, "Depth_Drill: Disabled"); }
        if (Initiator.traderDisintegrationRod) { Initiator.logger.log(Level.INFO, "Disintegration_Rod: Enabled"); }
        if (!Initiator.traderDisintegrationRod) { Initiator.logger.log(Level.INFO, "Disintegration_Rod: Disabled"); }
        if (Initiator.traderEnchantersCrystal) { Initiator.logger.log(Level.INFO, "Enchanters_Crystal: Enabled"); }
        if (!Initiator.traderEnchantersCrystal) { Initiator.logger.log(Level.INFO, "Enchanters_Crystal: Disabled"); }
        if (Initiator.traderEnchantOrb) { Initiator.logger.log(Level.INFO, "Enchant_Orb: Enabled"); }
        if (!Initiator.traderEnchantOrb) { Initiator.logger.log(Level.INFO, "Enchant_Orb: Disabled"); }
        if (Initiator.traderEternalOrb) { Initiator.logger.log(Level.INFO, "Eternal_Orb: Enabled"); }
        if (!Initiator.traderEternalOrb) { Initiator.logger.log(Level.INFO, "Eternal_Orb: Disabled"); }
        Initiator.logger.info("====== Value Of Items ======");
        Initiator.logger.info("Affinity_Catcher_Value = " + comma.format(valueAffinityCatcher) + " iron, or " + decimal.format(valueAffinityCatcher/copper) + " copper, or " + decimal.format(valueAffinityCatcher/silver) + " silver, or " + decimal.format(valueAffinityCatcher/gold) + " gold");
        Initiator.logger.info("Affinity_Orb_Value = " + comma.format(valueAffinityOrb) + " iron, or " + decimal.format(valueAffinityOrb/copper) + " copper, or " + decimal.format(valueAffinityOrb/silver) + " silver, or " + decimal.format(valueAffinityOrb/gold) + " gold");
        Initiator.logger.info("Arrow_Pack_Hunting_Value = " + comma.format(valueArrowPackHunting) + " iron, or " + decimal.format(valueArrowPackHunting/copper) + " copper, or " + decimal.format(valueArrowPackHunting/silver) + " silver, or " + decimal.format(valueArrowPackHunting/gold) + " gold");
        Initiator.logger.info("Arrow_Pack_Hunting_Value = " + comma.format(valueArrowPackWar) + " iron, or " + decimal.format(valueArrowPackWar/copper) + " copper, or " + decimal.format(valueArrowPackWar/silver) + " silver, or " + decimal.format(valueArrowPackWar/gold) + " gold");
        Initiator.logger.info("Arrow_Pack_War_Value = " + comma.format(valueBookOfConversion) + " iron, or " + decimal.format(valueBookOfConversion/copper) + " copper, or " + decimal.format(valueBookOfConversion/silver) + " silver, or " + decimal.format(valueBookOfConversion/gold) + " gold");
        Initiator.logger.info("Book_Of_Conversion_Value = " + comma.format(valueAffinityCatcher) + " iron, or " + decimal.format(valueAffinityCatcher/copper) + " copper, or " + decimal.format(valueAffinityCatcher/silver) + " silver, or " + decimal.format(valueAffinityCatcher/gold) + " gold");
        Initiator.logger.info("Chaos_Crystal_Value = " + comma.format(valueChaosCrystal) + " iron, or " + decimal.format(valueChaosCrystal/copper) + " copper, or " + decimal.format(valueChaosCrystal/silver) + " silver, or " + decimal.format(valueChaosCrystal/gold) + " gold");
        Initiator.logger.info("Coin_Decoration_Value = " + comma.format(valueCoinDecoration) + " iron, or " + decimal.format(valueCoinDecoration/copper) + " copper, or " + decimal.format(valueCoinDecoration/silver) + " silver, or " + decimal.format(valueCoinDecoration/gold) + " gold");
        Initiator.logger.info("Depth_Drill_Value = " + comma.format(valueDepthDrill) + " iron, or " + decimal.format(valueDepthDrill/copper) + " copper, or " + decimal.format(valueDepthDrill/silver) + " silver, or " + decimal.format(valueDepthDrill/gold) + " gold");
        Initiator.logger.info("Disintegration_Rod_Value = " + comma.format(valueDisintegrationRod) + " iron, or " + decimal.format(valueDisintegrationRod/copper) + " copper, or " + decimal.format(valueDisintegrationRod/silver) + " silver, or " + decimal.format(valueDisintegrationRod/gold) + " gold");
        Initiator.logger.info("Enchanters_Crystal_Value = " + comma.format(valueEnchantersCrystal) + " iron, or " + decimal.format(valueEnchantersCrystal/copper) + " copper, or " + decimal.format(valueEnchantersCrystal/silver) + " silver, or " + decimal.format(valueEnchantersCrystal/gold) + " gold");
        Initiator.logger.info("Enchant_Orb_Value = " + comma.format(valueEnchantOrb) + " iron, or " + decimal.format(valueEnchantOrb/copper) + " copper, or " + decimal.format(valueEnchantOrb/silver) + " silver, or " + decimal.format(valueEnchantOrb/gold) + " gold");
        Initiator.logger.info("Eternal_Orb_Value = " + comma.format(valueEternalOrb) + " iron, or " + decimal.format(valueEternalOrb/copper) + " copper, or " + decimal.format(valueEternalOrb/silver) + " silver, or " + decimal.format(valueEternalOrb/gold) + " gold");
    }

    public void preInit() {
        logger.log(Level.INFO, "preInit called");
        try {
            ModActions.init();
        } catch (IllegalArgumentException | ClassCastException e) {
            throw new HookException(e);
        }
        logger.log(Level.INFO, "all preInit completed");
    }

    @Override
    public void onItemTemplatesCreated() {
        logger.log(Level.INFO, "onItemTemplatesCreated called");
        try {
            logger.info("======= Creating Item Mod items =======");
            this.addItems();
            Traders.onServerStarted();
        } catch ( IllegalArgumentException | ClassCastException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Error in onItemTemplatesCreated()", e);
        }
        logger.log(Level.INFO, "all onItemTemplatesCreated completed");
    }

    private void addItems() {
        try {
            AffinityCatcher.onItemTemplatesCreated();
            AffinityOrb.onItemTemplatesCreated();
            ArrowPackHunting.onItemTemplatesCreated();
            ArrowPackWar.onItemTemplatesCreated();
            BookOfConversion.onItemTemplatesCreated();
            ChaosCrystal.onItemTemplatesCreated();
            CoinDecoration.onItemTemplatesCreated();
            DepthDrill.onItemTemplatesCreated();
            DisintegrationRod.onItemTemplatesCreated();
            EnchantersCrystal.onItemTemplatesCreated();
            EnchantOrb.onItemTemplatesCreated();
            EternalOrb.onItemTemplatesCreated();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupItems() {
        AffinityCatcher.onServerStarted();
        AffinityOrb.onServerStarted();
        ArrowPackHunting.onServerStarted();
        ArrowPackWar.onServerStarted();
        BookOfConversion.onServerStarted();
        ChaosCrystal.onServerStarted();
        CoinDecoration.onServerStarted();
        DepthDrill.onServerStarted();
        DisintegrationRod.onServerStarted();
        EnchantersCrystal.onServerStarted();
        EnchantOrb.onServerStarted();
        EternalOrb.onServerStarted();
    }

    @Override
    public void onServerStarted() {
        logger.log(Level.INFO, "onServerStarted called");
        try {
            logger.info("Registering Item Creation Entries...");
            this.setupItems();
            logger.info("Registering Item Actions...");
            Initiator.registerActions();
            logger.info("Adding Items To Traders...");
        }
        catch (IllegalArgumentException | ClassCastException e) {
            logger.log(Level.SEVERE, "Error in modifyItemsOnServerStarted()", e);
        }
        logger.log(Level.INFO, "all onServerStarted completed");
    }

    private static void registerActions(){
        ModActions.registerAction(new AffinityCatcherCaptureAction());
        ModActions.registerAction(new AffinityCatcherConsumeAction());
        ModActions.registerAction(new AffinityOrbAction());
        ModActions.registerAction(new ArrowPackUnpackAction());
        ModActions.registerAction(new BookConversionAction());
        ModActions.registerAction(new ChaosCrystalInfuseAction());
        ModActions.registerAction(new DepthDrillAction());
        ModActions.registerAction(new DisintegrationRodAction());
        ModActions.registerAction(new EnchantersCrystalInfuseAction());
        ModActions.registerAction(new EnchantOrbAction());
        ModActions.registerAction(new EternalOrbAction());
    }

    public String getVersion() { return "v1.0"; }

}