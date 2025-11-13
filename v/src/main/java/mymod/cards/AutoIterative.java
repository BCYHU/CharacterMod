package mymod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import mymod.ModTag;

import mymod.character.V;
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
        this.isEthereal=true;
        setMagic(4,-2);


        tags.add(ModTag.Card_v);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        SelectCardsInHandAction action = new SelectCardsInHandAction(1, "选择一张牌移除",
                card -> true,
                cards -> {
                    for (AbstractCard c : cards){
                        c.onRemoveFromMasterDeck();
                        p.hand.removeCard(c);
                        for (AbstractCard masterCard: p.masterDeck.group){
                            if(masterCard.uuid.equals(c.uuid)){
                                p.masterDeck.removeCard(masterCard);
                                break;
                            }
                        }
                    }
                }
         );

        addToBot(action);
        addToBot(new ApplyPowerAction(p,p,new VulnerablePower(p,magicNumber,false)));
        addToBot(new ApplyPowerAction(p,p,new WeakPower(p,magicNumber,false)));
        addToBot(new PressEndTurnButtonAction());

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AutoIterative();
    }
}
