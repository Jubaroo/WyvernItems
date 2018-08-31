
package org.jubaroo.mods.wyvernitems.items;

import com.wurmonline.server.MiscConstants;
import com.wurmonline.server.behaviours.BehaviourList;
import com.wurmonline.server.items.*;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.ItemTemplateBuilder;
import org.jubaroo.mods.wyvernitems.Initiator;

import java.io.IOException;
import java.util.logging.Level;

public class ArrowPackWar {
	private static int arrowPackWarId;

	public static int getId() {
		return ArrowPackWar.arrowPackWarId;
	}

	public static void onItemTemplatesCreated() throws IOException{
		try {
			String name = "war arrow pack";
			final ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.arrowpack.war");
			itemBuilder.name(name, "war arrow packs", "A pack of arrows, able to be unpacked into a full quiver.");
			itemBuilder.descriptions("excellent", "good", "ok", "poor");
			itemBuilder.itemTypes(new short[]{ // {108, 146, 44, 21, 147, 113} - War Arrow
					ItemTypes.ITEM_TYPE_NAMED,
					ItemTypes.ITEM_TYPE_REPAIRABLE,
					ItemTypes.ITEM_TYPE_WOOD
			});
			itemBuilder.imageNumber((short) 760);
			itemBuilder.behaviourType(BehaviourList.itemBehaviour);
			itemBuilder.combatDamage(0);
			itemBuilder.decayTime(Long.MAX_VALUE);
			itemBuilder.dimensions(15, 15, 50);
			itemBuilder.primarySkill(-10);
			itemBuilder.bodySpaces(MiscConstants.EMPTY_BYTE_PRIMITIVE_ARRAY);
			itemBuilder.modelName("model.container.quiver.");
			itemBuilder.difficulty(55.0f);
			itemBuilder.weightGrams(4500);
			itemBuilder.material(Materials.MATERIAL_WOOD_BIRCH);
			itemBuilder.value(Initiator.valueArrowPackWar);
			itemBuilder.isTraded(true);
			final ItemTemplate mirrorTemplate = itemBuilder.build();
			ArrowPackWar.arrowPackWarId = mirrorTemplate.getTemplateId();
			Initiator.logger.log(Level.INFO,name + "Using template id " + ArrowPackWar.arrowPackWarId);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		Initiator.logger.log(Level.INFO, "Setup completed");
	}
	
	public static void onServerStarted(){
		Initiator.logger.info("initCreationEntry()");
		if(ArrowPackWar.arrowPackWarId > 0){
	        AdvancedCreationEntry huntingPack = CreationEntryCreator.createAdvancedEntry(SkillList.GROUP_FLETCHING, ItemList.quiver, ItemList.arrowWar, ArrowPackWar.arrowPackWarId, false, false, 0.0F, true, false, 0, 50.0D, CreationCategories.FLETCHING);
	        huntingPack.addRequirement(new CreationRequirement(1, ItemList.arrowWar, 39, true));
		}
    }

}
