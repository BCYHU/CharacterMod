package mymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static mymod.BasicMod.makeID;


public class FuryMode extends BasePower{
    public static final String POWER_ID = makeID(FuryMode.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static  final int MAX_STACK = 5;

    public FuryMode(AbstractCreature owner,int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    public void updateDescription() {
        if(this.amount==1){
            this.description = powerStrings.DESCRIPTIONS[0]+powerStrings.DESCRIPTIONS[6];
        }else{
            this.description = powerStrings.DESCRIPTIONS[1]+ powerStrings.DESCRIPTIONS[2]+this.amount+powerStrings.DESCRIPTIONS[3]
            +powerStrings.DESCRIPTIONS[4]+this.amount+powerStrings.DESCRIPTIONS[5]+powerStrings.DESCRIPTIONS[6];
        }

    }


    public void onUseCard(AbstractCard c, UseCardAction action) {
        if(c.type==AbstractCard.CardType.ATTACK){
            int f=this.amount;
            for(int i=0;i<f;i++){
                addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner,1), 1));
                addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, 1), 1));
            }
        }
        flash();
    }
    public void atEndOfRound() {
        if(this.amount<=1){
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner,POWER_ID));
        }else{
           addToBot(new ReducePowerAction(this.owner, this.owner,POWER_ID,1));
        }
        addToBot(new DamageAction(this.owner,new DamageInfo(this.owner,this.amount, DamageInfo.DamageType.HP_LOSS)));
    }

    @Override
    public void stackPower(int stackAmount) {
        if(this.amount+stackAmount>MAX_STACK){
            this.amount=MAX_STACK;
        }else{
            this.amount+=stackAmount;
        }
    }
}
