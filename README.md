LeapSign
========
Matt Bu, Alvin Leung, Jia Sen Wu, Brandon Ip  
Made for HackRPI Fall 2015, and won the Data Science category, as well as a runner up for the Humanitarian category.

### Inspiration
For those who are born deaf or have speech impairments, communicating and socializing with friends, family, or anyone around them can be challenging. Not only do these people have to learn sign language, but so do those who communicate with them frequently, making life challenging for both parties. Therefore, why not develop a system to convert their hand signs to speech?

### What it does
Our software utilizes the Leap Motion sensor to calculate precise vector positions of various points on the hand and sends that data to a machine learning server using Microsoft Azure. The machine learns the patterns behind certain words and characters, and therefore can determine the literal meaning of gestures.

### How I built it
We utilized a Leap Motion for hand sensing, Microsoft Azure for machine learning, and Java to put everything together.

### Challenges I ran into
Machine learning was a crucial challenge to overcome, as it was hard for the machine differentiate minute differences between very similar gestures.

### Accomplishments that I'm proud of
Being able to interface the Leap Motion with machine learning was a great accomplishment and has led to a very useful product for those who are hearing-impaired.

### What I learned
Azure API, Leap Motion SDK

### What's next for LeapSign
Show the world what it can do!

### Built With
java, leap-motion, azure
