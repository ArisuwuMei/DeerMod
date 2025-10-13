package mei.arisuwu.deermod;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public record ModConfig(String configVersion, ModSpawnConfig habitatBiomesDeerSpawnSettings, ModSpawnConfig escapadeBiomesDeerSpawnSettings)
{
    private static final Logger LOGGER = LogUtils.getLogger();

    private final static GsonBuilder GSON_BUILDER = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setPrettyPrinting();

    public static ModConfig DEFAULT = new ModConfig(
        "0.0.1",
        new ModSpawnConfig(14, 2, 6),
        new ModSpawnConfig(4, 1, 2)
    );

    public static ModConfig load(Path path)
    {
        if (Files.notExists(path))
        {
            DEFAULT.save(path);
            return DEFAULT;
        }

        try (var reader = Files.newBufferedReader(path))
        {
            return GSON_BUILDER.create().fromJson(reader, ModConfig.class);
        }
        catch (IOException e)
        {
            LOGGER.error("Failed to load the config file: {}\nLoading default setting instead", path, e);
            return DEFAULT;
        }
    }

    public void save(Path path)
    {
        var jsonContent = GSON_BUILDER.create().toJson(this);

        try
        {
            Files.writeString(path, jsonContent, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
        catch (IOException e)
        {
            LOGGER.error("Failed to save the config file: {}", path, e);
        }
    }

    public record ModSpawnConfig(int spawnRate, int minGroupSize, int maxGroupSize) {}
}
