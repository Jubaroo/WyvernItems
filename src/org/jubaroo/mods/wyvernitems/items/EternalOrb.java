
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

public class EternalOrb implements ItemTypes, MiscConstants {
	private static int eternalOrbId;

	public static int getId() {
		return EternalOrb.eternalOrbId;
	}

	public static void onItemTemplatesCreated() throws IOException{
		try {
			String name = "eternal orb";
			final ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.eternal.orb");
			itemBuilder.name(name, "eternal orbs", "Legends say it consumes magic from an object, and moves it to another.");
			itemBuilder.itemTypes(new short[]{
					ItemTypes.ITEM_TYPE_FULLPRICE,
					ItemTypes.ITEM_TYPE_NOSELLBACK,
					ItemTypes.ITEM_TYPE_ALWAYS_BANKABLE
			});
			itemBuilder.imageNumber((short) 819);
			itemBuilder.behaviourType(BehaviourList.itemBehaviour);
			itemBuilder.combatDamage(0);
			itemBuilder.decayTime(Long.MAX_VALUE);
			itemBuilder.dimensions(1, 1, 1);
			itemBuilder.primarySkill((int) NOID);
			itemBuilder.bodySpaces(EMPTY_BYTE_PRIMITIVE_ARRAY);
			itemBuilder.modelName("model.artifact.orbdoom");
			itemBuilder.difficulty(5.0f);
			itemBuilder.weightGrams(500);
			itemBuilder.material(Materials.MATERIAL_CRYSTAL);
			itemBuilder.value(Initiator.valueEternalOrb);
			itemBuilder.isTraded(true);
			final ItemTemplate mirrorTemplate = itemBuilder.build();
			EternalOrb.eternalOrbId = mirrorTemplate.getTemplateId();
			Initiator.logger.log(Level.INFO, "Using template id " + EternalOrb.eternalOrbId);
	}
		catch (IOException e) {
		throw new RuntimeException(e);
	}
		Initiator.logger.log(Level.INFO, "Setup completed");
}

	public static void onServerStarted() {
	}

}
