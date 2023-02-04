# Author Name: Hashan
# Date: 11th Oct 2022

# Calculate interest of a loan

# Design
"""
# Pseudo code

input rate, time, amount
interest = amount * (rate / 100) * time
print interest
"""

# Code
rate = float(input("Enter interest rate: "))
amount = float(input("Enter loan amount: "))
time = int(input("Enter time period: "))

interest = amount * (rate / 100) * time
print("Interest for", amount, "is", round(interest, 2))
