package com.cube.user.event;

import com.cube.user.impl.ClassManager;
import com.cube.user.user.PlayerClass;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ClassEvent implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        ClassManager.getManager().load(event.getPlayer());
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        ClassManager.getManager().unload(event.getPlayer());
    }

    @EventHandler
    public void onKillEntityEvent(EntityDeathEvent event) {
        Entity dead = event.getEntity();

        if(dead instanceof Monster) {
            try {
                Player killer = ((Monster) dead).getKiller();
                PlayerClass playerClass = ClassManager.getManager().get(killer);
                if (playerClass == null) {
                    ClassManager.getManager().load(killer);
                    playerClass = ClassManager.getManager().get(killer);
                }
                playerClass.setHuntingPoint(playerClass.getHuntingPoint() + 1);
            }catch (Exception e) {
                System.out.println("...OnKillEntityEvent Except");
            }
        }
    }

    @EventHandler
    public void onPlayerPreprocessCommandEx(PlayerCommandPreprocessEvent event) {
        if(event.getMessage().contains("/등급")) {
            if(ClassManager.getManager().get(event.getPlayer()) == null) {
                ClassManager.getManager().load(event.getPlayer());
            }
        }
    }

}
