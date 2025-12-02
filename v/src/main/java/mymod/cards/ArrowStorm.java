package mymod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import mymod.ModTag;
import mymod.character.V;
import mymod.util.CardStats;



public class ArrowStorm extends BaseCard{
    public static final String ID = makeID(ArrowStorm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            1
    );
    private static final int DAMAGE = 1;
    private static final int baseMagicNumber = 4;

    public ArrowStorm(){
        super(ID,info);
        setDamage(DAMAGE);
        setMagic(baseMagicNumber);
        setExhaust(true);

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for (int i = 0; i < magicNumber; i++){
            int randomValue = AbstractDungeon.cardRandomRng.random(0,6);
            if (randomValue==0){
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractMonster target = AbstractDungeon.getRandomMonster();
                        if (target !=null) {
                            addToTop(new DamageAction(target, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                                    AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                            addToTop(new ApplyPowerAction(target, p, new WeakPower(target, 1, false), 1));
                        }
                        this.isDone=true;
                    }
                });
            }else {
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractMonster target = AbstractDungeon.getRandomMonster();
                        if (target !=null) {
                            addToTop(new DamageAction(target, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                                    AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                            addToTop(new ApplyPowerAction(target, p, new WeakPower(target, 1, false), 1));
                        }
                        this.isDone=true;
                    }
                });
            }
        }
    }
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(3);
        }
    }
    public AbstractCard makeCopy() { //Optional
        return new ArrowStorm();
    }

}
