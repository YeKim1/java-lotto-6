package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Prize {
    FIRST("6개 일치", 2000000000,
            (matchCount, isMatchBonus) -> matchCount == 6),
    SECOND("5개 일치, 보너스 볼 일치", 30000000,
            (matchCount, isMatchBonus) -> matchCount == 5 && isMatchBonus),
    THIRD("5개 일치", 1500000,
            (matchCount, isMatchBonus) -> matchCount == 5 && !isMatchBonus),
    FOURTH("4개 일치", 50000,
            (matchCount, isMatchBonus) -> matchCount == 4),
    FIFTH("3개 일치", 5000,
            (matchCount, isMatchBonus) -> matchCount == 3),
    MISS("", 0,
            (matchCount, isMatchBonus) -> matchCount < 3);

    private final String label;
    private final int money;
    private final BiPredicate<Integer, Boolean> condition;

    Prize(String label, int money, BiPredicate<Integer, Boolean> condition) {
        this.label = label;
        this.money = money;
        this.condition = condition;
    }

    public static Prize getPrizeByCount(int matchCount, boolean isMatchBonus) {
        return Arrays.stream(Prize.values())
                .filter(prize -> prize.condition.test(matchCount, isMatchBonus))
                .findAny().get();
    }

    public int getMoney() {
        return money;
    }
}
