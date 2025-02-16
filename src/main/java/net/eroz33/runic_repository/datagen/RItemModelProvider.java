package net.eroz33.runic_repository.datagen;

import net.eroz33.runic_repository.item.RItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static net.eroz33.runic_repository.core.RR.MOD_ID;

public class RItemModelProvider extends ItemModelProvider {
    public RItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(RItems.ARCANE_DUST.get());
        basicItem(RItems.ARCANE_PRIMER.get());
        basicItem(RItems.ARCANE_QUILL.get());
        basicItem(RItems.RUNIC_CORE_G.get());
        basicItem(RItems.RUNIC_CORE_R.get());
        basicItem(RItems.RUNIC_CORE_P.get());
        basicItem(RItems.RUNIC_CORE_B.get());
    }
}
