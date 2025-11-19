package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import mymod.ModTag;
import mymod.character.V;
import mymod.util.CardStats;

public class SpiritOfProtection extends BaseCard{
    public static final String ID = makeID(SpiritOfProtection.class.getSimpleName());
    public static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public SpiritOfProtection(){
        super(ID,info);
        setSelfRetain(true);
        setMagic(4);
        setInnate(false,true);

        tags.add(ModTag.Card_SP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new IntangiblePlayerPower(p,1)));
        addToBot(new ApplyPowerAction(p,p,new PlatedArmorPower(p,magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new SpiritOfProtection();
    }

}
