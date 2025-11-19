package mymod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.ModTag;
import mymod.character.V;
import mymod.util.CardStats;

import static mymod.BasicMod.modID;

public class SlashAttack extends BaseCard{
    public static final String ID = makeID(SlashAttack.class.getSimpleName());
    private static final CardStats info=new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );


    public SlashAttack(){
        super(ID,info);
        setDamage(4,3);
        setExhaust(true);

        tags.add(ModTag.Card_v);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAction(
                m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if(AbstractDungeon.player.hasPower(modID+":FuryMode"))
            addToBot(new HealAction(p,p,3));;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if(AbstractDungeon.player.hasPower(modID+":FuryMode"))
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SlashAttack();
    }
}
