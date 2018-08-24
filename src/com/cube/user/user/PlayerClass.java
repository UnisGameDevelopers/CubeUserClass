package com.cube.user.user;

import com.cube.user.impl.AbstractClass;

public class PlayerClass {

    /* 유저의 계급정보를 담고있는 클래스 */

    private AbstractClass abstractClass;
    private int fairyPoint; /** 페어리 등급에따른 점수 */
    private int relicPoint; /** 유물 점수 */
    private int pvpPoint; /** rpg world 에서의 무자비 점수 */
    private double propertyPoint; /** 재산 점수 */
    private int huntingPoint; /** 수렵 및 채광 점수 */

    public PlayerClass(AbstractClass abstractClass,
                       int fairyPoint, int relicPoint, int pvpPoint, int huntingPoint) {
        this.abstractClass = abstractClass;
        this.fairyPoint = fairyPoint;
        this.relicPoint = relicPoint;
        this.pvpPoint = pvpPoint;
        this.huntingPoint = huntingPoint;
    }

    public double getEverage() {
        return Double.parseDouble(String.format("%.2f", (fairyPoint + relicPoint + pvpPoint + propertyPoint + huntingPoint)/5));
    }
    public AbstractClass getAbstractClass() { return abstractClass; }
    public int getFairyPoint() { return fairyPoint; }
    public int getRelicPoint() { return relicPoint; }
    public int getPvpPoint() { return pvpPoint; }
    public float getPropertyPoint() { return Float.parseFloat(String.format("%.2f", propertyPoint)); }
    public int getHuntingPoint() { return huntingPoint; }

    public void setAbstractClass(AbstractClass abstractClass) { this.abstractClass = abstractClass; }
    public void setFairyPoint(int fairyPoint) { this.fairyPoint = fairyPoint; }
    public void setHuntingPoint(int huntingPoint) { this.huntingPoint = huntingPoint; }
    public void setRelicPoint(int relicPoint) { this.relicPoint = relicPoint; }
    public void setPvpPoint(int pvpPoint) { this.pvpPoint = pvpPoint; }
    public void setPropertyPoint(double propertyPoint) { this.propertyPoint = Double.parseDouble(String.format("%.2f", propertyPoint)); }

}
