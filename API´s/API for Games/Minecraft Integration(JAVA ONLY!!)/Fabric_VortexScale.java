public class VortexScaleMod implements ModInitializer {

    @Override
    public void onInitialize() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                double wind = world.random.nextDouble() * 600;
                Integer cat = VortexScale.getCategory(wind);

                if (cat != null) {
                    System.out.println("Fabric Wind: " + wind + " mph → " + VortexScale.describe(cat));
                }
            }
        });
    }
}
