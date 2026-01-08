package com.matt.autoannounce;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class AutoAnnounceConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "autoannounce.json";

    public static class AnnouncementInfo {
        public int FrequencySeconds;
        public int OffsetSeconds;
        public String AnnouncementMsg;

        public AnnouncementInfo( int FrequencySeconds, String AnnouncementMsg ) {
            this.FrequencySeconds = FrequencySeconds;
            this.AnnouncementMsg = AnnouncementMsg;
        }
    }

    public List<AnnouncementInfo> Announcements = List.of(
            new AnnouncementInfo( 60, "Test Announcement"),
            new AnnouncementInfo( 60, "2nd Test" )
    );

    public static AutoAnnounceConfig load(File configDir) {
        File file = new File(configDir, FILE_NAME);

        if (!file.exists()) {
            AutoAnnounceConfig cfg = new AutoAnnounceConfig();
            cfg.save(file);
            return cfg;
        }

        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, AutoAnnounceConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new AutoAnnounceConfig();
        }
    }

    public void save(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(this, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
