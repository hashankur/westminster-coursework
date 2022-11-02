#!/usr/bin/env python3

# Author: Hashan
# Date: 01 Nov 2022

# Gues number between 1 to 100

# Pseudo code
"""
number = input random number
guess_counter = 0
guess = None
while guess != number
    do
    input number
    if guess > number then
        print Too high
    else if guess < number then
        print Too low
    else
        print Congrats
"""

import random

number = random.randint(1, 100)
counter = 0
guess = None

while guess != number:
    guess = int(input("Enter your guess: "))
    counter += 1
    if guess > number:
        print("Too high")
    elif guess < number:
        print("Too low")
    else:
        print(f"{number} is correct, you win!")
        print(f"You got it in {counter} guesses.")
