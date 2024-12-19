package mei.arisuwu.deermod;

import mei.arisuwu.deermod.entity.DeerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer
{
	public static final String MOD_ID = "deermod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		FabricDefaultAttributeRegistry.register(ModEntities.DEER, DeerEntity.createAttributes());
		ModItems.addItemsToGroups();
		ModWorldGenerator.init();
	}
}