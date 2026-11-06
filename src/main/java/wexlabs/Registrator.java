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
import wexlabs.dtstilllife.trees.growthlogic.*;
import wexlabs.dtstilllife.trees.species.DiagonalPalmFamily;
import wexlabs.dtstilllife.trees.species.GenUnderwaterSpecies;

@EventBusSubscriber(modid = DTStillLife.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Registrator {
    
    // Déclarer les objets, mais NE PAS les instancier de manière statique tout de suite
    public static FeatureCanceller CANCELLER;

    public static GenFeature REPLACE_ON_RADIUS;
    public static GenFeature EXTRA_FLARE;
    public static GenFeature FOREST_SOIL;

    public static GrowthLogicKit LONG_BRANCH_CANOPY_GROWTH_LOGIC;
    public static GrowthLogicKit UP_AND_OUT_GROWTH_LOGIC;
    public static GrowthLogicKit BAOBAB_GROWTH_LOGIC;
    public static GrowthLogicKit CYPRESS_GROWTH_LOGIC;
    public static GrowthLogicKit REDWOOD_GROWTH_LOGIC;
    public static GrowthLogicKit SMALL_REDWOOD_GROWTH_LOGIC;
    public static GrowthLogicKit WILLOW_GROWTH_LOGIC;
    public static GrowthLogicKit CANOPY_GROWTH_LOGIC;
    public static GrowthLogicKit JUNIPER_GROWTH_LOGIC;
    public static GrowthLogicKit VARIATE_HEIGHT_GROWTH_LOGIC;
    public static GrowthLogicKit PALM_GROWTH_LOGIC;
    public static GrowthLogicKit SHRUB_GROWTH_LOGIC;

    @SubscribeEvent
    public static void registerFeatureCanceller(final RegistryEvent<FeatureCanceller> event) {
        // Instanciation sécurisée au moment précis où l'événement s'exécute
        CANCELLER = new StillLifeFeatureCanceller<>(new ResourceLocation(DTStillLife.MOD_ID, "tree"), TreeConfiguration.class);
        event.getRegistry().registerAll(CANCELLER);
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry(final RegistryEvent<GenFeature> event) {
        // Instanciation au moment du registre
        REPLACE_ON_RADIUS = new ReplaceOnRadiusGenFeature(new ResourceLocation(DTStillLife.MOD_ID, "replace_on_radius"));
        EXTRA_FLARE = new ExtraBottomFlareGenFeature(new ResourceLocation(DTStillLife.MOD_ID, "extra_bottom_flare"));
        FOREST_SOIL = new ForestSoilGenFeature(new ResourceLocation(DTStillLife.MOD_ID, "forest_soil"));

        event.getRegistry().registerAll(REPLACE_ON_RADIUS, EXTRA_FLARE, FOREST_SOIL);
    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry(final RegistryEvent<GrowthLogicKit> event) {
        // Instanciation au moment du registre
        LONG_BRANCH_CANOPY_GROWTH_LOGIC = new LongBranchCanopyGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "long_branch_canopy"));
        UP_AND_OUT_GROWTH_LOGIC = new UpAndOutGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "up_and_out"));
        BAOBAB_GROWTH_LOGIC = new BaobabGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "baobab"));
        CYPRESS_GROWTH_LOGIC = new CypressGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "cypress"));
        REDWOOD_GROWTH_LOGIC = new RedwoodGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "redwood"));
        SMALL_REDWOOD_GROWTH_LOGIC = new SmallRedwoodGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "small_redwood"));
        WILLOW_GROWTH_LOGIC = new WillowGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "willow"));
        CANOPY_GROWTH_LOGIC = new CanopyGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "canopy"));
        JUNIPER_GROWTH_LOGIC = new JuniperGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "juniper"));
        VARIATE_HEIGHT_GROWTH_LOGIC = new VariateHeightGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "variate_height"));
        PALM_GROWTH_LOGIC = new LeaningPalmGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "palm"));
        SHRUB_GROWTH_LOGIC = new ShrubGrowthLogic(new ResourceLocation(DTStillLife.MOD_ID, "shrub"));

        event.getRegistry().registerAll(
            SHRUB_GROWTH_LOGIC, PALM_GROWTH_LOGIC, VARIATE_HEIGHT_GROWTH_LOGIC, JUNIPER_GROWTH_LOGIC, 
            LONG_BRANCH_CANOPY_GROWTH_LOGIC, UP_AND_OUT_GROWTH_LOGIC, BAOBAB_GROWTH_LOGIC, 
            CYPRESS_GROWTH_LOGIC, REDWOOD_GROWTH_LOGIC, SMALL_REDWOOD_GROWTH_LOGIC, 
            WILLOW_GROWTH_LOGIC, CANOPY_GROWTH_LOGIC
        );
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
        event.getRegistry().registerAll(
            CellKits.PALM, CellKits.POPLAR, CellKits.DOME, 
            CellKits.JOSHUA, CellKits.WILLOW, CellKits.SAGEBRUSH, CellKits.SPARSE
        );
    }
}
