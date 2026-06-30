@Mod("vortexscale")
public class VortexScaleMod {

    public VortexScaleMod(IEventBus bus) {
        bus.addListener(this::onServerTick);
    }

    private void onServerTick(ServerTickEvent event) {
        if (event.phase != ServerTickEvent.Phase.END) return;

        MinecraftServer server = event.getServer();

        for (ServerLevel level : server.getAllLevels()) {
            double wind = level.random.nextDouble() * 600; // Beispiel
            Integer cat = VortexScale.getCategory(wind);

            if (cat != null) {
                System.out.println("NeoForge Wind: " + wind + " mph → " + VortexScale.describe(cat));
            }
        }
    }
}