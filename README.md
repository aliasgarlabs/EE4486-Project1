# EE4486-Project1
## Synopsis

This project is made for EE4483 course at Nanyang Technological University. This project is implemented in Java 7.

## Problem Statement

Implement TWO different search algorithms to find 𝑥3/5 of any real number 𝑥. Note 𝑥 can be negative number too.

## Approach number 1

In this approach, we first find the cube of the given real number x. After that, we find the 5th root of the result using binary search. 

## Approach number 2

In this approach, we use the Log property to find the 3/5th power of the real number x.   We first find log of x and multiply it by the exponent power.  Then we find the log inverse of the product obtained from above multiplication using the Inverse Log table. 

## Installation

Java 1.7+ required. 

## Input

The first line of the input gives the number of test cases, T. T test cases follow. Each test case consists of one line with three integers: x, a and b. x is the real number. a and b are the numerator and the denominator of the power respectively.

## Output

For each test case, output one line containing Case #i: y z, where i is the test case number (starting from 1), y and z are the approach 1 & 2 results respectively.

## Sample 

**Input**
```
4
2 3 5
-5 3 5 
4.393 3 5 
-3.219 3 5
```

**Output**
```
Case #        Algorithm 1     Algorithm 2     Actual Answer
Case #1:      1.5157108       1.516           1.5157167
Case #2:      -2.6265202      -2.626          -2.6265278
Case #3:      -2.4302666      -2.43           -2.4302824
Case #4:      -2.0166233      -2.017          -2.0166597
```

