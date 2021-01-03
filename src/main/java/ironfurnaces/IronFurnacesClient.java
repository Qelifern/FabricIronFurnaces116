package ironfurnaces;

import ironfurnaces.init.Reference;
import net.fabricmc.api.ClientModInitializer;

public class IronFurnacesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Reference.initClient();
    }

}
