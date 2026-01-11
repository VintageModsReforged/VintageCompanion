package vintage.mods.companion.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.vintage.core.helpers.BlockHelper;
import mods.vintage.core.helpers.ToolHelper;
import mods.vintage.core.helpers.pos.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;
import vintage.mods.companion.items.base.ItemBaseAOETool;

import java.util.List;

@SideOnly(Side.CLIENT)
public class AOERenderHandler {

    private static final double FACE_OFFSET = 0.002D;

    private final Minecraft mc = Minecraft.getMinecraft();

    @ForgeSubscribe
    public void onRenderWorldLast(RenderWorldLastEvent e) {
        if (mc.theWorld == null || mc.thePlayer == null)
            return;

        ItemStack held = mc.thePlayer.getCurrentEquippedItem();
        if (held == null || !(held.getItem() instanceof ItemBaseAOETool))
            return;

        MovingObjectPosition mop = mc.thePlayer.rayTrace(5.0D, e.partialTicks);
        if (mop == null || mop.typeOfHit != EnumMovingObjectType.TILE)
            return;

        EnumFacing face = EnumFacing.getFront(mop.sideHit);
        if (face == null)
            return;

        BlockPos origin = new BlockPos(mop.blockX, mop.blockY, mop.blockZ);
        List<BlockPos> aoe = ToolHelper.getAOE(
                mc.thePlayer,
                origin,
                mc.thePlayer.isSneaking() ? 0 : 1
        );

        if (aoe.isEmpty())
            return;

        double px = RenderManager.renderPosX;
        double py = RenderManager.renderPosY;
        double pz = RenderManager.renderPosZ;

        GL11.glPushMatrix();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_CULL_FACE);

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.setColorRGBA_F(0.7F, 0.7F, 0.7F, 0.2F);

        for (BlockPos pos : aoe) {
            if (mc.theWorld.isAirBlock(pos.getX(), pos.getY(), pos.getZ()) || !((ItemBaseAOETool) held.getItem()).canHarvest(BlockHelper.getBlock(mc.theWorld, pos)))
                continue;
            drawFace(tess, pos.getX() - px, pos.getY() - py, pos.getZ() - pz, face);
        }

        tess.draw();

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }

    public static void drawFace(Tessellator tess, double x, double y, double z, EnumFacing face) {
        double minX = x;
        double minY = y;
        double minZ = z;
        double maxX = x + 1;
        double maxY = y + 1;
        double maxZ = z + 1;

        Vec3[] verts;

        switch (face) {
            case UP:
                verts = new Vec3[] {
                        Vec3.createVectorHelper(minX, maxY, minZ),
                        Vec3.createVectorHelper(maxX, maxY, minZ),
                        Vec3.createVectorHelper(maxX, maxY, maxZ),
                        Vec3.createVectorHelper(minX, maxY, maxZ)
                };
                break;

            case DOWN:
                verts = new Vec3[] {
                        Vec3.createVectorHelper(minX, minY, maxZ),
                        Vec3.createVectorHelper(maxX, minY, maxZ),
                        Vec3.createVectorHelper(maxX, minY, minZ),
                        Vec3.createVectorHelper(minX, minY, minZ)
                };
                break;

            case NORTH:
                verts = new Vec3[] {
                        Vec3.createVectorHelper(minX, minY, minZ),
                        Vec3.createVectorHelper(maxX, minY, minZ),
                        Vec3.createVectorHelper(maxX, maxY, minZ),
                        Vec3.createVectorHelper(minX, maxY, minZ)
                };
                break;

            case SOUTH:
                verts = new Vec3[] {
                        Vec3.createVectorHelper(minX, maxY, maxZ),
                        Vec3.createVectorHelper(maxX, maxY, maxZ),
                        Vec3.createVectorHelper(maxX, minY, maxZ),
                        Vec3.createVectorHelper(minX, minY, maxZ)
                };
                break;

            case WEST:
                verts = new Vec3[] {
                        Vec3.createVectorHelper(maxX, minY, minZ),
                        Vec3.createVectorHelper(maxX, minY, maxZ),
                        Vec3.createVectorHelper(maxX, maxY, maxZ),
                        Vec3.createVectorHelper(maxX, maxY, minZ)
                };
                break;

            case EAST:
                verts = new Vec3[] {
                        Vec3.createVectorHelper(minX, minY, maxZ),
                        Vec3.createVectorHelper(minX, minY, minZ),
                        Vec3.createVectorHelper(minX, maxY, minZ),
                        Vec3.createVectorHelper(minX, maxY, maxZ)
                };
                break;

            default:
                return;
        }

        for (Vec3 v : verts) {
            v.xCoord += face.getFrontOffsetX() * FACE_OFFSET;
            v.yCoord += face.getFrontOffsetY() * FACE_OFFSET;
            v.zCoord += face.getFrontOffsetZ() * FACE_OFFSET;
            tess.addVertex(v.xCoord, v.yCoord, v.zCoord);
        }
    }
}
