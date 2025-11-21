package mymod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import mymod.BasicMod;
import mymod.ModTag;
import mymod.cards.SlashAttack;
import mymod.powers.ComprehendPower;
import mymod.powers.FuryMode;
import mymod.util.TextureLoader;
import static mymod.BasicMod.makeID;


public class BeamSlice extends CustomRelic {
    public static final String ID = makeID(BeamSlice.class.getSimpleName());
    public AbstractCard SA;

    public BeamSlice() {
        super(ID,
                TextureLoader.getTexture(BasicMod.relicPath("BeamSlice.png")),
                TextureLoader.getTexture(BasicMod.relicPath("large/BeamSlice.png")),
                RelicTier.STARTER, LandingSound.CLINK);
        this.counter=0;
        createSlashA();
    }

    private void createSlashA() {
        SA=new SlashAttack(){
            @Override
            public AbstractCard makeCopy() {
                SlashAttack SAc = (SlashAttack) super.makeCopy();
                SAc.cost = 0;
                SAc.costForTurn = 0;
                SAc.freeToPlayOnce = true;
                return SAc;
            }
        };
        SA.cost = 0;
        SA.costForTurn = 0;
        SA.freeToPlayOnce = true;
        SA.isEthereal = true;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }



    @Override
    public void atBattleStart() {
        this.counter=0;
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ComprehendPower(AbstractDungeon.player,0)));
    }
    public void onUseCard(AbstractCard c, UseCardAction a) {
        if(isV(c)){
            this.counter++;
            if(this.counter==2){
                this.pulse=true;
                beginPulse();
            }else if(this.counter==3){
                this.pulse=false;
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player,this));
                addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new FuryMode(AbstractDungeon.player,1)));
                addToBot(new MakeTempCardInHandAction(SA.makeCopy(),1,false));
                AbstractDungeon.player.hand.refreshHandLayout();
                this.counter=0;
                flash();
            }
        }else{
            this.counter=0;
            this.pulse=false;
        }

    }

    public static boolean isV(AbstractCard c){
        return c.hasTag(ModTag.Card_v);
    }

    public AbstractRelic makeCopy() {
        return new BeamSlice();
    }

}
