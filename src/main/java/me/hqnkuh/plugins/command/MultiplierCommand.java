package me.hqnkuh.plugins.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import me.hqnkuh.plugins.MultiplierDrop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

import java.util.List;

public class MultiplierCommand {
    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("multiplier")
                .requires(source -> source.getSender().hasPermission("md.commands.multiplier"))
                .then(Commands.literal("get")
                        .executes(context -> {
                            context.getSource().getSender().sendMessage(
                                    MiniMessage.miniMessage().deserialize("<white>現在のドロップ倍率は</white> <aqua><b><multiplier></b></aqua> <white>です</white>",
                                            Placeholder.component("multiplier", Component.text(MultiplierDrop.getInstance().getMultiplier())))
                            );
                            return Command.SINGLE_SUCCESS;
                        })
                )
                .then(Commands.literal("set")
                        .then(Commands.argument("multiplier", IntegerArgumentType.integer(1))
                                .executes(context -> {
                                    final int multiplier = IntegerArgumentType.getInteger(context, "multiplier");
                                    MultiplierDrop.getInstance().setMultiplier(multiplier);
                                    context.getSource().getSender().sendRichMessage("<white>ドロップ倍率を</white> <aqua><b><multiplier></b></aqua> <white>に設定しました</white>",
                                            Placeholder.component("multiplier", Component.text(multiplier))
                                    );
                                    return Command.SINGLE_SUCCESS;
                                })
                        )
                )
                .build();
    }
}
