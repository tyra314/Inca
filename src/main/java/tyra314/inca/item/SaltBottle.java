package tyra314.inca.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;

public class SaltBottle extends BaseItem
{
    SaltBottle()
    {
        super("salt_bottle", new Item.Settings());
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> list, TooltipContext context)
    {
        list.add(new TranslatableText("item.inca.salt_bottle.lore"));
    }
}
