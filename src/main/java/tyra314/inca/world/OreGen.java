package tyra314.inca.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import tyra314.inca.IncaMod;
import tyra314.inca.block.ModBlocks;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public
class OreGen {
    public static
    ConfiguredFeature registerOreFeature(Identifier id,
                                         Block block,
                                         int size,
                                         int bottomOffset,
                                         int topOffset,
                                         int maxY,
                                         int veins
    ) {
        ConfiguredFeature feature = (ConfiguredFeature) ((ConfiguredFeature) Feature.ORE.configure(
                new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                        block.getDefaultState(),
                        size
                )).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                bottomOffset,
                topOffset,
                maxY
        ))).spreadHorizontally()).repeat(veins);

        registerFeature(id, feature);

        return feature;
    }

    public static
    void init() {
        ConfiguredFeature nautilus_ore_feature = registerOreFeature(new Identifier(
                        IncaMod.MOD_ID,
                        "nautilus_ore_feature"
                ),
                ModBlocks.NAUTILUS_ORE,
                3,
                0,
                0,
                25,
                5
        );

        for (Biome biome : BuiltinRegistries.BIOME) {
            convertImmutableFeatures(biome);
            addFeatureToBiome(biome,
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    nautilus_ore_feature
            );
        }
    }

    public static
    void registerFeature(Identifier id, ConfiguredFeature feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, feature);
    }

    public static
    void addFeatureToBiome(Biome biome,
                           GenerationStep.Feature feature,
                           ConfiguredFeature<?, ?> configuredFeature
    ) {
        convertImmutableFeatures(biome);
        List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = biome
                .getGenerationSettings().features;
        while (biomeFeatures.size() <= feature.ordinal()) {
            biomeFeatures.add(Lists.newArrayList());
        }
        biomeFeatures.get(feature.ordinal()).add(() -> configuredFeature);
    }

    private static
    void convertImmutableFeatures(Biome biome) {
        if (biome.getGenerationSettings().features instanceof ImmutableList) {
            biome.getGenerationSettings().features = biome.getGenerationSettings().features.stream()
                                                                                           .map(Lists::newArrayList)
                                                                                           .collect(
                                                                                                   Collectors
                                                                                                           .toList());
        }
    }

}
