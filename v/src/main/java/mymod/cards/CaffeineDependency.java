package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import mymod.ModTag;
import mymod.powers.CaffeineDependencyPower;
import mymod.util.CardStats;
import mymod.character.V;

public class CaffeineDependency extends BaseCard{
    public static final String ID = makeID(CaffeineDependency.class.getSimpleName());
    public static final CardStats info =new CardStats(
            V.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    public CaffeineDependency() {
        super(ID,info);
        setMagic(6);
        setCostUpgrade(0);

        tags.add(ModTag.Card_Knight);


    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new CaffeineDependencyPower(p, 2)));
    }
    public AbstractCard makeCopy() {
        return new CaffeineDependency();
    }


}
