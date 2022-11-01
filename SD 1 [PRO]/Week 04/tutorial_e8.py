i = 1

for x in range(1, 4):  # print 3 rows
    i += 1
    for y in range(1, i):  # (i + 1) asterisks a row
        print("*", end=" ")
    print()
