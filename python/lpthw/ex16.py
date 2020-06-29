from sys import argv

script, filename = argv

print(f"We're going to erase {filename}")
print("If you don't want that, his CTRL-C (^C).")
print("If you want that, hit return")

input("?")

print("Opening the file ... ")
target = open(filename, 'w')

print("Truncating the file. Goodbye!")
target.truncate()

print("Now I'am goint to ask you for three lines.")

line1 = input("line 1: ")
line2 = input("line 2: ")
line3 = input("line 3: ")

print("I'am going to write these to file")

formatter = "{}\n{}\n{}\n"

target.write(formatter.format(line1,line2,line3))

print("And finally, we close it")
target.close()