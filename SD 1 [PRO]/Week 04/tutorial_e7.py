import random

count = 0

for i in range(100):
    dice1 = random.randint(1, 6)
    dice2 = random.randint(1, 6)
    if dice1 == dice2:
        count += 1

print(f"Out of 100 you rolled {count} doubles")
