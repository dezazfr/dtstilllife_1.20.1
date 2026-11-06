package wexlabs;

import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wexlabs.dtstilllife.cancellers.StillLifeFeatureCanceller;
import wexlabs.dtstilllife.trees.cells.CellKits;
import wexlabs.dtstilllife.trees.features.ExtraBottomFlareGenFeature;
import wexlabs.dtstilllife.trees.features.ForestSoilGenFeature;
import wexlabs.dtstilllife.trees.features.ReplaceOnRadiusGenFeature;
import wexlabs.dtstilllife.trees.growthlogic.BaobabGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.CanopyGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.CypressGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.JuniperGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.LeaningPalmGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.LongBranchCanopyGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.RedwoodGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.ShrubGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.SmallRedwoodGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.UpAndOutGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.VariateHeightGrowthLogic;
import wexlabs.dtstilllife.trees.growthlogic.WillowGrowthLogic;
import wexlabs.dtstilllife.trees.species.DiagonalPalmFamily;
import wexlabs.dtstilllife.trees.species.GenUnderwaterSpecies;

@EventBusSubscriber(modid = DTStillLife.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Registrator {
    
    public static final FeatureCanceller CANCELLER = new StillLifeFeatureCanceller<>(new ResourceLocation(DTStillLife.MOD_ID, "tree"), TreeConfiguration.class);

    public static final GenFeature REPLACE_ON_RADIUS = new ReplaceOnRadiusGenFeature(new ResourceLocation(DTStillLife.MOD_ID, "replace_on_radius"));
    public static final GenFeature EXTRA_FLARE = new ExtraBottomFlareGenFeature(new ResourceLocation(DTStillLife.MOD_ID, "extra_bottom_flare"));
    public static final GenFeature FOREST_SOIL = new ForestSoilGenFeature(new ResourceLocation(DTStillLife.MOD_ID, "forest_soil"));

    public static final GrowthLogicKit LONG_BRANCH_CANOPY_GROWTH_LOGIC = new LongBranchCanopyGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "long_branch_canopy"));
    public static final GrowthLogicKit UP_AND_OUT_GROWTH_LOGIC = new UpAndOutGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "up_and_out"));
    public static final GrowthLogicKit BAOBAB_GROWTH_LOGIC = new BaobabGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "baobab"));
    public static final GrowthLogicKit CYPRESS_GROWTH_LOGIC = new CypressGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "cypress"));
    public static final GrowthLogicKit REDWOOD_GROWTH_LOGIC = new RedwoodGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "redwood"));
    public static final GrowthLogicKit SMALL_REDWOOD_GROWTH_LOGIC = new SmallRedwoodGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "small_redwood"));
    public static final GrowthLogicKit WILLOW_GROWTH_LOGIC = new WillowGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "willow"));
    public static final GrowthLogicKit CANOPY_GROWTH_LOGIC = new CanopyGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "canopy"));
    public static final GrowthLogicKit JUNIPER_GROWTH_LOGIC = new JuniperGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "juniper"));
    public static final GrowthLogicKit VARIATE_HEIGHT_GROWTH_LOGIC = new VariateHeightGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "variate_height"));
    public static final GrowthLogicKit PALM_GROWTH_LOGIC = new LeaningPalmGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "palm"));
    public static final GrowthLogicKit SHRUB_GROWTH_LOGIC = new ShrubGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "shrub"));

    @SubscribeEvent
    public static void registerFeatureCanceller(final RegistryEvent<FeatureCanceller> event) {
        event.getRegistry().registerAll(CANCELLER);
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry(final RegistryEvent<GenFeature> event) {
        event.getRegistry().registerAll(REPLACE_ON_RADIUS, EXTRA_FLARE, FOREST_SOIL);
    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry(final RegistryEvent<GrowthLogicKit> event) {
        event.getRegistry().registerAll(SHRUB_GROWTH_LOGIC, PALM_GROWTH_LOGIC, VARIATE_HEIGHT_GROWTH_LOGIC, JUNIPER_GROWTH_LOGIC, LONG_BRANCH_CANOPY_GROWTH_LOGIC, UP_AND_OUT_GROWTH_LOGIC, BAOBAB_GROWTH_LOGIC, CYPRESS_GROWTH_LOGIC, REDWOOD_GROWTH_LOGIC, SMALL_REDWOOD_GROWTH_LOGIC, WILLOW_GROWTH_LOGIC, CANOPY_GROWTH_LOGIC);
    }

    @SubscribeEvent
    public static void registerSpeciesTypes(final TypeRegistryEvent<Species> event) {
        event.registerType(new ResourceLocation(DTStillLife.MOD_ID, "cypress"), GenUnderwaterSpecies.TYPE);
    }

    @SubscribeEvent
    public static void registerFamilyTypes(final TypeRegistryEvent<Family> event) {
        event.registerType(new ResourceLocation(DTStillLife.MOD_ID, "palm"), DiagonalPalmFamily.TYPE);
    }

    @SubscribeEvent
    public static void onCellKitRegistry(final RegistryEvent<CellKit> event) {
        event.getRegistry().registerAll(CellKits.PALM, CellKits.POPLAR, CellKits.DOME, CellKits.JOSHUA, CellKits.WILLOW, CellKits.SAGEBRUSH, CellKits.SPARSE);
    }
}
