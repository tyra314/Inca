package tyra314.inca.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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
    public void buildTooltip(ItemStack stack, World world, List<Component> list, TooltipContext context)
    {
        list.add(new TextComponent(I18n.translate("item.inca.salt_bottle.lore")));
    }
}
