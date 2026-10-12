# 地毯SSL扩展

一款Fabric模组，为末地传送门和海豚物品拾取添加了一小部分地毯规则。

当前版本：**2.1.1**

## 特性

- **末地传送门区块票证控制**：跳过所有传送门或下方有骨块的传送门在传送后的区块票证处理。  
- **末地传送门自定义着陆**：从外岛传送门返回时，若目标传送门下方存在绿宝石方块，则在首个安全的相邻方块上着陆；若无安全的相邻位置，则采用原版着陆行为。  
- **海豚拾取拦截**：恢复1.21.8版本风格的物品投掷机制，并防止海豚装备拾取的物品。普通海豚每40个游戏刻最多投掷5次；名为“fast”的海豚每20个游戏刻最多投掷10次。受速率限制的物品将延迟40个游戏刻才能被拾取。

## 要求

- Minecraft 26.1.2  
- Fabric Loader 0.18.4 或更新版本  
- 与 Minecraft 26.1.2 兼容的 Fabric Carpet Mod  
- Java 25 或更新版本  

## 使用方法  

所有规则均采用 Carpet 格式：`/carpet <规则> <值>`。  

```text
/carpet endGatewayDoNotAddLoadTicket [bone_block|all|OFF]  
/carpet endGatewayCustomLanding [true|false]  
/carpet dolphinPickupIntercept [true|false]  
```
```

启用 `endGatewayCustomLanding` 后，着陆搜索会依次检查翡翠标记的东、西、南、北方向。候选位置需要有稳固的支撑方块，并为到达的实体提供足够的无障碍空间。

## 建设

```bash
./gradlew build
```

## 许可证

LGPL-v3

## 致谢

基于并兼容 gnembon 开发的 [Carpet Mod](https://github.com/gnembon/fabric-carpet)。

作者：IceCron — https://github.com/IceeCron