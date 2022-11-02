#!/usr/bin/env python3

# Author: Hashan
# Date: 01 Nov 2022

# Guess number between 1 to 100

# Psuedo code
"""
counter = 0
number = input random number
while true
    input number from user
    increase counter
    if guess > number
        print "Too high"
    else if guess < number
        print "Too low"
    else
        print "Congrats you won"
        break
"""

import random

rand = random.randint(1, 100)  # Generate number between 1 to 100
counter = 0

while True:
    guess = int(input("Enter your guess: "))
    counter += 1

    if guess > rand:
        print("Too high!")
    elif guess < rand:
        print("Too low!")
    else:
        print(f"{rand} is correct, you win!")
        print(f"You got it in {counter} guesses.")
        break
