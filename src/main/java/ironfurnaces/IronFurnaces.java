package ironfurnaces;

import ironfurnaces.init.Reference;
import ironfurnaces.tileentity.BlockWirelessHeaterTile;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class IronFurnaces implements ModInitializer {

    @Override
    public void onInitialize() {
        Reference.init();

    }


}
