#!/usr/bin/env python3

# Author: Hashan
# Date: 01 Nov 2022

# Calculate stats about lap times

total = 0
fastest = 9999
slowest = 0

laps = int(input("Enter number of laps: "))
print("Enter lap times:")

for i in range(laps):
    lapTime = float(input(f"{i + 1}> "))
    if lapTime < fastest:
        fastest = lapTime
    if lapTime > slowest:
        slowest = lapTime

    total += lapTime

print(f"Fastest: {fastest}")
print(f"Slowest: {slowest}")
print(f"Total: {round((total / laps), 2)}")
