package net.amerigolem.tutorial_mod;

import net.amerigolem.tutorial_mod.block.ModBlocks;
import net.amerigolem.tutorial_mod.component.ModDataComponentTypes;
import net.amerigolem.tutorial_mod.item.ModItemGroups;
import net.amerigolem.tutorial_mod.item.ModItems;
import net.amerigolem.tutorial_mod.util.DashLandingTracker;
import net.amerigolem.tutorial_mod.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		/**
		 * If working with other mods, then I might need to check out mixins
		 */

		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {

			if (!(entity instanceof PlayerEntity player)) {
				return true;
			}

			if (!source.isOf(DamageTypes.FALL)) {
				return true;
			}

			double effectiveFall = DashLandingTracker.getEffectiveFallDistance(player);

			if (effectiveFall >= 0) {
				// Vanilla-like fall damage formula
				float newDamage = (float) Math.max(0, effectiveFall - 3.0);

				if (newDamage <= 0) {
					return false; // cancel entirely
				}

				// Apply reduced damage manually
				player.damage(source, newDamage);
				return false; // cancel original damage
			}

			return true;
		});


		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 2400);


		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
	}
}