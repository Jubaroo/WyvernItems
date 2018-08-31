
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

public class EnchantOrb implements ItemTypes, MiscConstants {
	private static int enchantOrbId;

	public static int getId() {
		return EnchantOrb.enchantOrbId;
	}

	public static void onItemTemplatesCreated() throws IOException{
		try {
			String name = "enchant orb";
			final ItemTemplateBuilder itemBuilder = new ItemTemplateBuilder("mod.item.enchantorb");
			itemBuilder.name(name, "enchant orbs", "It shimmers lightly, the magic inside waiting for a proper vessel.");
			itemBuilder.descriptions("vibrant", "glowing", "faint", "empty");
			itemBuilder.itemTypes(new short[]{
					ITEM_TYPE_MAGIC,
					ITEM_TYPE_INDESTRUCTIBLE
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
			itemBuilder.value(Initiator.valueEnchantOrb);
			itemBuilder.isTraded(true);
			final ItemTemplate mirrorTemplate = itemBuilder.build();
			EnchantOrb.enchantOrbId = mirrorTemplate.getTemplateId();
			Initiator.logger.log(Level.INFO, "Using template id " + EnchantOrb.enchantOrbId);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		Initiator.logger.log(Level.INFO, "Setup completed");
	}

	public static void onServerStarted() {
	}

}
