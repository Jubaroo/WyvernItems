
package org.jubaroo.mods.wyvernitems.items;

import com.wurmonline.server.MiscConstants;
import com.wurmonline.server.behaviours.BehaviourList;
import com.wurmonline.server.items.*;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.ItemTemplateBuilder;
import org.jubaroo.mods.wyvernitems.Initiator;

import java.io.IOException;
import java.util.logging.Level;

public class CoinDecoration implements ItemTypes, MiscConstants {
	public static String name = "coin pile";
	private static int coinDecorationId;

	public static int getId() {
		return CoinDecoration.coinDecorationId;
	}

	public static void onItemTemplatesCreated() throws IOException{
		try {
			final ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.coin.pile");
			itemBuilder.name(name, "coin piles", "A pile of decorative coins.");
			itemBuilder.descriptions("excellent", "good", "ok", "poor");
			itemBuilder.itemTypes(new short[]{
					ItemTypes.ITEM_TYPE_NAMED,
					ItemTypes.ITEM_TYPE_OWNER_DESTROYABLE,
					ItemTypes.ITEM_TYPE_DESTROYABLE,
					ItemTypes.ITEM_TYPE_TURNABLE,
					ItemTypes.ITEM_TYPE_DECORATION,
					ItemTypes.ITEM_TYPE_NOT_MISSION,
					ItemTypes.ITEM_TYPE_REPAIRABLE,
					ItemTypes.ITEM_TYPE_COLORABLE
			});
			itemBuilder.imageNumber((short) 572);
			itemBuilder.behaviourType(BehaviourList.itemBehaviour);
			itemBuilder.combatDamage(0);
			itemBuilder.decayTime(Long.MAX_VALUE);
			itemBuilder.dimensions(5, 5, 5);
			itemBuilder.primarySkill(SkillList.MISCELLANEOUS);
			itemBuilder.bodySpaces(EMPTY_BYTE_PRIMITIVE_ARRAY);
			itemBuilder.modelName("model.pile.coin.");
			itemBuilder.difficulty(70.0f);
			itemBuilder.weightGrams(1000);
			itemBuilder.material(Materials.MATERIAL_COPPER);
			itemBuilder.value(Initiator.valueCoinDecoration);
			final ItemTemplate mirrorTemplate = itemBuilder.build();
			CoinDecoration.coinDecorationId = mirrorTemplate.getTemplateId();
			Initiator.logger.log(Level.INFO, CoinDecoration.name + "Using template id " + CoinDecoration.coinDecorationId);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		Initiator.logger.log(Level.INFO, "Setup completed");
	}
	
	public static void onServerStarted(){
		Initiator.logger.info("initCreationEntry()");
		if(CoinDecoration.coinDecorationId > 0){
			Initiator.logger.info("Creating "+name+" creation entry, ID = "+CoinDecoration.coinDecorationId);
			final AdvancedCreationEntry coinDeco = CreationEntryCreator.createAdvancedEntry(SkillList.MISCELLANEOUS, ItemList.coinCopper, ItemList.coinCopper, CoinDecoration.coinDecorationId, false, false, 0f, true, false, CreationCategories.DECORATION);
			coinDeco.addRequirement(new CreationRequirement(1, ItemList.coinCopper, 3, true));
		}else{
			Initiator.logger.info(name+" does not have a template ID on creation entry.");
		}
	}
}
