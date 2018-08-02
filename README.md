# FindCoin（一般性假币称重鉴别问题）
- 问题重述

设有<img src="https://latex.codecogs.com/gif.latex?$n$" title="$n$" />枚硬币，其中仅有一枚假币，在已知或未知假币与真币之间
重量关系两种情况下，通过无砝码天平称重的方法鉴别假币，求所需的最少称重次数。
	
- 问题分析

此问题是经典的信息论算法问题，许多大公司都曾以此作为面试、笔试题来考核员工。从信息论角度看，“有
<img src="https://latex.codecogs.com/gif.latex?$n$" title="$n$" />枚硬币，其中仅有一枚假币”发生概率为
<img src="https://latex.codecogs.com/gif.latex?P&space;=&space;\frac{1}{n}" title="P = \frac{1}{n}" />，“假币与真币之
间重量关系未知”发生概率为
<img src="https://latex.codecogs.com/gif.latex?P&space;=&space;\frac{1}{2}" title="P = \frac{1}{2}" />，为了确定哪一枚
假币，
即要消除上述事件的联合不确定性。又因为两事件独立，因此有
<img src="https://latex.codecogs.com/gif.latex?${I_1}&space;=&space;\log&space;n&space;&plus;&space;\log&space;2&space;=&space;\log&space;2n$" title="${I_1} = \log n + \log 2 = \log 2n$" />
比特；用天平称重，有三种可能：平衡、左倾、右倾，三者等概率，为
<img src="https://latex.codecogs.com/gif.latex?P&space;=&space;\frac{1}{3}" title="P = \frac{1}{3}" />，因此天平称重一次消
除的不确定性为<img src="https://latex.codecogs.com/gif.latex?${I_2}&space;=&space;\log&space;3$" title="${I_2} = \log 3$" />
比特，所以称重次数至少为<img src="https://latex.codecogs.com/gif.latex?\frac{{{I_1}}}{{{I_2}}}&space;=&space;\frac{{\log&space;2n}}{{\log&space;3}}" title="\frac{{{I_1}}}{{{I_2}}} = \frac{{\log 2n}}{{\log 3}}" />次。
