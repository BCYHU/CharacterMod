package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import mymod.ModTag;
import mymod.character.V;
import mymod.powers.RevolutionHammerPower;
import mymod.util.CardStats;

public class RevolutionHammer extends BaseCard{
    public static final String ID = makeID(RevolutionHammer.class.getSimpleName());
    public static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public RevolutionHammer(){
        super(ID,info);
        setSelfRetain(true);
        setMagic(3,2);

        tags.add(ModTag.Card_SP);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new ThornsPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p,p,new RevolutionHammerPower(p,1)));
    }


    public AbstractCard makeCopy() {
        return new RevolutionHammer();
    }

}
