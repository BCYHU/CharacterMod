package mymod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import mymod.character.V;
import mymod.powers.AutoIterativePower;
import mymod.util.CardStats;

import java.util.ArrayList;

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

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        //remove
//        if(!p.discardPile.isEmpty()){
//            ArrayList<AbstractCard> removeCards = new ArrayList<>();
//            for(AbstractCard c : AbstractDungeon.player.discardPile.group)
//                removeCards.add(c);
//        }
        //upgrade
//        addToBot(new AbstractGameAction() {
//            private AbstractCard theCard = null;
//            public void update() {
//                ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
//                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
//                    if (c.canUpgrade())
//                        upgradableCards.add(c);
//                }
//                if(!upgradableCards.isEmpty()){
//                    this.theCard=upgradableCards.get(AbstractDungeon.miscRng.random(0,upgradableCards.size()-1));
//                    this.theCard.upgrade();
//                    AbstractDungeon.player.bottledCardUpgradeCheck(this.theCard);
//                }
//                if (this.isDone && this.theCard != null) {
//                AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
//                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.theCard.makeStatEquivalentCopy()));
//                addToTop(new WaitAction(Settings.ACTION_DUR_MED));
//                }
//            }
//        });

        addToBot(new PressEndTurnButtonAction());
        addToBot(new ApplyPowerAction(p,p,new AutoIterativePower(p,1),1));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AutoIterative();
    }
}
