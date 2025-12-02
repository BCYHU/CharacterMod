package mymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static mymod.BasicMod.makeID;

public class ThornsOfVengeancePower extends BasePower{
    public int threshold;
    public static final String POWER_ID = makeID(ThornsOfVengeancePower.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public ThornsOfVengeancePower(AbstractCreature owner, int amount,int threshold){
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.threshold = threshold;
        updateDescription();
    }
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0]+threshold+powerStrings.DESCRIPTIONS[1];
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        this.amount++;
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount >= threshold ) {
            this.flash();
            for (int i = 0; i < this.amount/threshold ; ++i) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DoubleDamagePower(this.owner, 1, false)));
            }
            this.amount = 0;
        }
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
}
