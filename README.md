# Create: Sophisticated Storage Compatibility

This mod provides compatibility between the Create mod and Sophisticated Storage mod for Minecraft 1.20.1 on Fabric.

## Problem

When a Sophisticated Storage block entity (chest, barrel, etc.) is placed inside a Create Contraption, the game crashes with:

```
java.lang.ClassCastException: class com.simibubi.create.foundation.virtualWorld.VirtualChunk cannot be cast to class net.minecraft.world.chunk.WorldChunk
```

This happens because:
1. Create Contraptions use a special `VirtualChunk` class for rendering and manipulation
2. Sophisticated Storage calls `BlockEntity.markDirty()` during NBT data loading
3. `markDirty()` tries to cast the chunk to `WorldChunk`, but it's actually a `VirtualChunk`
4. The cast fails and crashes the game

## Solution

This mod implements a **Mixin** that intercepts all `BlockEntity.markDirty()` calls and:
1. Checks if the block entity is from Sophisticated Storage
2. Checks if the current chunk is a Create `VirtualChunk`
3. If both conditions are true, cancels the `markDirty()` call to prevent the crash

The fix is transparent and doesn't break any functionality - items still transfer properly between Create and Sophisticated Storage.

## Features

- Enables Sophisticated Storage chests to be moved in Create Contraptions
- Prevents the ClassCastException crash
- Automatic detection - no configuration needed
- Logs when compatibility is active

## Setup

For setup instructions, please see the [Fabric Documentation page](https://docs.fabricmc.net/develop/getting-started/creating-a-project#setting-up) related to the IDE that you are using.

## Building

Run `./gradlew build` to build the mod. The output JAR will be in `build/libs/`.

## Requirements

- Minecraft 1.20.1
- Fabric Loader 0.18.4
- Fabric API 0.92.8+1.20.1
- Create (Fabric version)
- Sophisticated Storage

## License

This mod is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.
