package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import mymod.ModTag;
import mymod.character.V;
import mymod.powers.ThornsOfVengeancePower;
import mymod.util.CardStats;

public class ThornsOfVengeance extends BaseCard{
    public static final String ID = makeID(ThornsOfVengeance.class.getSimpleName());
    public static final CardStats info= new CardStats(
            V.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public ThornsOfVengeance(){
        super(ID, info);
        setMagic(3,-1);
        setSelfRetain( true);
        setExhaust( true);

        tags.add(ModTag.Card_SP);

    }
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new ThornsPower(p,3)));
        addToBot(new ApplyPowerAction(p,p,new ThornsOfVengeancePower(p,0,magicNumber)));


    }
    public AbstractCard makeCopy() {
        return new ThornsOfVengeance();
    }

}
