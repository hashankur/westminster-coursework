#!/usr/bin/env python3

# Author: Hashan
# Date: 25 Oct 2022

# Generate random number until 0 is generated

import random

num = None
count = 1

while num != 0:
    num = random.randint(0, 10)
    if num == 7:
        print("Lucky 7!")
    else:
        print(f"Number {count} was {num}")
    count += 1
