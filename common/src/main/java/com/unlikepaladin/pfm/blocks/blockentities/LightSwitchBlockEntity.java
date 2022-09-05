package com.unlikepaladin.pfm.blocks.blockentities;

import com.unlikepaladin.pfm.blocks.PowerableBlock;
import com.unlikepaladin.pfm.registry.BlockEntities;
import com.unlikepaladin.pfm.registry.BlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtLong;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class LightSwitchBlockEntity extends BlockEntity {
    private final List<BlockPos> lights;
    public LightSwitchBlockEntity() {
        super(BlockEntities.LIGHT_SWITCH_BLOCK_ENTITY);
        lights = DefaultedList.of();
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtList tagList = new NbtList();
        lights.forEach(blockPos -> tagList.add(NbtLong.of(blockPos.asLong())));
        nbt.put("lights", tagList);
        return nbt;
    }

    @Override
    public void fromTag(BlockState state, NbtCompound nbt) {
        super.fromTag(state, nbt);
        if(nbt.contains("lights")){
            lights.clear();
            //TODO: Find right number here
            NbtList lightTagList = nbt.getList("lights", 3);
            lightTagList.forEach(nbtElement -> addLight(((NbtLong)nbtElement).longValue()));
        }
    }

    public void addLight(long pos)
    {
        BlockPos lightPos = BlockPos.fromLong(pos);
        if(!lights.contains(lightPos))
        {
            lights.add(lightPos);
        }
    }
    public void isLightValid(){

    }
    public void setState(boolean powered)
    {
        if(!lights.isEmpty()) {
            lights.removeIf(lightPos ->
            {
                BlockState state = world.getBlockState(lightPos);
                return !(state.getBlock() instanceof PowerableBlock);
            });
            lights.forEach(lightPos ->
            {
                BlockState state = world.getBlockState(lightPos);
                ((PowerableBlock) state.getBlock()).setPowered(world, lightPos, powered);

            });

        }
    }

}
