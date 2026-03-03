/*
 * MIT License
 *
 * Copyright (c) 2026 HappyAreaBean
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cc.happyareabean.mobduplicate;

import cc.happyareabean.mobduplicate.commands.CommandMain;
import cc.happyareabean.mobduplicate.listener.PlayerDamageEntityListener;
import cc.happyareabean.mobduplicate.utils.ModrinthUpdateChecker;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitLamp;

@Getter
public class MobDuplicate extends JavaPlugin {
    public static MobDuplicate INSTANCE;
    @Setter private boolean duplicateEnabled = false;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getSLF4JLogger().info("ModDuplicate by {} has been enabled!", getPluginMeta().getAuthors());
        getSLF4JLogger().info("Current version: {}", getPluginMeta().getVersion());

        loadCommands();
        loadListener();
        updateCheck();
    }

    private void loadListener() {
        getServer().getPluginManager().registerEvents(new PlayerDamageEntityListener(), this);
    }

    private void loadCommands() {
        var lamp = BukkitLamp.builder(this).build();

        lamp.register(new CommandMain());
    }

    private void updateCheck() {
        new ModrinthUpdateChecker("mobduplicate", "paper")
                .setFeatured(true)
                .setOnError(null)
                .checkVersion(version -> {
                    if (version == null || getPluginMeta().getVersion().equals(version)) {
                        getSLF4JLogger().info("MobDuplicate is up to date.");
                        return;
                    }
                    getSLF4JLogger().warn("New version available: {}", version);
                    getSLF4JLogger().warn("Download at https://modrinth.com/plugin/mobduplicate");
                });
    }

}
