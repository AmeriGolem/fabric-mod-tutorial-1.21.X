package net.amerigolem.tutorial_mod.util;
import net.minecraft.entity.player.PlayerEntity;

import java.util.WeakHashMap;

public class DashLandingTracker {

    private static class DashData {
        final double startY;
        boolean active = true;

        DashData(double startY) {
            this.startY = startY;
        }
    }

    private static final WeakHashMap<PlayerEntity, DashData> DASHES = new WeakHashMap<>();

    public static void startDash(PlayerEntity player) {
        DASHES.put(player, new DashData(player.getY()));
    }

    /**
     * @return effective fall distance override, or -1 if not applicable
     */
    public static double getEffectiveFallDistance(PlayerEntity player) {
        DashData data = DASHES.get(player);
        if (data == null || !data.active) return -1;

        data.active = false;

        double landingY = player.getY();
        double effectiveFall = data.startY - landingY;

        // Only care if player ended lower than dash start
        return Math.max(0.0, effectiveFall);
    }
}