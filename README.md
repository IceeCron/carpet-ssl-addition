# carpet-ssl-addition

A Fabric mod that adds Carpet rules to adjust and fix certain Minecraft behaviors.

## Release v1.2.0

Release date: 2026-07-26

Highlights:

- Renamed project to `carpet-ssl-addition` and new author `IceCron`.
- Mod ID changed to `carpet-ssl-addition`, version `1.2.0`.
- Updated resource paths and translations; improved README and packaging.
- Bug fixes and improvements: piston deletion fix, end gateway load ticket handling, multiple chunk-loading features and translations.

## Changelog (since previous release)

- Refactor: project and package metadata updated (mod id, archives_base_name).
- Fix: prevent extended pistons from deleting front blocks.
- Fix: optional suppression of end gateway load tickets by config.
- Feature: note block / piston / ender pearl chunk loaders improved.
- Docs: README cleanup, clearer usage examples and build instructions.

## Upgrade Notes

- If upgrading from older releases, remove the old `fmca` mod from your `mods/` folder and replace with the new `carpet-ssl-addition` jar.
- Config keys and rule names remain the same; categories now use the `carpet_ssl_addition_*` prefix internally.

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

### Bug Fixes

- **Fix Extended Piston Delete Front Block**: Fixes bug where extended pistons can delete any front block
- **End Gateway No Load Ticket**: Prevents load tickets when passing through end gates

## Requirements

- Minecraft: 1.21.8
- Fabric Loader: 0.18.4+
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
