secret = "water"
turns = 6
guesses = []

print("Let's play Guess the Word")
print(f"You have {turns} turns to guess the word!")
print(f"{len(secret) *  ' _ '}")

while turns > 0:
    guess = input("\nGuess a letter: ").lower()

    if len(guess) > 1:
        if guess == secret:
            break
        else:
            guesses.append(guess[0])
    else:
        guesses.append(guess)
    missed = 0

    for letter in secret:
        if letter in guesses:
            print("", letter, "", end="")
        else:
            print(" _ ", end="")
            missed -= 1

    if missed == 0:
        break

    if guess not in secret:
        turns -= 1
        if turns > 1:
            print(f"\n{turns} turns left...")
        if turns == 1:
            print(f"\n{turns} turn left...")

print("\nEnd of Game")
