package fr.mrcubee.forgeproxy;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.network.NetworkConstants;
import org.slf4j.Logger;

@Mod("forgeproxy")
public class ForgeProxyMod
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public final ForgeProxyConfig config;
    public final ForgeConfigSpec configSpec;

    public ForgeProxyMod() {
        final ModLoadingContext context = ModLoadingContext.get();
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        context.registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY,
                        (incoming, isNetwork) -> true));
        this.config = new ForgeProxyConfig(builder);
        this.configSpec = builder.build();
        context.registerConfig(ModConfig.Type.SERVER, this.configSpec);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(final ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

}
