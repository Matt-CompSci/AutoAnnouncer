# AutoAnnouncer

AutoAnnouncer is a server-side only mod which announces configured messages to players.

- Server-side only (no client install required)
- JSON config with live reload

---

## Features

- **Announcements:** Broadcasts a configurable message every X seconds.
- **Config reload:** Reload the config without restarting the server.

---

## Installation

1. Install **NeoForge** for your Minecraft version.
2. Download the `AutoAnnounce` mod JAR.
3. Place the JAR into your server’s `mods/` folder.
4. Start the server once to generate the config:
   - `config/autoannounce.json`
5. Edit the config to your liking.
6. Either restart the server or use the reload command:
   - `/autoannounce reload`

---

## Configuration

The config file is created at:

```text
config/autoannounce.json
