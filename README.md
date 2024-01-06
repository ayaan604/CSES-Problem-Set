# CSES-Problem-Set
My attempt at solving the CSES DSA problems. And some leetcode problems too.

Removal Game : 
I struggled with this problem for a couple of days before getting the KEY insight. Player 1 and player 2 will always be working with unique combination of indices! Hence implying that for dp to work, I need to consider 2 steps ahead in each move instead of 1, purely from the perspective of player 1. Subarray size 2 and 3 are the base cases. If subarray size == 2, then only consider the max value. If size == 3, then choose one of the either corner values and ADD it to the minimum of the remaining (because player 2 will take the maximum from the remaining)

L2939:
The key to understanding this problem is, that at the end of the day its just multiplication of two numbers. And since we want the maximum product, its in our interest to maximise the value of (a * x) and (b * x). However, we can't win it all, at some point, 'a' would have to reduce to make way for 'b' to increase or vice-versa. More on this in a while.
Basic idea is that we iterate from 0 to n-1, and check every bit of a and b.
- if they both have set bit, then we will do nothing
- if they both have unset bit, then we will add 2^(bit position) to both a and b. Because the "x" that we are creating to multiply with both a and b
is totally in our hands. We will make that x with set bit at that position.

However if they both have different bit values at a position then we have to CHOOSE which bit should be set, the one of 'a' or 'b'. How will we do that? Comes back to earlier point of increasing/decreasing a or b. 
We will add the indexes with such cases in an array. Then in the case where a > b, we will make the highest index of that array bit set in 'b'. Re-read the sentence if you didn't understand. If you still haven't understood, its probably because I haven't written it in the best manner. Alright. But why? Because 14*6 is greater than 15*5. Because 99*2 is greater than 100*1. Because... for a fixed sum of two numbers, the maximum product is achieved when those two numbers are as close as possible. 
But how do we make the two numbers as close as possible? 
Proof by contradiction. Suppose a > b, if we add the highest index set bit to 'a', then we are only increasing their gap because the sum of all other bits would be less than the highest bit. So we will iterate the indexes array and add the highest bit to the one that is smaller between a and b. Once we have final values of a and b, we just multiply it.