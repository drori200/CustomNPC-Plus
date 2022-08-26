package noppes.npcs.entity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityNPCFlying extends EntityNPCInterface {

	public EntityNPCFlying(World world) {
		super(world);
	}

	@Override
	public boolean canFly(){
		return ai.movementType == 1;
	}

    @Override
    public void fall(float distance) {
    	if(!canFly())
    		super.fall(distance);
    }

    @Override
    protected void updateFallState(double p_180433_1_, boolean p_180433_3_) {
    	if(!canFly())
    		super.updateFallState(p_180433_1_, p_180433_3_);
    }

    @Override
    public boolean isOnLadder(){
        return false;
    }

    @Override
    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
    {
        if(!this.canFly() || this.hurtTime != 0) {
            super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
            return;
        }

        if(isInWater()) {
            moveFlying(p_70612_1_, p_70612_2_, 0.02F);
            moveEntity(motionX, motionY, motionZ);
            motionX *= 0.800000011920929D;
            motionY *= 0.800000011920929D;
            motionZ *= 0.800000011920929D;
        } else if(handleLavaMovement()) {
            moveFlying(p_70612_1_, p_70612_2_, 0.02F);
            moveEntity(motionX, motionY, motionZ);
            motionX *= 0.5D;
            motionY *= 0.5D;
            motionZ *= 0.5D;
        } else {
            float f2 = 0.91F;

            if (onGround)  {
                f2 = 0.54600006F;
                Block block = worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));
                f2 = block.slipperiness * 0.91F;
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            moveFlying(p_70612_1_, p_70612_2_, onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;

            if (onGround) {
                f2 = 0.54600006F;
                Block block = worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));
                f2 = block.slipperiness * 0.91F;
            }

            moveEntity(motionX, motionY, motionZ);
            motionX *= f2;
            motionY *= f2;
            motionZ *= f2;
        }

        prevLimbSwingAmount = limbSwingAmount;
        double d0 = posX - prevPosX;
        double d1 = posZ - prevPosZ;
        float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

        if(f4 > 1.0F)
            f4 = 1.0F;

        limbSwingAmount += (f4 - limbSwingAmount) * 0.4F;
        limbSwing += limbSwingAmount;

        this.velocityChanged = true;
    }

    public void moveEntityWithHeadingOld(float p_70612_1_, float p_70612_2_)
    {

        double heightOffGround = 0;
        if(this.ai.hasFlyLimit) {
            for (int blockY = (int) this.posY; blockY > 0; blockY--) {
                heightOffGround = this.posY - blockY;
                if (this.worldObj.getBlock((int) this.posX, blockY, (int) this.posZ) != Blocks.air || heightOffGround > this.ai.flyHeightLimit){
                    break;
                }
            }
        }

        if (heightOffGround > this.ai.flyHeightLimit && this.ai.hasFlyLimit) {
            super.moveEntityWithHeading(p_70612_1_,p_70612_2_);
            return;
        }

        double d3 = this.motionY;
        super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
        this.motionY = d3;

        if(this.getNavigator().noPath())
            this.motionY = 0.0D;

        this.velocityChanged = true;
    }
}
