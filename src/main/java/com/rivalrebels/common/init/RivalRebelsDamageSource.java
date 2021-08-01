package com.rivalrebels.common.init;

import net.minecraft.util.DamageSource;

public class RivalRebelsDamageSource {
    public static DamageSource plasmaexplosion = new DamageSource("plasma_explosion").setExplosion();
    public static DamageSource cuchillo = new DamageSource("cuchillo");
    public static DamageSource charge = new DamageSource("charge");
    public static DamageSource cooked = new DamageSource("cooked").setFireDamage();
    public static DamageSource flare = new DamageSource("flare");
}
