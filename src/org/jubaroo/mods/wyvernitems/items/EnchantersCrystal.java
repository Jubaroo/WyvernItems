
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

public class EnchantersCrystal implements ItemTypes, MiscConstants {
	private static int enchantCrystalId;

	public static int getId() {
		return EnchantersCrystal.enchantCrystalId;
	}

	public static void onItemTemplatesCreated() throws IOException{
		try {
			String name = "enchanters crystal";
			final ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.crystal.enchanters");
			itemBuilder.name(name, "enchanters crystals", "This crystal can manipulate the magical properties of an item.");
			itemBuilder.descriptions("brilliantly glowing", "strongly glowing", "faintly glowing", "barely glowing");
			itemBuilder.itemTypes(new short[]{
					ItemTypes.ITEM_TYPE_MAGIC,
					ItemTypes.ITEM_TYPE_FULLPRICE,
					ItemTypes.ITEM_TYPE_NOSELLBACK,
					ItemTypes.ITEM_TYPE_ALWAYS_BANKABLE
			});
			itemBuilder.imageNumber((short) 462);
			itemBuilder.behaviourType(BehaviourList.itemBehaviour);
			itemBuilder.combatDamage(0);
			itemBuilder.decayTime(Long.MAX_VALUE);
			itemBuilder.dimensions(1, 1, 1);
			itemBuilder.primarySkill((int) NOID);
			itemBuilder.bodySpaces(EMPTY_BYTE_PRIMITIVE_ARRAY);
			itemBuilder.modelName("model.valrei.magic.");
			itemBuilder.difficulty(5.0f);
			itemBuilder.weightGrams(250);
			itemBuilder.material(Materials.MATERIAL_CRYSTAL);
			itemBuilder.value(Initiator.valueEnchantersCrystal);
			itemBuilder.isTraded(true);
			final ItemTemplate mirrorTemplate = itemBuilder.build();
			EnchantersCrystal.enchantCrystalId = mirrorTemplate.getTemplateId();
			Initiator.logger.log(Level.INFO, "Using template id " + EnchantersCrystal.enchantCrystalId);
		}
        catch (IOException e) {
		throw new RuntimeException(e);
		}
		Initiator.logger.log(Level.INFO, "Setup completed");
	}

	public static void onServerStarted() {
	}

}
