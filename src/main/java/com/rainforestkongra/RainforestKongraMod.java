package com.rainforestkongra;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import com.rainforestkongra.entity.KongraEntity;
import com.rainforestkongra.entity.JungleJaguarEntity;
import com.rainforestkongra.entity.PoisonFrogEntity;
import com.rainforestkongra.entity.RainforestToucanEntity;
import com.rainforestkongra.item.KongraArmorMaterial;
import com.rainforestkongra.event.RainDamageHandler;

public class RainforestKongraMod implements ModInitializer {
    public static final String MOD_ID = "rainforestkongra";

    // Items
    public static final Item RAINFOREST_SCALE = new Item(new FabricItemSettings());
    public static final Item COBRA_FANG = new Item(new FabricItemSettings());
    public static final Item KONGRA_INGOT = new Item(new FabricItemSettings());

    public static final Item KONGRA_HELMET = new ArmorItem(KongraArmorMaterial.INSTANCE, ArmorItem.Type.HELMET, new FabricItemSettings());
    public static final Item KONGRA_CHESTPLATE = new ArmorItem(KongraArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings());
    public static final Item KONGRA_LEGGINGS = new ArmorItem(KongraArmorMaterial.INSTANCE, ArmorItem.Type.LEGGINGS, new FabricItemSettings());
    public static final Item KONGRA_BOOTS = new ArmorItem(KongraArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, new FabricItemSettings());

    // Entities
    public static final EntityType<KongraEntity> KONGRA = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(MOD_ID, "kongra"),
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, KongraEntity::new)
            .dimensions(EntityDimensions.fixed(1.4f, 3.2f)).build()
    );

    public static final EntityType<JungleJaguarEntity> JUNGLE_JAGUAR = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(MOD_ID, "jungle_jaguar"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, JungleJaguarEntity::new)
            .dimensions(EntityDimensions.fixed(0.9f, 0.9f)).build()
    );

    public static final EntityType<PoisonFrogEntity> POISON_FROG = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(MOD_ID, "poison_frog"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PoisonFrogEntity::new)
            .dimensions(EntityDimensions.fixed(0.5f, 0.4f)).build()
    );

    public static final EntityType<RainforestToucanEntity> RAINFOREST_TOUCAN = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(MOD_ID, "rainforest_toucan"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RainforestToucanEntity::new)
            .dimensions(EntityDimensions.fixed(0.5f, 0.7f)).build()
    );

    @Override
    public void onInitialize() {
        // Register items
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "rainforest_scale"), RAINFOREST_SCALE);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cobra_fang"), COBRA_FANG);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "kongra_ingot"), KONGRA_INGOT);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "kongra_helmet"), KONGRA_HELMET);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "kongra_chestplate"), KONGRA_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "kongra_leggings"), KONGRA_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "kongra_boots"), KONGRA_BOOTS);

        // Add to creative tabs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RAINFOREST_SCALE);
            entries.add(COBRA_FANG);
            entries.add(KONGRA_INGOT);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(KONGRA_HELMET);
            entries.add(KONGRA_CHESTPLATE);
            entries.add(KONGRA_LEGGINGS);
            entries.add(KONGRA_BOOTS);
        });

        // Register entity attributes
        FabricDefaultAttributeRegistry.register(KONGRA, KongraEntity.createKongraAttributes());
        FabricDefaultAttributeRegistry.register(JUNGLE_JAGUAR, JungleJaguarEntity.createJaguarAttributes());
        FabricDefaultAttributeRegistry.register(POISON_FROG, PoisonFrogEntity.createFrogAttributes());
        FabricDefaultAttributeRegistry.register(RAINFOREST_TOUCAN, RainforestToucanEntity.createToucanAttributes());

        // Rain damage tick handler
        RainDamageHandler handler = new RainDamageHandler();
        ServerTickEvents.END_SERVER_TICK.register(handler::onServerTick);

        System.out.println("[Rainforest Kongra] Initialized. Beware the rain, and beware KONGRA.");
    }
}