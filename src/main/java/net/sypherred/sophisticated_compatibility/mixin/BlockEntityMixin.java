package net.sypherred.sophisticated_compatibility.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Prevents ClassCastException when Sophisticated Storage tries to mark dirty in a Create Virtual Chunk
 * This Mixin targets BlockEntity.markDirty() and cancels it for Sophisticated Storage in Virtual Chunks.
 */
@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin {

    /**
     * Intercepts BlockEntity.markDirty() calls and cancels them if in a Virtual Chunk.
     * This prevents: ClassCastException: VirtualChunk cannot be cast to WorldChunk
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void onMarkDirty(CallbackInfo ci) {
        try {
            BlockEntity blockEntity = (BlockEntity) (Object) this;

            // Check if this is a Sophisticated Storage block entity
            String className = blockEntity.getClass().getName();
            if (!className.contains("net.p3pp3rf1y.sophisticatedstorage")) {
                return;
            }

            // Check if we're in a Virtual Chunk by looking at the chunk class name
            if (blockEntity.getLevel() != null) {
                net.minecraft.world.level.chunk.ChunkAccess chunk = blockEntity.getLevel().getChunk(blockEntity.getBlockPos());

                if (chunk != null) {
                    String chunkClassName = chunk.getClass().getName();
                    // If we're in a Virtual Chunk, cancel the markDirty
                    if (chunkClassName.contains("VirtualChunk")) {
                        ci.cancel();
                    }
                }
            }
        } catch (Exception e) {
            // Silently continue on any errors - this is a safety net
        }
    }
}
