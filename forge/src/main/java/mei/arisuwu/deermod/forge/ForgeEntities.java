package mei.arisuwu.deermod.forge;

import mei.arisuwu.deermod.Mod;
import mei.arisuwu.deermod.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ForgeEntities extends ModEntities
{
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Mod.MOD_ID);

    public ForgeEntities(FMLJavaModLoadingContext context)
    {
        ENTITY_TYPES.register(context.getModEventBus());
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.Builder<T> builder)
    {
        return ENTITY_TYPES.register(name, () -> builder.build(name));
    }
}
