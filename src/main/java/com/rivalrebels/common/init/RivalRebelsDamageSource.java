package com.rivalrebels.common.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
//import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class RivalRebelsDamageSource extends EntityDamageSourceIndirect  {

    public static DamageSource plasmaexplosion = new DamageSource("plasma_explosion").setExplosion();
    public static DamageSource timebomb = new DamageSource("timebomb").setExplosion(); // Maybe change this to vanilla explosion damage
    public static DamageSource cuchillo = new DamageSource("cuchillo");
    public static DamageSource charge = new DamageSource("charge"); // Maybe change this to vanilla explosion damage
    public static DamageSource cooked = new DamageSource("cooked").setFireDamage();
    public static DamageSource electricity = new DamageSource("electricity");
    public static DamageSource flare = new DamageSource("flare").setFireDamage(); // Maybe change this to vanilla fire damage
    public static DamageSource gas = new DamageSource("gas");
    public static DamageSource radiation = new DamageSource("radiation");
    public static DamageSource laser = new DamageSource("laser");
    public static DamageSource cyanide = new DamageSource("cyanide").setDamageAllowedInCreativeMode(); // because it's funny

    private float damage_amount;


    public static enum RR_damage_sources {
        PLASMA("plasma"),
        ELECTRICITY("electricity"),
        GAS("gas"),
        RADIATION("radiation"),
        INCINERATION("cooked"),
        EVICERATION("laser"),
        CYANIDE("cyanide");

        private String damage_source_string;

        // Constructor (runs once for each constant above)
        private RR_damage_sources(String damage_type) {
            this.damage_source_string = damage_type;
        }

        public String get_damage_source_string() {
            return damage_source_string;
        }
        
    }

    
    public RivalRebelsDamageSource(RR_damage_sources rr_type, Entity cause, Entity shooter) {

        super(rr_type.get_damage_source_string(), cause, shooter);
        if (rr_type == RR_damage_sources.INCINERATION) {
            this.setFireDamage();
        } else if (rr_type == RR_damage_sources.PLASMA) {
            this.setExplosion();
        }

        if (rr_type == RR_damage_sources.GAS) {
            this.setDamageBypassesArmor();
        }

        if (rr_type == RR_damage_sources.CYANIDE) {
            this.setDamageAllowedInCreativeMode(); // because it's funny
            this.setDamageBypassesArmor();
        }


    }


    @Override
    public ITextComponent getDeathMessage(EntityLivingBase victim) {

        Entity attacker = this.getTrueSource();

        String messageKey;

        switch (this.damageType) {

            case "cooked":
                messageKey = "death.attack.cooked";
                break;

            case "electricity":
                messageKey = "death.attack.electricity";
                break;

            case "plasma":
                messageKey = "death.attack.plasma";
                break;

            case "gas":
                messageKey = "death.attack.gas";
                break;

            case "radiation":
                messageKey = "death.attack.radiation";
                break;

            case "laser":
                messageKey = "death.attack.laser";
                break;

            case "cyanide":
                messageKey = "death.attack.cyanide";
                break;

            default:
                messageKey = "death.attack.generic";
                break;
        }

        if (attacker != null) {
            return new TextComponentTranslation(
                messageKey + ".player",
                victim.getDisplayName(),
                attacker.getDisplayName()
            );
        }

        return new TextComponentTranslation(
            messageKey,
            victim.getDisplayName()
        );
    }

}
