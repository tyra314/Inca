package tyra314.inca.item;

import net.minecraft.item.Item;
import tyra314.inca.IncaMod;

public class BaseItem extends Item
{
    private final String registry_key;

    public BaseItem(String registry_key, Settings settings)
    {
        super(settings.itemGroup(IncaMod.ITEM_GROUP));

        this.registry_key = registry_key;
    }


    @SuppressWarnings("WeakerAccess")
    public String getRegistryKey()
    {
        return registry_key;
    }
}
