
package org.jubaroo.mods.wyvernitems.items;

import com.wurmonline.server.MiscConstants;
import com.wurmonline.server.behaviours.BehaviourList;
import com.wurmonline.server.items.*;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.ItemTemplateBuilder;
import org.jubaroo.mods.wyvernitems.Initiator;

import java.io.IOException;
import java.util.logging.Level;

public class DepthDrill implements ItemTypes, MiscConstants {
	private static String name = "depth drill";
	private static int depthDrillId;

	public static int getId() {
		return DepthDrill.depthDrillId;
	}

	public static void onItemTemplatesCreated() throws IOException{
		try {
			final ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.depthdrill");
			itemBuilder.name(name, "depth drills", "A tool for determining dirt depth.");
			itemBuilder.itemTypes(new short[]{
					ItemTypes.ITEM_TYPE_NAMED,
					ItemTypes.ITEM_TYPE_REPAIRABLE,
					ItemTypes.ITEM_TYPE_TOOL,
					ItemTypes.ITEM_TYPE_WEAPON_PIERCE
			});
			itemBuilder.imageNumber((short) 60);
			itemBuilder.behaviourType(BehaviourList.itemBehaviour);
			itemBuilder.combatDamage(0);
			itemBuilder.decayTime(Long.MAX_VALUE);
			itemBuilder.dimensions(6, 6, 96);
			itemBuilder.primarySkill(SkillList.CARPENTRY_FINE);
			itemBuilder.bodySpaces(EMPTY_BYTE_PRIMITIVE_ARRAY);
			itemBuilder.modelName("model.resource.shaft.");
			itemBuilder.difficulty(30.0f);
			itemBuilder.weightGrams(1100);
			itemBuilder.material(Materials.MATERIAL_IRON);
			itemBuilder.value(Initiator.valueDepthDrill);
			final ItemTemplate mirrorTemplate = itemBuilder.build();
			DepthDrill.depthDrillId = mirrorTemplate.getTemplateId();
			Initiator.logger.log(Level.INFO, CoinDecoration.name + "Using template id " + DepthDrill.depthDrillId);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		Initiator.logger.log(Level.INFO, "Setup completed");
	}
	
	public static void onServerStarted(){
		Initiator.logger.info("initCreationEntry()");
		if(DepthDrill.depthDrillId > 0){
			Initiator.logger.info("Creating "+name+" creation entry, ID = "+DepthDrill.depthDrillId);
			final AdvancedCreationEntry entry = CreationEntryCreator.createAdvancedEntry(SkillList.CARPENTRY_FINE, ItemList.ironBand, ItemList.shaft, DepthDrill.depthDrillId, false, false, 0f, true, false, CreationCategories.TOOLS);
			entry.addRequirement(new CreationRequirement(1, ItemList.woodenHandleSword, 2, true));
			entry.addRequirement(new CreationRequirement(2, ItemList.nailsIronSmall, 1, true));
		}else{
			Initiator.logger.info("Depth Drill does not have a template ID on creation entry.");
		}
	}

}
