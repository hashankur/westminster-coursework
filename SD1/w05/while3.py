#!/usr/bin/env python3

# Print even numbers between 1 to 100 using an endless loop

count = 0

while True:  # Endless or infinite loop
    print(count)
    count += 2
    if count > 100:
        break  # Terminates the loop when the condition count > 100 becomes True
