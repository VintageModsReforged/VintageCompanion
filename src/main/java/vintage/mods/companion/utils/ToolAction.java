package vintage.mods.companion.utils;

import mods.vintage.core.helpers.StackHelper;
import mods.vintage.core.utils.IHarvestCallback;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class ToolAction {

    public static IHarvestCallback smelt() {
        return new IHarvestCallback() {
            @Override
            public HarvestMode getMode(World world, Block block, int x, int y, int z, int meta, EntityPlayer player) {
                return HarvestMode.SMELT;
            }

            @Override
            public void handleCustom(World world, Block block, int x, int y, int z, int meta, EntityPlayer player) {
                int fortune = EnchantmentHelper.getFortuneModifier(player);
                boolean silk = EnchantmentHelper.getSilkTouchModifier(player);

                // Silk Touch fallback
                if (silk) {
                    block.harvestBlock(world, player, x, y, z, meta);
                    return;
                }

                boolean droppedSomething = false;

                for (ItemStack input : block.getBlockDropped(world, x, y, z, meta, fortune)) {
                    ItemStack smelted = FurnaceRecipes.smelting().getSmeltingResult(input);

                    if (smelted == null) {
                        // fallback to vanilla
                        block.harvestBlock(world, player, x, y, z, meta);
                        return;
                    }

                    droppedSomething = true;

                    // spawn the smelted item
                    StackHelper.dropAsEntity(world, x, y, z, smelted.copy());

                    // spawn furnace XP
                    float xpFloat = FurnaceRecipes.smelting().getExperience(smelted);
                    int xpInt = (int) xpFloat;
                    if (xpFloat > xpInt && world.rand.nextFloat() < xpFloat - xpInt) {
                        xpInt++;
                    }

                    while (xpInt > 0) {
                        int split = EntityXPOrb.getXPSplit(xpInt);
                        xpInt -= split;
                        world.spawnEntityInWorld(new EntityXPOrb(world, x + 0.5, y + 0.5, z + 0.5, split));
                    }
                }

                // fallback if nothing smelted
                if (!droppedSomething) {
                    block.harvestBlock(world, player, x, y, z, meta);
                } else {
                    if (world.rand.nextFloat() < 0.3F) {
                        world.playAuxSFX(2004, x, y, z, 0); // furnace flame
                    }
                }
            }
        };
    }
}
