package tyra314.inca.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class LlamaSpitBottle extends BaseItem
{
    public LlamaSpitBottle()
    {
        super("llama_spit_bottle", new Item.Settings().stackSize(1).rarity(Rarity.EPIC));
    }

    @Environment(EnvType.CLIENT)
    public boolean hasEnchantmentGlint(ItemStack stack) {
        return true;
    }
}
