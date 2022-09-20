

enum Color {
    RED,BLACK
}

class RouletteNumber {
    Integer number
    Color color


    @Override
    public String toString() {
        def DEFAULT = "${(char)27}[39;49"+"m"
        def printcolors = [
                (Color.RED): "${(char)27}[39;41"+"m",
                (Color.BLACK): "${(char)27}[39;40"+"m"
        ]
        def outputColor = printcolors[color] ?: DEFAULT
        if (number == 0) {
            outputColor = "${(char)27}[39;42"+"m"
        }
        return "${outputColor}${number}$DEFAULT";
    }
}
def rouletteNumbers = [
        new RouletteNumber(color: null, number: 0),
        new RouletteNumber(color: Color.RED, number: 1),
        new RouletteNumber(color: Color.BLACK, number: 2),
        new RouletteNumber(color: Color.RED, number: 3),
        new RouletteNumber(color: Color.BLACK, number: 4),
        new RouletteNumber(color: Color.RED, number: 5),
        new RouletteNumber(color: Color.BLACK, number: 6),
        new RouletteNumber(color: Color.RED, number: 7),
        new RouletteNumber(color: Color.BLACK, number: 8),
        new RouletteNumber(color: Color.RED, number: 9),
        new RouletteNumber(color: Color.BLACK, number: 10),
        new RouletteNumber(color: Color.BLACK, number: 11),
        new RouletteNumber(color: Color.RED, number: 12),
        new RouletteNumber(color: Color.BLACK, number: 13),
        new RouletteNumber(color: Color.RED, number: 14),
        new RouletteNumber(color: Color.BLACK, number: 15),
        new RouletteNumber(color: Color.RED, number: 16),
        new RouletteNumber(color: Color.BLACK, number: 17),
        new RouletteNumber(color: Color.RED, number: 18),
        new RouletteNumber(color: Color.RED, number: 19),
        new RouletteNumber(color: Color.BLACK, number: 20),
        new RouletteNumber(color: Color.RED, number: 21),
        new RouletteNumber(color: Color.BLACK, number: 22),
        new RouletteNumber(color: Color.RED, number: 23),
        new RouletteNumber(color: Color.BLACK, number: 24),
        new RouletteNumber(color: Color.RED, number: 25),
        new RouletteNumber(color: Color.BLACK, number: 26),
        new RouletteNumber(color: Color.RED, number: 27),
        new RouletteNumber(color: Color.BLACK, number: 28),
        new RouletteNumber(color: Color.BLACK, number: 29),
        new RouletteNumber(color: Color.RED, number: 30),
        new RouletteNumber(color: Color.BLACK, number: 31),
        new RouletteNumber(color: Color.RED, number: 32),
        new RouletteNumber(color: Color.BLACK, number: 33),
        new RouletteNumber(color: Color.RED, number: 34),
        new RouletteNumber(color: Color.BLACK, number: 35),
        new RouletteNumber(color: Color.RED, number: 36),
]

def highestBet = 1
def longestLosingStreak = 0
def currentLosingStreak = 0

def rand = new Random()

def MAX_BET = 10000
bankroll = 10000

def highestBankroll = bankroll

bet = 1 // add your base bet size
def pick = Color.RED // pick a color
def rounds = 1_000_000 // amount of rounds simulated
for (n in 1..rounds){
    highestBet = Math.max(highestBet, bet) // calculate highest bet for stats
    def  pickedNumber = rouletteNumbers[rand.nextInt(37)]
    if (pick == pickedNumber.color) { // win
        bankroll  += bet // add the bet to bankroll
        bet = 1 // reset bet to base
        currentLosingStreak = 0 // reset losing streak
    } else { // lose
        bankroll -= bet //deduct the bet from bankroll
        bet = bet * 2 // double next bet
        currentLosingStreak += 1 // keep track of streak
        longestLosingStreak = Math.max(longestLosingStreak, currentLosingStreak) // calculate highest losing streak
    }

    highestBankroll = Math.max(highestBankroll, bankroll) // calculate highest balance
    if (bet >= MAX_BET) { // we can not bet more than the table limit
        bet = MAX_BET
    }
    if (bet > bankroll) { // we can not bet more than we have
        bet = bankroll
    }

    println("${pickedNumber} - Bankroll: ${bankroll} - Next: ${bet}")

    if (bankroll <= 1 ) {
        println("YOU ARE BROKE!!!! ($n rounds survived) highest Bet was ${highestBet} CHF, longest losing streak was ${longestLosingStreak}, your highest balance was ${highestBankroll}")
        return
    }

}
println("After ${rounds} you have ${bankroll} CHF, highest Bet was ${highestBet} CHF, longest losing streak was ${longestLosingStreak}, your highest balance was ${highestBankroll}")
