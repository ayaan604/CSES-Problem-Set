# CSES-Problem-Set
My attempt at solving the CSES DSA problems.

Removal Game : 
I struggled with this problem for a couple of days before getting the KEY insight. Player 1 and player 2 will always be working with unique combination of indices! Hence implying that for dp to work, I need to consider 2 steps ahead in each move instead of 1, purely from the perspective of player 1. Subarray size 2 and 3 are the base cases. If subarray size == 2, then only consider the max value. If size == 3, then choose one of the either corner values and ADD it to the minimum of the remaining (because player 2 will take the maximum from the remaining)