package tyra314.inca.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.world.World;

import java.util.List;

public class SaltBottle extends BaseItem
{
    public SaltBottle()
    {
        super("salt_bottle", new Item.Settings());
    }

    @Environment(EnvType.CLIENT)
    public void buildTooltip(ItemStack stack, World world, List<TextComponent> list, TooltipContext context)
    {
        list.add(new StringTextComponent(I18n.translate("item.inca.salt_bottle.lore")));
    }
}
