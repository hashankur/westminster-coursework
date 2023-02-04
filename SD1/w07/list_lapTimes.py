# Author: Hashan
# Date: 08 Nov 2022

# Lap time recorder (list method)

lapTimes = []

laps = int(input("Enter amount of laps: "))

for lap in range(laps):
    time = int(input(f"Enter lap time {lap + 1} of {laps}: "))
    lapTimes.append(time)

print("Fastest:", min(lapTimes))
print("Slowest:", max(lapTimes))
print("Average:", sum(lapTimes) / len(lapTimes))
