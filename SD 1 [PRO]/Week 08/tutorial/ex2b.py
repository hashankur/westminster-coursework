def isPositive(value):
    if value > 0:
        return True
    else:
        return False

num = int(input("Input a number: "))

print(isPositive(num))
