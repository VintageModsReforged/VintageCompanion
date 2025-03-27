//package dev.crossvas.vintagecompanion.items.compat.thaumcraft;
//
//import dev.crossvas.vintagecompanion.CompanionConfig;
//import dev.crossvas.vintagecompanion.items.Materials;
//import dev.crossvas.vintagecompanion.items.ToolAction;
//import dev.crossvas.vintagecompanion.items.base.ItemBaseHammer;
//import dev.crossvas.vintagecompanion.items.compat.ItemRefs;
//import mods.vintage.core.helpers.BlockHelper;
//import mods.vintage.core.helpers.ToolHelper;
//import mods.vintage.core.helpers.pos.BlockPos;
//import net.minecraft.block.Block;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.EnumRarity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.world.World;
//import net.minecraftforge.common.ForgeDirection;
//import thaumcraft.api.EnumTag;
//import thaumcraft.api.IVisRepairable;
//import thaumcraft.api.ObjectTags;
//import thaumcraft.client.lib.RenderEventHandler;
//import thaumcraft.common.Thaumcraft;
//import thaumcraft.common.aura.AuraManager;
//import thaumcraft.common.lib.ThaumcraftCraftingManager;
//import thaumcraft.common.lib.Utils;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class ItemElementalHammer extends ItemBaseHammer implements IVisRepairable {
//
//    public ItemElementalHammer() {
//        super(Materials.ELEMENTAL);
//    }
//
//    @Override
//    public EnumRarity getRarity(ItemStack itemstack) {
//        return EnumRarity.rare;
//    }
//
//    @Override
//    public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean debug) {
//        super.addInformation(stack, entityPlayer, list, debug);
//    }
//
//    @Override
//    public boolean getIsRepairable(ItemStack tool, ItemStack repair) {
//        ItemStack thaumiumStack = ItemRefs.getThaumiumStack();
//        if (thaumiumStack != null) {
//            return repair.itemID == thaumiumStack.itemID;
//        } else return super.getIsRepairable(tool, repair);
//    }
//
//    @Override
//    public void doRepair(ItemStack stack, Entity entity) {
//        if (AuraManager.decreaseClosestAura(entity.worldObj, entity.posX, entity.posY, entity.posZ, 1)) {
//            stack.damageItem(-1, (EntityLiving) entity);
//        }
//    }
//
//    @Override
//    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
//        if (!player.worldObj.isRemote && (!(entity instanceof EntityPlayer) || MinecraftServer.getServer().isPVPEnabled())) {
//            entity.setFire(2);
//        }
//        return super.onLeftClickEntity(stack, player, entity);
//    }
//
//    @Override
//    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
//        int mined = 0;
//        World world = player.worldObj;
//        Block block = BlockHelper.getBlock(world, x, y, z);
//        if (!canHarvestBlock(block))
//            return false;
//        int radius = player.isSneaking() ? 0 : 1;
//        float refStrength = block.getBlockHardness(world, x, y, z);
//        if (refStrength != 0.0D) {
//            BlockPos origin = new BlockPos(x, y, z);
//            for (BlockPos pos : ToolHelper.getAOE(player, origin, radius)) {
//                Block adjBlock = BlockHelper.getBlock(world, pos);
//                if (!BlockHelper.isAir(world, pos)) {
//                    float strength = adjBlock.getBlockHardness(world, pos.getX(), pos.getY(), pos.getZ());
//                    if (strength > 0f && strength / refStrength <= 8f) {
//                        if ((getStrVsBlock(stack, adjBlock, world.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ())) > 0.0F && canHarvestBlock(adjBlock)) && ToolAction.harvestAndDrop(world, pos.getX(), pos.getY(), pos.getZ(), player, new ToolAction.IDropCallback() {
//                            boolean dontBreak = false;
//                            boolean playPing = false;
//                            @Override
//                            public void handleDropServer(World world, Block block, int x, int y, int z, EntityPlayer player) {
//                                int fortune = EnchantmentHelper.getFortuneModifier(player);
//                                float chance = 0.275F + (float)fortune * 0.075F;
//                                int blockMetadata = world.getBlockMetadata(x, y, z);
//                                ArrayList<ItemStack> drops = block.getBlockDropped(world, x, y, z, blockMetadata, fortune);
//                                if (!drops.isEmpty()) {
//                                    for(ItemStack drop : drops) {
//                                        ItemStack dropped = drop.copy();
//                                        boolean replaced = false;
//                                        if (!drop.isItemEqual(Utils.findSpecialMiningResult(drop, 1.0F, world.rand))) {
//                                            dontBreak = true;
//                                            replaced = true;
//                                            if (!world.isRemote) {
//                                                dropped = Utils.findSpecialMiningResult(drop, chance, world.rand);
//                                            }
//                                        }
//
//                                        if (replaced && !world.isRemote) {
//                                            world.spawnEntityInWorld(new EntityItem(world, (double)x + (double)0.5F, (double)y + (double)0.5F, (double)z + (double)0.5F, dropped));
//                                            if (!drop.isItemEqual(dropped)) {
//                                                playPing = true;
//                                            }
//                                        }
//                                    }
//                                }
//                                if (dontBreak) {
//                                    world.setBlock(x, y, z, 0, 0, 3);
//                                    if (!world.isRemote && playPing) {
//                                        world.playSoundEffect((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, "random.orb", 0.2F, 0.7F + world.rand.nextFloat() * 0.2F);
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void handleDropClient(World world, Block block, int x, int y, int z, EntityPlayer player) {
//                                int id = world.getBlockId(x, y, z);
//                                int metadata = world.getBlockMetadata(x, y, z);
//                                if (dontBreak && world.isRemote) {
//                                    world.playAuxSFX(2001, x, y, z, id + (metadata << 12));
//                                }
//                            }
//                        })) {
//                            mined++;
//                        }
//                    }
//                }
//            }
//            if (mined > 0) {
//                stack.damageItem((int) (mined * CompanionConfig.durability_factor), player);
//            }
//        } else {
//            return super.onBlockStartBreak(stack, x, y, z, player);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
//        if (player.isSneaking()) {
//            stack.damageItem(5, player);
//            if (!world.isRemote) {
//                world.playSoundEffect((double)x + (double)0.5F, (double)y + (double)0.5F, (double)z + (double)0.5F, "thaumcraft.wand", 0.1F, 0.2F + world.rand.nextFloat() * 0.2F);
//                return super.onItemUse(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
//            } else {
//                ForgeDirection dir = ForgeDirection.getOrientation(side);
//                dir = dir.getOpposite();
//                int xx = 0;
//                int yy = 0;
//                int zz = 0;
//                int highest = 0;
//                EnumTag highestTag = null;
//
//                for(int a = 0; a < 8; ++a) {
//                    for(int aa = -1; aa <= 1; ++aa) {
//                        for(int bb = -1; bb <= 1; ++bb) {
//                            int mx = x + xx;
//                            int my = y + yy;
//                            int mz = z + zz;
//                            if (dir.offsetX != 0) {
//                                my += aa;
//                                mz += bb;
//                            } else if (dir.offsetY != 0) {
//                                mx += aa;
//                                mz += bb;
//                            } else if (dir.offsetZ != 0) {
//                                mx += aa;
//                                my += bb;
//                            }
//
//                            int bi = world.getBlockId(mx, my, mz);
//                            int md = world.getBlockMetadata(mx, my, mz);
//                            if (bi > 0) {
//                                ArrayList<ItemStack> ret = Block.blocksList[bi].getBlockDropped(world, mx, my, mz, md, 0);
//                                if (!ret.isEmpty()) {
//                                    for(ItemStack is : ret) {
//                                        ObjectTags ot = ThaumcraftCraftingManager.getObjectTags(is);
//                                        if (ot != null && ot.size() > 0) {
//                                            for(EnumTag tag : ot.getAspects()) {
//                                                if (ot.getAmount(tag) > highest) {
//                                                    highest = ot.getAmount(tag);
//                                                    highestTag = tag;
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    xx += dir.offsetX;
//                    yy += dir.offsetY;
//                    zz += dir.offsetZ;
//                }
//
//                Thaumcraft.proxy.blockSparkle(world, x, y, z, 4, 4);
//                if (highestTag != null) {
//                    RenderEventHandler.tagscale = 0.0F;
//                    RenderEventHandler.blockTags = Arrays.asList(x, y, z, (new ObjectTags()).add(highestTag, highest), side);
//                }
//
//                player.swingItem();
//                return false;
//            }
//        } else return super.onItemUse(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
//    }
//}
