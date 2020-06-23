from sys import argv

script, user_name = argv

prompt = '>'

print(f"Hi {user_name}, I'am the {script} script.")

print("I'd like to ask you a few questions")

print(f"Do you like me {user_name}")

likes = input(prompt)

print("Where do you live?")

lives = input(prompt)

print("What kind of computer do you have?")

computer = input(prompt)

print(f"""
Alright, so you said you {likes} about liking me.
You live in {lives}. Not sure where that is.
And you have a {computer} computer. Nice.
""")