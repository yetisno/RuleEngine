# Introduction
This Project defines a Simple Rule Engine Framework. Use this framework to make rule have more readability when
generate code.

# Definition

**Rule** : a `Rule` can contain multiple `Fact`, but it can't contain two same `fact` in a Rule. When `Rule` invoke `evaluate()`, it will invoke `evaluate()` of each `fact` in `Rule`.

**Fact**: a `Fact` contain `Condition`, `Action` and `TailFact`. `Fact` means some situations, it's intention is evaluated by `judge` the `Condition` with `Pattern`, then do `Action` and `TailFact` by the `Intention`.

**Condition**: a `Condition` be `judge()` with `Pattern`, it will return the `Intention` of this `Condition`.

**Action**: a Action means the action it should do in target `Intention` which implement `Actional`.

**Intention**: `Intention` is Enum, which contain `POSITIVE(true)` and `NEGATIVE(false)`.

**Pattern**: a `Pattern` contains mutliple `PatternUnit`, it's a key-value map. `Pattern` is the input of method `evaluate(Pattern pattern)` of `Rule`, and it can pass the value between `Action`, `evaluate()`, `judge()`, `TailFact`. `Pattern` will pass from `Rule.evaluate()` to `Fact.evaluate()`, `Condition.judge()`, `Action.doAction()`, `Fact.evaluate() (Tail)` and so on.

    Pattern Pass Flow:
    `Rule` -> `Fact1, Fact2`
    `Fact1` -> `Condition` -> `Action` -> `Fact1_t1[Tail]`
    `Fact1_t1` -> `Condition` -> `Action` -> `Fact1_t2[Tail]`
    `Fact1_t2` -> `Condition` -> `Action` -> `Fact1_tn[Tail]...`
    `Fact2` -> `Condition` -> `Action` -> `Fact2_tn[Tail]...`
**PatternUnit**: a `PatternUnit` means the unit of `Pattern`, it contain a key-value pair,it could be `Boolean`, `String`, `BigDecimal`, `Number`(Integer, Long, Double ...), `Calendar` and other `Object`. The implemented Class are `BigDecimalPatternUnit`, `BooleanPatternUnit`, `NumberPatternUnit`, `CalendarPatternUnit`, `ObjectPatternUnit` and `StringPatternUnit`.

---

# Example
##Case 1. 
Create a Rule, it determine the `Budget` of `Pattern` and mark `Level` to A or B.

```Java
Rule rule = new Rule("Level").addFact(
    new ActionFact()
        .setCondition(new Condition() {
            public Intention judge(Pattern pattern) {
                return Intention.get(pattern.getNumber("Budget").intValue() > 3000);
            }
        })
        .addAction(Intention.POSITIVE, new Actional() {
            public Actional doAction(Pattern pattern) {
                pattern.put("Level", "A");
                return null;
            }
        })
        .addAction(Intention.NEGATIVE, new Actional() {
            public Actional doAction(Pattern pattern) {
                pattern.put("Level", "B");
                return null;
            }
        })
);
Pattern pattern = new Pattern()
    .put("Budget", 4500);
rule.evaluate(pattern);
// The result of pattern.getString("Level") should be "A"
pattern.clear();
pattern.put("Budget", 1500);
rule.evaluate(pattern);
// The result of pattern.getString("Level") should be "B"
```