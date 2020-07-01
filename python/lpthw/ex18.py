
def print_two(*args):
    arg1, arg2 = args
    print(f"arg1: {arg1}, arg2: {arg2}")

def print_two_again(arg1, arg2):
    print(f"arg1: {arg1}, arg2:{arg2}")

def print_one(arg1):
    print(f"arg1: {arg1}")

def print_none():
    print("I got nothin.")

print_two("guodong", "hu")
print_two_again("tianwa", "wang")
print_one("First")
print_none()

# python3 ex18.py 
# arg1: guodong, arg2: hu
# arg1: tianwa, arg2:wang
# arg1: First
# I got nothin.