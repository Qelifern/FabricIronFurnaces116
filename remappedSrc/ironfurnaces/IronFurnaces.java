package ironfurnaces;

import ironfurnaces.init.Reference;
import net.fabricmc.api.ModInitializer;

public class IronFurnaces implements ModInitializer {

    @Override
    public void onInitialize() {
        Reference.init();
    }


}
