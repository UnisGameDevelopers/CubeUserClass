package com.cube.user.command;

import com.cube.user.impl.ClassManager;
import com.cube.user.user.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClassCommand implements CommandExecutor {

    /**
     * /class setev [player] [float]
     * /class update
     * */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equals("등급")) {
            if(sender.isOp()) {
                if(args.length == 1 && args[0].equals("업데이트"))
                    ClassManager.getManager().update();
            }
            if(args.length == 0) {
                PlayerClass playerClass = ClassManager.getManager().get((Player) sender);
                sender.sendMessage(" ");
                sender.sendMessage("§f[ §6! §f] 페어리 점수: §e" + playerClass.getFairyPoint() + "§fP §c(페어리 업데이트시 활성화)");
                sender.sendMessage("§f[ §6! §f] PVP 점수: §e" + playerClass.getPvpPoint() + "§fP §c(Rpg world 업데이트시 활성화)");
                sender.sendMessage("§f[ §6! §f] 유물 점수: §e" + playerClass.getRelicPoint() + "§fP §c(Rpg world 업데이트시 활성화)");
                sender.sendMessage("§f[ §6! §f] 재산 점수: §e" + playerClass.getPropertyPoint() + "§fP");
                sender.sendMessage("§f[ §6! §f] 수렵 점수: §e" + playerClass.getHuntingPoint() + "§fP");
                sender.sendMessage(" ");
                sender.sendMessage("§f[ §6! §f] §a나의 영향력 총 점수(랭크반영)§f: "+ playerClass.getEverage());
                sender.sendMessage("§f[ §6! §f] §a나의 랭크(영향력에 의한)§f: "+ playerClass.getAbstractClass().getClassName());
                sender.sendMessage(" ");
            }
        }
        return false;
    }
}
