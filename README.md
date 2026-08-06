# carpet-ssl-addition

A Fabric mod that adds Carpet rules to adjust and fix certain Minecraft behaviors.

## Release v1.3.0

Release date: 2026-08-6

Highlights:

- Added Dolphin pickup interception to restore 1.21.8-style behavior in 26.1.x, preventing item equip animation lock and enabling controlled dolphin item throwing.
- Added rate limiting for dolphin throws to prevent excessive instant item launches.
- Updated resource paths and translations; improved README and packaging.

## Changelog (since previous release)

- Feature: dolphin pickup interception and throw rate limiting.

## Contact & Source

- GitHub: https://github.com/IceeCron/carpet-ssl-addition
- Author: IceCron — https://github.com/IceeCron

## Features

### Chunk Loading

- **Note Block Chunk Loader**: Load 3x3 chunks when a note block is triggered
- **Piston Chunk Loader**: Load chunks when pistons are activated
- **Ender Pearl Chunk Loader**: Ender pearls load chunks during flight

### Game Modifications

- **Soft Deepslate**: Makes deepslate as easy to mine as stone
- **Soft Obsidian**: Makes obsidian as easy to mine as end stone
- **Scheduled Random Tick Cactus**: Cacti accept scheduled ticks as random ticks
- **dolphin pickup interception and throw rate limiting.**

## Requirements

- Minecraft: 26.1.2
- Fabric Loader: 0.19.3+
- Carpet Mod: compatible versions

## Installation

1. Install Fabric Loader
2. Install Carpet Mod
3. Place this mod in your mods folder

## Usage

All commands use the Carpet format: `/carpet <rule> <value>`

### Chunk Loading Rules

```
/carpet noteBlockChunkLoader [bone_block|wither_skeleton_skull|note_block|OFF]
/carpet pistonBlockChunkLoader [bone_block|bedrock|all|OFF]
/carpet enderPearlChunkLoader [true|false]
```

### Modification Rules

```
/carpet softDeepslate [true|false]
/carpet softObsidian [true|false]
/carpet scheduledRandomTickCactus [true|false]
/carpet dolphinPickupIntercept [true|false]
```

### Dolphin Rate Limit Rules

```
/carpet dolphinThrowWindowTicks [integer]
/carpet dolphinThrowMaxPerWindow [integer]
/carpet dolphinThrowPenaltyPickupDelay [integer]
```

### Fix Rules

```
/carpet fixExtendedPistonDeleteFrontBlock [true|false]
/carpet endGatewayDoNotAddLoadTicket [bone_block|all|OFF]
```

## Building

```bash
./gradlew build
```

## License

LGPL-v3

## Credits

Based on and compatible with [Carpet Mod](https://github.com/gnembon/fabric-carpet) by gnembon.

Author: IceCron — https://github.com/IceeCron
