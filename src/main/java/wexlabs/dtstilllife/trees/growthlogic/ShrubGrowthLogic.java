package wexlabs.dtstilllife.trees.growthlogic;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKitConfiguration;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionManipulationContext;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class ShrubGrowthLogic extends GrowthLogicKit {
    public static final ConfigurationProperty<Integer> MAX_TRUNK_HEIGHT = ConfigurationProperty.integer("max_trunk_height");
    public ShrubGrowthLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(MAX_TRUNK_HEIGHT, 1);
    }

    @Override
    protected void registerProperties() {
        this.register(MAX_TRUNK_HEIGHT);
    }

    /**
     * Trunk fixed to one block in height, branches spread outward from there
     */
    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final int[] probMap = super.populateDirectionProbabilityMap(configuration, context);

        //TODO: Shrubs die if leaves ever disconnect, and possibly if branches grow into the trunk?
        if (context.signal().isInTrunk() && context.signal().delta.getY() >= configuration.get(MAX_TRUNK_HEIGHT)) {
            probMap[Direction.UP.get3DDataValue()] = 0;
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                probMap[dir.get3DDataValue()] = 10;
            }
        }
        return probMap;
    }
}
