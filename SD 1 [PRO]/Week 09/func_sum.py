# Author: Hashan
# Date: 22 Nov 2022

# Function to return sum of two numbers

# Design
"""

"""

# Code
def sumTwoNumbers(num1, num2):
    return num1 + num2


def validation(message, error="Invalid Input. Retry...", minValue=None, maxValue=None):
    while True:
        try:
            number = float(input(message))
        except:
            print(error)
            continue
        else:
            if minValue is not None and number < minValue:
                print(f"{number} cannot be lower than {minValue}")
                continue

            if maxValue is not None and number > maxValue:
                print(f"{number} cannot be lower than {minValue}")
                continue

            return number


print("Enter numbers to calculate sum")

n1 = validation("Enter first number: ", minValue=0, maxValue=100)
n2 = validation("Enter first number: ", "Invalid number", 0, 100)
print(f"Sum of {n1} and {n2} is: {sumTwoNumbers(n1, n2)}")
