print("Let's practice everything.")
print('You\'d need to know \'bout escapes with \\ that do:')
print('\n new lines and \t tabs')

poem = """
为伊消得人憔悴
"""

print("-------")
print(poem)
print("-------")

five = 10 - 2 + 3 - 6
print(f"This should be :{five}")

def secred_formula(started):
    jelly_beans = started * 500
    jars = jelly_beans / 10000
    crates = jars / 100
    return jelly_beans, jars, crates

start_point = 10000

beans, jars, crates = secred_formula(start_point)

print("With a starting point of: {}".format(start_point))

print(f"We'd have {beans} beans, {jars} jars, {crates} crates.")

start_point = start_point / 10

print("We can also do that this way:")
formula = secred_formula(start_point)

# this is an easy way to apply a list to a format string
print("We'd have {} beans, {} jars, {} crates.".format(*formula))
