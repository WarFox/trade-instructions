# Trade Instruction Aggregator

[![wercker status](https://app.wercker.com/status/f653cf00b6e7654d132d7fc21dd28a65/s/master "wercker status")](https://app.wercker.com/project/byKey/f653cf00b6e7654d132d7fc21dd28a65)
[![codecov](https://codecov.io/gh/WarFox/trade-instructions/branch/master/graph/badge.svg)](https://codecov.io/gh/WarFox/trade-instructions)

This applications, give aggregate information about trading instructions that has come through
Input is assumed to be in memory.



A sample trading instruction

| Entity | Buy/Sell | AgreedFx | Currency | InstructionDate | SettlementDate | Units | Price per unit |
|--------|----------|----------|----------|-----------------|----------------|-------|----------------|
| foo    | B        |     0.50 | SGD      | 01 Jan 2017     | 02 Jan 2017    |   200 |         100.25 |
| foo    | S        |     0.50 | SGD      | 01 Jan 2017     | 02 Jan 2017    |   100 |         100.25 |
| bar    | S        |     0.22 | AED      | 02 Jan 2017     | 07 Jan 2017    |   450 |          150.5 |
| bar    | B        |     0.22 | AED      | 03 Jan 2017     | 08 Jan 2017    |   450 |          150.5 |
| bar    | S        |     1.25 | GBP      | 03 Jan 2017     | 07 Jan 2017    |   500 |          150.5 |
| grunt  | S        |     0.22 | AED      | 05 Jan 2017     | 09 Jan 2017    |   450 |          172.5 |
| bar    | S        |     0.22 | AED      | 05 Jan 2017     | 06 Jan 2017    |   450 |          150.5 |
| bar    | B        |     0.22 | AED      | 05 Jan 2017     | 07 Jan 2017    |   450 |          150.5 |
| thud   | S        |     1.07 | EUR      | 05 Jan 2017     | 10 Jan 2017    |   450 |         255.75 |
| bar    | S        |     0.24 | AED      | 06 Jan 2017     | 07 Jan 2017    |   450 |          150.5 |
| foo    | S        |     0.23 | AED      | 09 Jan 2017     | 10 Jan 2017    |   450 |          101.5 |
| bar    | B        |     0.22 | AED      | 09 Jan 2017     | 11 Jan 2017    |   450 |          150.5 |
| thud   | S        |     0.22 | AED      | 05 Jan 2017     | 12 Jan 2017    |   450 |          256.5 |
| bar    | B        |     0.22 | AED      | 05 Jan 2017     | 13 Jan 2017    |   450 |          150.5 |
| foo    | S        |     0.22 | AED      | 05 Jan 2017     | 13 Jan 2017    |   450 |         102.75 |

Sample output

Incoming settlement (Selling)
-----------------------------
2017-01-02 : $5,012.50
2017-01-08 : $46,053.00
2017-01-09 : $111,140.00
2017-01-10 : $133,648.88
2017-01-12 : $25,393.50
2017-01-15 : $10,172.25
============================
Outgoing settlement (Buying)
----------------------------
2017-01-02 : $10,025.00
2017-01-08 : $29,799.00
2017-01-11 : $14,899.50
2017-01-15 : $14,899.50
============================
Incoming rank (Selling)
-----------------------
thud
bar
foo
grunt
============================
Outgoing rank (Buying)
----------------------
bar
foo


## Test
```
./gradlew test jacocoTestReport
```

## Build
```
./gradlew build
```

## Run
```
./gradlew run
```

or

```
java -jar build/libs/trade-instructions.jar
```

## Next features

* Read trading instructions from a datasource
