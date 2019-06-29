package tyra314.inca;

import net.fabricmc.api.ClientModInitializer;
import tyra314.inca.handler.KeyBinds;

@SuppressWarnings("WeakerAccess")
public class IncaModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        IncaMod.LOG.info("Initializing client code...");
        KeyBinds.init();
    }
}
