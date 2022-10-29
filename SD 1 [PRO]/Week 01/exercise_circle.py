# Author Name: Hashan
# Date: 11th Oct 2022

# Calculate area and circumference of a circle by getting radius as input

# Design
"""
# Pseudo code

input radius
area = PI * radius * radius
circumference = PI * radius * 2
print area
print circumference
"""

# Code
import math
radius = float(input("Enter radius: "))
area = math.pi * radius ** 2    # Exponent
circF = math.pi * radius * 2
print("Area =", round(area, 3))
print("Circumference =", round(circF))
