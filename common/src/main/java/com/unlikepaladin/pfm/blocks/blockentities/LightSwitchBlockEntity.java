package com.unlikepaladin.pfm.blocks.blockentities;

import com.unlikepaladin.pfm.blocks.PowerableBlock;
import com.unlikepaladin.pfm.registry.BlockEntities;
import com.unlikepaladin.pfm.registry.BlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
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
        System.out.println("Lights in write:" + lights);
        nbt.put("lights", tagList);
        return nbt;
    }

    @Override
    public void fromTag(BlockState state, NbtCompound nbt) {
        super.fromTag(state, nbt);
        if(nbt.contains("lights", 9)){
            this.lights.clear();
            System.out.println("lights in read tag: " + nbt.get("lights"));
            NbtList lightTagList = nbt.getList("lights", 12);
            System.out.println("lights in read: " + lightTagList);
            lightTagList.forEach(nbtElement -> {addLight(((NbtLong)nbtElement).longValue());
                System.out.println("x:" + nbtElement);
            });
        }
    }
    public void addLight(long pos)
    {
        BlockPos lightPos = BlockPos.fromLong(pos);
        System.out.println("Adding light:" + lightPos);
        if(!this.lights.contains(lightPos))
        {
            this.lights.add(lightPos);
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