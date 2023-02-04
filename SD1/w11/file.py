# Get file name as input. Open a file and find how many words in the file. Print.
# Ensure errors are handles and input is taked as long as a non valid input is given

# Design
"""
get the file name as input
open the file
read from the efile
replace the newline characters with space
split using space which will give you list of all thw words
using len() function find the nimber of item in the list
print the no of items which equals to no of words
"""

while True:
    filename = input("Enter the file name: ")
    file = None
    try:
        file = open(filename)
    except:
        print("Invalid filename, Retry...")
        continue
    else:
        content = file.read().strip().replace("\n", " ")
        words = content.split(" ")
        print(words)
        wordCount = len(words)
        print(f"No of words in the file: {wordCount}")
        break
    finally:
        if file is not None:
            file.close()
