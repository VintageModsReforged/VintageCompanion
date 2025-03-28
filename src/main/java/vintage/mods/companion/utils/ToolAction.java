package vintage.mods.companion.utils;

import mods.vintage.core.helpers.ToolHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class ToolAction {

    public static ToolHelper.IDropCallback smelt() {
        return new ToolHelper.IDropCallback() {
            @Override
            public void handleServer(World world, Block block, int x, int y, int z, int metadata, EntityPlayer player) {
                boolean dontBreak = false;
                for (ItemStack input : block.getBlockDropped(world, x, y, z, metadata, 0)) {
                    ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(input);
                    if (result != null) {
                        if (!world.isRemote) {
                            float floatXP = FurnaceRecipes.smelting().getExperience(result);
                            int smeltXP = (int) floatXP;
                            if (floatXP > (float) smeltXP && world.rand.nextFloat() < floatXP - (float) smeltXP) {
                                ++smeltXP;
                            }

                            while (smeltXP > 0) {
                                int splitXP = EntityXPOrb.getXPSplit(smeltXP);
                                smeltXP -= splitXP;
                                world.spawnEntityInWorld(new EntityXPOrb(world, (double) x + (double) 0.5F, (double) y + (double) 0.5F, (double) z + (double) 0.5F, splitXP));
                            }
                            world.spawnEntityInWorld(new EntityItem(world, (double) x + (double) 0.5F, (double) y + (double) 0.5F, (double) z + (double) 0.5F, result.copy()));
                        }
                    } else {
                        dontBreak = true;
                        break;
                    }
                }
                if (dontBreak) block.harvestBlock(world, player, x, y, z, metadata);
            }

            @Override
            public void handleClient(World world, Block block, int x, int y, int z, int metadata, EntityPlayer player) {
                for (int i = 0; i < 5; ++i) {
                    double rx = world.rand.nextGaussian() * 0.02;
                    double ry = world.rand.nextGaussian() * 0.02;
                    double rz = world.rand.nextGaussian() * 0.02;
                    double magnitude = 20.0;
                    world.spawnParticle("flame", (double) x + (double) 0.5F + rx * magnitude, (double) y + (double) 0.5F + ry * magnitude, (double) z + (double) 0.5F + rz * magnitude, -rx, -ry, -rz);
                }
            }
        };
    }
}
