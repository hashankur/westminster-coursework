#!/usr/bin/env python3

# Author: Hashan
# Date: 25 Oct 2022

# Generate random number 0 to 10 (10 times)

import random

count = 0

while count < 10:
    print(random.randint(0, 10))
    count += 1

print("\n")

# count known; 10 times
for count in range(10):
    print(random.randint(0, 10))
