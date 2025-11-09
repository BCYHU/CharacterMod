package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.ModTag;
import mymod.actions.AutoIterativeAction;
import mymod.character.V;
import mymod.powers.AutoIterativePower;
import mymod.util.CardStats;

public class AutoIterative extends BaseCard{
    public static final String ID = makeID(AutoIterative.class.getSimpleName());
    public static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public AutoIterative(){
        super(ID,info);
        this.exhaust=true;
        this.magicNumber=1;
        tags.add(ModTag.Card_v);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//
        addToBot(new AutoIterativeAction(this.magicNumber));

        addToBot(new PressEndTurnButtonAction());
        addToBot(new ApplyPowerAction(p,p,new AutoIterativePower(p,1),1));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AutoIterative();
    }
}
