import os
import sys
import random
import argparse


def row(i: int, number_of_sellers: int) -> list:
    time = i
    
    t = time / 1000
    day = int((t % 30) + 1)
    month = int(((t / 30) % 12) + 1)
    year = int((t / 30 / 12) + 2016)
    amount = random.randint(10, 10000)
    buyer = random.randint(0, 50)
    seller = random.randint(0, number_of_sellers)
    credit_card = random.randint(0, 100000)
    latitude = random.randint(-500, 500)
    longitude = random.randint(-500, 500)
    return [time, year, month, day, amount, buyer, seller, credit_card, latitude, longitude]

def row_as_str(r: list) -> str:
    return ",".join([ str(e) for e in r ]) + "\n"

if __name__ == '__main__':
    ap = argparse.ArgumentParser()
    ap.add_argument("--number_of_lines", "-l", type=int,
                    help="Number of lines for dataset", required=True)
    ap.add_argument("--output", "-out", type=str,
                    help="Output path of dataset", required=True)
    args = ap.parse_args()

    print(f"Executing timeseries_generator.py for {args.number_of_lines} transactions")
    number_of_sellers = int(args.number_of_lines / 1000) + 1

    with open(args.output, "w") as f:
        for i in range(args.number_of_lines):
            f.write(row_as_str(row(i, number_of_sellers)))

    print(f"Finished. Output path: {args.output}")
