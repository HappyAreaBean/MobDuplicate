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

package cc.happyareabean.mobduplicate.commands;

import cc.happyareabean.mobduplicate.MobDuplicate;
import net.kyori.adventure.text.Component;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.CommandPlaceholder;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

import static cc.happyareabean.mobduplicate.utils.Utils.COMMAND_PREFIX;
import static cc.happyareabean.mobduplicate.utils.Utils.MINI_MESSAGE;

@Command("mobduplicate")
public class CommandMain {

    @CommandPlaceholder
    public void handle(BukkitCommandActor actor) {
        var value = MobDuplicate.INSTANCE.isDuplicateEnabled();

        MobDuplicate.INSTANCE.setDuplicateEnabled(!value);

        actor.reply(COMMAND_PREFIX
                .append(Component.text("Duplicate is now "))
                .append(!value ? MINI_MESSAGE.deserialize("<green>enabled") : MINI_MESSAGE.deserialize("<red>disabled"))
                .append(Component.text("!"))
        );
    }

    @Subcommand("status")
    public void status(BukkitCommandActor actor) {
        actor.reply(COMMAND_PREFIX
                .append(Component.text("Duplicate is currently "))
                .append(MobDuplicate.INSTANCE.isDuplicateEnabled() ?
                        MINI_MESSAGE.deserialize("<green>enabled") : MINI_MESSAGE.deserialize("<red>disabled"))
                .append(Component.text("."))
        );
    }

}
