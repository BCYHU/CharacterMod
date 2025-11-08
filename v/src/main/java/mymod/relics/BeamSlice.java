package mymod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import mymod.BasicMod;
import mymod.ModTag;
import mymod.util.TextureLoader;

import static mymod.BasicMod.makeID;

public class BeamSlice extends CustomRelic {
    public static final String ID = makeID(BeamSlice.class.getSimpleName());

    public BeamSlice() {
        super(ID,
                TextureLoader.getTexture(BasicMod.relicPath("BeamSlice.png")),
                TextureLoader.getTexture(BasicMod.relicPath("large/BeamSlice.png")),
                RelicTier.STARTER, LandingSound.CLINK);
        this.counter=0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        if(isV(c)){
            this.counter++;

            if(this.counter==4){
                this.counter=0;
                flash();
                this.pulse=false;
            }else if(this.counter==3){
                beginPulse();
                this.pulse=true;
                AbstractDungeon.player.hand.refreshHandLayout();
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player,this));
                addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new PenNibPower(AbstractDungeon.player,1),1,true));
            }
        }else{
            this.counter=0;
        }

    }

    public static boolean isV(AbstractCard c){
        return c.hasTag(ModTag.Card_v);
    }

    public AbstractRelic makeCopy() {
        return new BeamSlice();
    }

}
