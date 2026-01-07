package net.amerigolem.tutorial_mod.item;

import net.amerigolem.tutorial_mod.TutorialMod;
import net.amerigolem.tutorial_mod.block.custom.HammerItem;
import net.amerigolem.tutorial_mod.item.custom.ChiselItem;
import net.amerigolem.tutorial_mod.item.custom.DashWand;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;


public class ModItems {

    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));
    public static final Item DASH_WAND = registerItem("dash_wand", new DashWand(new Item.Settings().maxDamage(32)));

    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.tutorialmod.cauliflower.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item PINK_GARNET_SWORD = registerItem("pink_garnet_sword",
            new SwordItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 3, -2.4f))));
    public static final Item PINK_GARNET_PICKAXE = registerItem("pink_garnet_pickaxe",
            new PickaxeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 3, -2.4f))));
    public static final Item PINK_GARNET_AXE = registerItem("pink_garnet_axe",
            new AxeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 6, -3.2f))));
    public static final Item PINK_GARNET_SHOVEL = registerItem("pink_garnet_shovel",
            new ShovelItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 1.5f, -3f))));
    public static final Item PINK_GARNET_HOE = registerItem("pink_garnet_hoe",
            new HoeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 0, -3f))));


    public static final Item PINK_GARNET_HAMMER = registerItem("pink_garnet_hammer",
            new HammerItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 7, -3.4f))));

    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.PINK_GARNET_GROUP_KEY).register( entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
            entries.add(CHISEL);
            entries.add(DASH_WAND);
            entries.add(CAULIFLOWER);
            entries.add(STARLIGHT_ASHES);
            entries.add(PINK_GARNET_SWORD);
            entries.add(PINK_GARNET_PICKAXE);
            entries.add(PINK_GARNET_AXE);
            entries.add(PINK_GARNET_SHOVEL);
            entries.add(PINK_GARNET_HOE);
            entries.add(PINK_GARNET_HAMMER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register( entries -> {
            entries.add(CAULIFLOWER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register( entries -> {
            entries.add(PINK_GARNET_SWORD);
            entries.add(PINK_GARNET_AXE);
        });
    }
}
