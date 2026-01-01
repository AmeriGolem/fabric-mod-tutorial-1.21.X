package net.amerigolem.tutorial_mod.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DashWand extends Item {

    private static final float VELOCITY = 2F;
    private static final int COOLDOWN = 100;

    public DashWand(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            Vec3d lookVector = user.getRotationVector();
            user.addVelocity(lookVector.multiply(VELOCITY));
            user.velocityModified = true; // IMPORTANT

            stack.damage(1, user, EquipmentSlot.MAINHAND);

            user.getItemCooldownManager().set(this, COOLDOWN); // 20 ticks = 1 second
        }

        return TypedActionResult.success(stack, world.isClient);
    }
}
