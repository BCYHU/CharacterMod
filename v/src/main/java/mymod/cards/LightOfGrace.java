package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import mymod.ModTag;
import mymod.character.V;
import mymod.util.CardStats;

public class LightOfGrace extends BaseCard{
    public static final String ID = makeID(LightOfGrace.class.getSimpleName());
    public static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1

    );
    public LightOfGrace(){
        super(ID,info);
        setSelfRetain(true);
        setMagic(3,2);


        tags.add(ModTag.Card_SP);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, magicNumber));
        if(this.upgraded){
            addToBot(new ApplyPowerAction(p, p, new RegenPower(p,2), 2));
        }else{
            addToBot(new ApplyPowerAction(p, p, new RegenPower(p,1), 1));
        }

    }
    public AbstractCard makeCopy() {
        return new LightOfGrace();
    }


}
