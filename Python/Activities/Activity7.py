numbers = list(input("Enter a sequence of comma-space separated values: ").split(", "))
sum = 0

for number in numbers:
  sum += int(number)

print(sum)