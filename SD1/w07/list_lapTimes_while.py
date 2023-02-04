# Author: Hashan
# Date: 08 Nov 2022

# Lap time recorder enhanced (list method, while loop)

lapTimes = []
time = None
lap = 1

while True:
    time = input(f"Enter lap time {lap} ('x' to end): ")
    if time == "x":
        break
    lapTimes.append(float(time))
    lap += 1

print("Fastest:", min(lapTimes))
print("Slowest:", max(lapTimes))
print("Average:", sum(lapTimes) / len(lapTimes))
