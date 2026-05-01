# Create: Sophisticated Storage Compatibility

A Fabric mod that fixes crashes when Sophisticated Storage containers are placed inside Create Contraptions on Minecraft 1.20.1.

## Problem

When a Sophisticated Storage block entity (chest, barrel, etc.) is placed inside a Create Contraption, the game crashes with:

```
java.lang.ClassCastException: class com.simibubi.create.foundation.virtualWorld.VirtualChunk cannot be cast to class net.minecraft.world.chunk.WorldChunk
```

This happens because:
1. Create Contraptions use a special `VirtualChunk` class for rendering and manipulation
2. Sophisticated Storage calls `BlockEntity.setChanged()` during NBT data loading
3. `setChanged()` tries to cast the chunk to `WorldChunk`, but it's actually a `VirtualChunk`
4. The cast fails and crashes the game

## Solution

This mod implements a **Mixin** that intercepts all `BlockEntity.setChanged()` calls and:
1. Checks if the block entity is from Sophisticated Storage
2. Checks if the current chunk is a Create `VirtualChunk`
3. If both conditions are true, cancels the `setChanged()` call to prevent the crash

The fix is transparent and doesn't break any functionality — items still transfer properly between Create and Sophisticated Storage.

## Features

- Enables Sophisticated Storage chests to be moved in Create Contraptions
- Prevents the ClassCastException crash
- Automatic detection — no configuration needed

## Requirements

- Minecraft 1.20.1
- Fabric Loader ≥ 0.18.4
- Fabric API 0.92.8+1.20.1
- Create (Fabric)
- Sophisticated Storage

## Building

```
./gradlew build
```
## License

This mod is licensed under the [GNU General Public License v3.0](LICENSE).
