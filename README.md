# CoinCounter
CoinCounter is a Java desktop application that takes in an image of coins and uses the OpenCV library to determine each coin's denomination
and find the total dollar value of the coins in the image. I helped built it in a team of 4 for NwHacks 2021. 

## How it's done
The image is preprocessed using Greyscale, Gausian Blur, and Conny Edge detection schemes before being given to the OpenCV HoughCircles algorithm.
the HoughCircles algorithm returns a list of the circles in the image along with their radiuses and positions. The circles radiuses are then passed 
to our Denomination module to determine what coins are what and their respective values using the specific size ratios of Canadian currency.

Check out our Devpost page [here](https://devpost.com/software/coincointer)
