# Carpet SSL Addition

A Fabric mod that adds a small set of Carpet rules for End gateways and dolphin item pickup.

Current version: **2.1.0**

## Features

- **End gateway chunk-ticket control**: Skip the post-teleport chunk ticket for all gateways or gateways with a bone block underneath.
- **End gateway custom landing**: On a return trip from an outer-island gateway, detect an emerald block below the target gateway and land on the first safe adjacent block. If no safe adjacent position exists, vanilla landing behavior is used.
- **Dolphin pickup interception**: Restore 1.21.8-style item throwing and prevent dolphins from equipping picked-up items. Regular dolphins are limited to 5 throws per 40 ticks; dolphins named `fast` are limited to 10 throws per 20 ticks. Rate-limited items receive a 40-tick pickup delay.

## Requirements

- Minecraft 26.1.2
- Fabric Loader 0.18.4 or newer
- Fabric Carpet Mod compatible with Minecraft 26.1.2
- Java 25 or newer

## Usage

All rules use the Carpet format: `/carpet <rule> <value>`.

```text
/carpet endGatewayDoNotAddLoadTicket [bone_block|all|OFF]
/carpet endGatewayCustomLanding [true|false]
/carpet dolphinPickupIntercept [true|false]
```

With `endGatewayCustomLanding` enabled, the landing search checks east, west, south, then north of the emerald marker. A candidate needs a solid supporting block and enough collision-free space for the arriving entity.

## Building

```bash
./gradlew build
```

## License

LGPL-v3

## Credits

Based on and compatible with [Carpet Mod](https://github.com/gnembon/fabric-carpet) by gnembon.

Author: IceCron — https://github.com/IceeCron
