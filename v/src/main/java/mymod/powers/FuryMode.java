package mymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static mymod.BasicMod.makeID;
import static mymod.BasicMod.modID;

public class FuryMode extends BasePower{
    public static final String POWER_ID = makeID(FuryMode.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(modID+":FuryMode");
    public FuryMode(AbstractCreature owner,int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0]+ this.amount + powerStrings.DESCRIPTIONS[1];
    }


    public void onUseCard(AbstractCard c, UseCardAction action) {
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.amount), this.amount));
        addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, this.amount), this.amount));
        flash();
    }
}
