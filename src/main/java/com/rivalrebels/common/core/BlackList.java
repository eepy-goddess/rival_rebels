package com.rivalrebels.common.core;

//import assets.rivalrebels.RivalRebels;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BlackList {
    public static Block[] tesla = new Block[]{
        // RivalRebels.omegaobj,
        // RivalRebels.sigmaobj,
        // RivalRebels.fshield,
        // Blocks.field_150357_h,
        // Blocks.field_150355_j,
        // Blocks.field_150358_i,
        // Blocks.field_150353_l,
        // Blocks.field_150356_k,
        // Blocks.field_150359_w,
        // Blocks.field_150343_Z,
        // Blocks.field_150410_aZ,
        // Blocks.field_150399_cn,
        // Blocks.field_150397_co,
        // RivalRebels.nuclearBomb,
        // RivalRebels.tsarbombablock,
        // RivalRebels.loader,
        // RivalRebels.reactor,
        // RivalRebels.forcefieldnode,
        // RivalRebels.conduit,
        // RivalRebels.forcefield,
        // RivalRebels.meltdown,
        // RivalRebels.ffreciever
   };
    public static Block[] explosion = new Block[]{
        // RivalRebels.fshield,
        // RivalRebels.nuclearBomb,
        // RivalRebels.tsarbombablock,
        // RivalRebels.omegaobj,
        // RivalRebels.sigmaobj,
        // RivalRebels.forcefieldnode,
        // RivalRebels.reactor,
        // RivalRebels.loader,
        // RivalRebels.meltdown,
        // RivalRebels.forcefield,
        // RivalRebels.ffreciever
    };
    public static Block[] plasmaExplosion = new Block[]{
        // RivalRebels.omegaobj,
        // RivalRebels.sigmaobj,
        // RivalRebels.tsarbombablock,
        // RivalRebels.nuclearBomb,
        // RivalRebels.reactor,
        // RivalRebels.loader,
        // RivalRebels.controller,
        // RivalRebels.meltdown,
        // RivalRebels.forcefield,
        // RivalRebels.ffreciever
    };
    public static Block[] autobuild = new Block[]{
        // RivalRebels.fshield,
        // RivalRebels.nuclearBomb,
        // RivalRebels.tsarbombablock,
        // RivalRebels.omegaobj,
        // RivalRebels.sigmaobj,
        // RivalRebels.forcefieldnode,
        // RivalRebels.reactive,
        // RivalRebels.conduit,
        // RivalRebels.reactor,
        // RivalRebels.loader,
        // RivalRebels.meltdown,
        // RivalRebels.forcefield,
        // RivalRebels.ffreciever,
        // Blocks.field_150357_h
    };

    public static boolean tesla(Block block) {
        for (int i = 0; i < tesla.length; i++) {
            if (tesla[i] == block) {
                return true;
            }
        }

        return false;
    }

    public static boolean explosion(Block block) {
        for (int i = 0; i < explosion.length; i++) {
            if (explosion[i] == block) {
                return true;
            }
        }

        return false;
    }

    public static boolean plasmaExplosion(Block block) {
        for (int i = 0; i < plasmaExplosion.length; i++) {
            if (plasmaExplosion[i] == block) {
                return true;
            }
        }

        return false;
    }

    public static boolean autobuild(Block block) {
        for (int i = 0; i < autobuild.length; i++) {
            if (autobuild[i] == block) {
                return true;
            }
        }

        return false;
    }
}
