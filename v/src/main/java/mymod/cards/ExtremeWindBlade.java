package mymod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.actions.ExtremeWindBladeAction;
import mymod.character.V;
import mymod.util.CardStats;

import java.util.ArrayList;

public class ExtremeWindBlade extends BaseCard{
    public static final String ID = makeID(ExtremeWindBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    public ExtremeWindBlade(){
        super(ID,info);
        setDamage(3);
        setMagic(2,2);

        this.damageType = DamageInfo.DamageType.THORNS;
        this.isMultiDamage = true;

    }

    public static int triggerCount = 0;
    private static int tCheck = 1;

    public static void onCardUsedWithTag(){
        if(isExtremeWindBladeInHand()){
            triggerCount++;
            if(triggerCount >tCheck) {
                triggerCount = 0;
                autoUseCard();
            }

                }
    }
    public void triggerOnGlowCheck() {
        if (triggerCount == tCheck) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    private static boolean isExtremeWindBladeInHand(){
        AbstractPlayer player = AbstractDungeon.player;
        if(player== null|| player.hand==null)
            return false;
        for(AbstractCard card : player.hand.group){
            if(card instanceof ExtremeWindBlade){
                    card.triggerOnGlowCheck();

                return true;
            }
        }
        return false;
    }

    public static void autoUseCard(){
        AbstractPlayer player = AbstractDungeon.player;
        if(player== null|| player.hand==null)
            return;

        ArrayList<AbstractCard> cardsToDiscard = new ArrayList<>();

        for(AbstractCard card : player.hand.group){
            if(card instanceof ExtremeWindBlade){

                cardsToDiscard.add(card);

                ExtremeWindBlade blade = (ExtremeWindBlade) card;
                blade.calculateCardDamage(null);
                AbstractMonster target = AbstractDungeon.getRandomMonster();
                if(target != null){
                    AbstractDungeon.actionManager.addToBottom(
                            new ExtremeWindBladeAction(
                                    player,
                                    blade.multiDamage,
                                    blade.damageTypeForTurn,
                                    blade.upgraded
                            )
                    );
                }
            }

        }
        for(AbstractCard c:cardsToDiscard){
            player.hand.moveToDiscardPile(c);
        }
    }

    public static void resetTriggerCount(){
        triggerCount = 0;

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(null);
        addToBot(new ExtremeWindBladeAction(p,this.multiDamage,this.damageTypeForTurn,this.upgraded));
    }


    public AbstractCard makeCopy() {
        return new ExtremeWindBlade();
    }

}

