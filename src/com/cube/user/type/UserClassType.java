package com.cube.user.type;

import com.cube.user.classes.MasterClass;
import com.cube.user.classes.NewbieClass;
import com.cube.user.impl.AbstractClass;

public enum UserClassType {

    Newbie1(new NewbieClass(0, 1000, "§f[ §el §f]")),
    Newbie2(new NewbieClass(1001, 7999, "§f[ §ell §f]")),
    Newbie3(new NewbieClass(8000, 19999, "§f[ §elll §f]")),
    Newbie4(new NewbieClass(20000, 59999, "§f[ §ellll §f]")),
    Newbie5(new NewbieClass(60000, 99999, "§f[ §eV §f]")),

    Master1(new MasterClass(100000, 199999, "§f[ §4l §f]")),
    Master2(new MasterClass(200000, 499999, "§f[ §4ll §f]")),
    Master3(new MasterClass(500000, 699999, "§f[ §4lll §f]")),
    Master4(new MasterClass(700000, 899999, "§f[ §4llll §f]")),
    Master5(new MasterClass(900000, 999999, "§f[ §4V §f]"));

    private AbstractClass abstractClass;

    private UserClassType(AbstractClass abstractClass) {
        this.abstractClass = abstractClass;
    }

    public AbstractClass getAbstractClass() { return abstractClass; }

}
