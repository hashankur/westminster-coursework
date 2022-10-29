import random

hidden = random.randint(1, 20)

guess = int(input("Guess a number from 1 to 20: "))

while guess != hidden:
    print(f"{guess} is not correct")
    print("Guess is too high" if guess > hidden else "Guess is too low")
    guess = int(input("Guess again: "))
print(f"{guess} was correct")
