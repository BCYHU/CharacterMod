package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.ModTag;
import mymod.character.V;
import mymod.powers.SpeedAlongPower;
import mymod.util.CardStats;

public class ShieldOfInvincibility extends BaseCard{
    public static final String ID = makeID( ShieldOfInvincibility.class.getSimpleName());
    public static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1

    );
    public ShieldOfInvincibility(){
        super(ID,info);
        setSelfRetain(true);
        setMagic(1,1);


        tags.add(ModTag.Card_SP);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ApplyPowerAction(p, p, new SpeedAlongPower(p,magicNumber), magicNumber));

    }
    public AbstractCard makeCopy() {
        return new ShieldOfInvincibility();
    }


}
