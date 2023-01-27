package fr.mrcubee.forgeproxy;

import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeProxyConfig {

    private final ForgeConfigSpec.ConfigValue<ProxyMode> configModeValue;
    private final ForgeConfigSpec.ConfigValue<String> configSecretValue;

    public ForgeProxyConfig(final ForgeConfigSpec.Builder builder) {
        this.configModeValue = builder.comment("Choose mod between BUNGEECORD and VELOCITY").defineEnum("mode", ProxyMode.BUNGEECORD);
        this.configSecretValue = builder.comment("If you choose VELOCITY mod you need to enter secret.").define("secret", "");
    }

    public ProxyMode getProxyMode() {
        return this.configModeValue.get();
    }

    public String getSecret() {
        final String secret = this.configSecretValue.get();

        if (secret.isEmpty())
            return null;
        return secret;
    }

}
