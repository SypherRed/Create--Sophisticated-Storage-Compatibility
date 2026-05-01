package net.sypherred.sophisticated_compatibility.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.level.block.entity.BlockEntity;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin {

    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void onMarkDirty(CallbackInfo ci) {
        try {
            BlockEntity blockEntity = (BlockEntity) (Object) this;

            String className = blockEntity.getClass().getName();
            if (!className.contains("net.p3pp3rf1y.sophisticatedstorage")) {
                return;
            }

            if (blockEntity.getLevel() != null) {
                net.minecraft.world.level.chunk.ChunkAccess chunk = blockEntity.getLevel().getChunk(blockEntity.getBlockPos());

                if (chunk != null && chunk.getClass().getName().contains("VirtualChunk")) {
                    ci.cancel();
                }
            }
        } catch (Exception e) {
            // intentionally ignored — safety net for unexpected runtime states
        }
    }
}
