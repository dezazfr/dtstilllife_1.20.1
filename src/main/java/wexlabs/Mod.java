package wexlabs;

import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.systems.pod.Pod;
import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.resources.Resources;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@net.minecraftforge.fml.common.Mod(Mod.MOD_ID)
public class Mod {
    public static final String MOD_ID = "dtstilllife";
    public static final String MOD_NAME = "DT - Still Life";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public Mod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        RegistryHandler.setup(MOD_ID);
        modEventBus.addListener(this::gatherData);
    }

    private void gatherData(final GatherDataEvent event) {
        Resources.MANAGER.gatherData();
        GatherDataHelper.gatherAllData(
                MOD_ID,
                event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                Fruit.REGISTRY,
                Pod.REGISTRY,
                CapProperties.REGISTRY
        );
    }


}


