# Author: Hashan
# Date: 15 Nov 2022


def inToCm(inch, r=2):
    cm = inch * 2.54
    return round(cm, r)


inch = float(input("Enter inches: "))
getRound = int(input("Round value: "))
print(f"{inch}: {inToCm(inch, getRound)} centimeters")
