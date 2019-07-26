package tyra314.inca.item;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tyra314.inca.IncaMod;

public class ModItems
{
    public static final BaseItem LLAMA_SPIT_BOTTLE = new LlamaSpitBottle();

    @SuppressWarnings("WeakerAccess")
    public static final BaseItem SALT_BOTTLE = new SaltBottle();

    @SuppressWarnings("WeakerAccess")
    public static final Tag<Item> SALT = TagRegistry.item(new Identifier(IncaMod.SHARED_NAMESPACE, "salt"));

    public static void init()
    {
        register(LLAMA_SPIT_BOTTLE);
        register(SALT_BOTTLE);

//        TagEntryManager.registerToTag(TagType.ITEM, SALT.getId(), Registry.ITEM.getId(SALT_BOTTLE).toString());
    }

    private static void register(BaseItem item)
    {
        Registry.register(Registry.ITEM, new Identifier(IncaMod.MOD_ID, item.getRegistryKey()), item);
    }
}
