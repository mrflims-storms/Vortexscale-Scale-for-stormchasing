@Mod("vortexscale")
public class VortexScaleMod {

    public VortexScaleMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);

        for (ServerLevel level : server.getAllLevels()) {
            double wind = level.random.nextDouble() * 600;
            Integer cat = VortexScale.getCategory(wind);

            if (cat != null) {
                System.out.println("Forge Wind: " + wind + " mph → " + VortexScale.describe(cat));
            }
        }
    }
}
