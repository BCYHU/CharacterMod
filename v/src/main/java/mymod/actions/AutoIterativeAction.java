package mymod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import java.util.ArrayList;



public class AutoIterativeAction extends AbstractGameAction {

    private AbstractCard theCard;
    private int numberOfCards;
    private AbstractPlayer player ;
    private boolean optional;


    public AutoIterativeAction(int numberOfCards,boolean optional){
        this.player=AbstractDungeon.player;
        this.numberOfCards=numberOfCards;
        this.optional=optional;
        this.duration=this.startDuration=Settings.ACTION_DUR_FAST;
    }
    public AutoIterativeAction(int numberOfCards){
        this(numberOfCards,false);
    }


    public void update() {
        //remove
        if(this.duration==this.startDuration){
            if(this.player.drawPile.isEmpty()|| this.numberOfCards<=0){
                this.isDone=true;
                return;
            }
            if (this.player.drawPile.size() <= this.numberOfCards && !this.optional) {
                ArrayList<AbstractCard> cardsToRemove = new ArrayList<>();
                for (AbstractCard c : this.player.drawPile.group)
                    cardsToRemove.add(c);
                for (AbstractCard c : cardsToRemove) {
                    this.player.masterDeck.removeCard(c);
                    this.player.drawPile.removeCard(c);

                }
                this.isDone = true;
                return;
            }
            CardGroup temp =new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(AbstractCard c:this.player.drawPile.group)
                temp.addToTop(c);
            temp.sortAlphabetically(true);
            temp.sortByRarityPlusStatusCardType(false);
            if(this.numberOfCards==1){
                if(this.optional){
                    AbstractDungeon.gridSelectScreen.open(temp,this.numberOfCards,true,"选择要删除的卡牌");
                }else{
                    AbstractDungeon.gridSelectScreen.open(temp,this.numberOfCards,"选择要删除的卡牌",false);
                }
            }else if(this.optional){
                AbstractDungeon.gridSelectScreen.open(temp,this.numberOfCards,true,"选择要删1除的卡牌");
            }else {
                AbstractDungeon.gridSelectScreen.open(temp,this.numberOfCards,"xxxx",false);
            }
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                this.player.masterDeck.removeCard(c);
                this.player.drawPile.removeCard(c);

            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
            tickDuration();
        }


        //Upgrade
        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.canUpgrade())
                upgradableCards.add(c);
        }
        if (!upgradableCards.isEmpty()) {
            this.theCard = upgradableCards.get(AbstractDungeon.miscRng.random(0, upgradableCards.size() - 1));
            this.theCard.upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(this.theCard);
        }
        tickDuration();
        if (this.isDone && this.theCard != null) {
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.theCard.makeStatEquivalentCopy()));
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));

        }
    }
}
