package com.cube.user.impl;

import com.cube.user.CubeUserClassPlugin;
import com.cube.user.type.UserClassType;
import com.cube.user.user.PlayerClass;
import com.cube.user.util.DataManager;
import com.shop.money.type.MoneyType;
import com.shop.money.wrappers.MoneyWrapper;
import com.shop.money.wrappers.MoneyWrapperManager;
import com.shop.money.wrappers.impl.AbstractMoneyWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClassManager {

    private static ClassManager instance;
    private static DataManager dataManager = new DataManager("class.yml");
    private static final double PROPERTY_MULTYPLY = 0.00005D;

    private Set<AbstractClass> classes = new HashSet<>();
    private Map<Player, PlayerClass> classMap = new HashMap<>();

    private ClassManager(){}

    public static ClassManager getManager() {
        if(instance == null) instance = new ClassManager();
        return instance;
    }

    public void load(Player player) {
        if(player == null) return;
        if(dataManager.get(player.getName() + ".class") == null) {
            PlayerClass playerClass = new PlayerClass(UserClassType.Newbie1.getAbstractClass(), 0, 0, 0, 0);
            classMap.put(player, playerClass);
            this.save(playerClass, player);
            return;
        }
        String classType = dataManager.getString(player.getName() + ".class");
        AbstractMoneyWrapper<?> moneyWrapper = MoneyWrapperManager.get(MoneyType.MONEY, player.getName());
        int fairyPoint = dataManager.getInt(player.getName() + ".fairyPoint");
        int relicPoint = dataManager.getInt(player.getName() + ".relicPoint");
        int pvpPoint = dataManager.getInt(player.getName() + ".pvpPoint");
        int huntingPoint = dataManager.getInt(player.getName() + ".huntingPoint");
        PlayerClass playerClass = new PlayerClass(UserClassType.valueOf(classType).getAbstractClass(), fairyPoint, relicPoint,
                pvpPoint, huntingPoint);
        playerClass.setPropertyPoint(playerClass.getPropertyPoint() + moneyWrapper.getAmount(player.getName()) * PROPERTY_MULTYPLY);
        classMap.put(player, playerClass);
    }

    public void unload(Player player) {
        if(classMap.containsKey(player)) {
            PlayerClass playerClass = classMap.get(player);
            this.save(playerClass, player);
            classMap.remove(player);
        }
    }

    public void save(PlayerClass playerClass, Player player) {
        String name = player.getName();
        dataManager.set(name + ".class", playerClass.getAbstractClass().getType().name());
        dataManager.set(name + ".fairyPoint", playerClass.getFairyPoint());
        dataManager.set(name + ".relicPoint", playerClass.getRelicPoint());
        dataManager.set(name + ".pvpPoint", playerClass.getPvpPoint());
        dataManager.set(name + ".huntingPoint", playerClass.getHuntingPoint());
        DataManager.save(dataManager);
    }

    public void writeDataManager(PlayerClass playerClass, Player player) {
        String name = player.getName();
        dataManager.set(name + ".class", playerClass.getAbstractClass().getType().name());
        dataManager.set(name + ".fairyPoint", playerClass.getFairyPoint());
        dataManager.set(name + ".relicPoint", playerClass.getRelicPoint());
        dataManager.set(name + ".pvpPoint", playerClass.getPvpPoint());
        dataManager.set(name + ".huntingPoint", playerClass.getHuntingPoint());
    }

    public void initialize() {
        for(UserClassType type : UserClassType.values()) {
            type.getAbstractClass().setType(type);
            classes.add(type.getAbstractClass());
        }
        updateScheduler();
    }

    public PlayerClass get(Player player) {
        return classMap.get(player);
    }

    public void update(){
        for(Map.Entry<Player, PlayerClass> entry : classMap.entrySet()) {
            AbstractMoneyWrapper<?> moneyWrapper = MoneyWrapperManager.get(MoneyType.MONEY, entry.getKey().getName());
            PlayerClass playerClass = entry.getValue();
            playerClass.setPropertyPoint(moneyWrapper.getAmount(entry.getKey().getName()) * PROPERTY_MULTYPLY);

            for(AbstractClass abstractClass : classes) {
                if(playerClass.getEverage() >= abstractClass.getMinValue() &&
                        playerClass.getEverage() <= abstractClass.getMaxValue()) {
                    playerClass.setAbstractClass(abstractClass);
                }
            }
            this.writeDataManager(playerClass, entry.getKey());
        }
        DataManager.save(dataManager);
    }

    /* 유저의 계급을 갱신
     * 주기 = 30sec
     */
    public void updateScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CubeUserClassPlugin.instance, new Runnable() {
            @Override
            public void run() {
                update();
            }
        }, 0L, 600L);
    }

}
