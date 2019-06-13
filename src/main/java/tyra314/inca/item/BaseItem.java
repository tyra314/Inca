package tyra314.inca.item;

import net.minecraft.item.Item;
import tyra314.inca.IncaMod;

class BaseItem extends Item
{
    private final String registry_key;

    BaseItem(String registry_key, Settings settings)
    {
        super(settings.group(IncaMod.ITEM_GROUP));

        this.registry_key = registry_key;
    }


    @SuppressWarnings("WeakerAccess")
    public String getRegistryKey()
    {
        return registry_key;
    }
}
