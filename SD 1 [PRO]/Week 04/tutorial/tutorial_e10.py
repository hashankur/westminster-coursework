import random


hidden = random.randint(1, 20)
guessesTaken = 0

while guessesTaken < 5:
    guess = int(input("Guess a number from 1 to 20: "))
    guessesTaken += 1

    if guess == hidden:
        break
    elif guess < hidden:
        print("Your guess is too low")
    else:
        print("You guess is too high")

if guess == hidden:
    print(f"You got it in {guessesTaken} guesses")
else:
    print(f"Not guessed. The correct answer was: {hidden}")
