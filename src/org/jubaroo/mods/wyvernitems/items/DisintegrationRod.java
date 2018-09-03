
package org.jubaroo.mods.wyvernitems.items;

import com.wurmonline.server.MiscConstants;
import com.wurmonline.server.behaviours.BehaviourList;
import com.wurmonline.server.items.ItemTemplate;
import com.wurmonline.server.items.ItemTypes;
import com.wurmonline.server.items.Materials;
import org.gotti.wurmunlimited.modsupport.ItemTemplateBuilder;
import org.jubaroo.mods.wyvernitems.Initiator;

import java.io.IOException;
import java.util.logging.Level;

public class DisintegrationRod implements ItemTypes, MiscConstants {
	private static int disintegrationRodId;

	public static int getId() {
		return DisintegrationRod.disintegrationRodId;
	}

	public static void onItemTemplatesCreated() throws IOException{
		try {
			String name = "Rod of Disintegration";
			final ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.rod.disintegration");
			itemBuilder.name(name, "rods of disintegration", "A rod designed for removal of ore veins and cave walls. It will remove " + Initiator.depleteAmount + " with each use." );
			itemBuilder.itemTypes(new short[]{
					ITEM_TYPE_FULLPRICE,
					ITEM_TYPE_NOSELLBACK,
					ITEM_TYPE_ALWAYS_BANKABLE
			});
			itemBuilder.imageNumber((short) 1259);
			itemBuilder.behaviourType(BehaviourList.itemBehaviour);
			itemBuilder.combatDamage(0);
			itemBuilder.decayTime(Long.MAX_VALUE);
			itemBuilder.dimensions(5, 10, 60);
			itemBuilder.primarySkill((int) NOID);
			itemBuilder.bodySpaces(MiscConstants.EMPTY_BYTE_PRIMITIVE_ARRAY);
			itemBuilder.modelName("model.tool.rodtrans.");
			itemBuilder.difficulty(99.0f);
			itemBuilder.weightGrams(1000);
			itemBuilder.material(Materials.MATERIAL_STONE);
			itemBuilder.value(Initiator.valueDisintegrationRod);
			itemBuilder.isTraded(true);
			final ItemTemplate mirrorTemplate = itemBuilder.build();
			DisintegrationRod.disintegrationRodId = mirrorTemplate.getTemplateId();
			Initiator.logger.log(Level.INFO,name + "Using template id " + DisintegrationRod.disintegrationRodId);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		Initiator.logger.log(Level.INFO, "Setup completed");
	}

	public static void onServerStarted() {
	}

}
