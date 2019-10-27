# sNEK 
_A statically typed , s-expression based meta language_

`snek sourceFile.snek`

### Purpose
_"JavaScript was written in ten days, I'll write sNEK in two."_

I wrote sNEK in a matter of around twelve hours to see how much complexity/features I can put into a language in short a short amount of time.

Seeing that my biggest obstacles in my past PL projects was grammar, I decided that the quickest was to overcome it was by implmenting S-expressions. The rest of the lanuage kind of just *popped* in after that.

### Features
_"I just gotta finish this so it looks I actually did something for SD Hacks"_

sNEK combines allows for both imperative and functional programming.

In sNEK, one can declare a a variable scope with multiple variables and multiple statements to execute within that scope. The same goes for while-loops which can also house a sequential list of statements.

A major feature of sNEK is its static-type checking as all variables and functions must list their respective types. 


### Requirements
sNEK is written in Java 1.8 and depends on a modified version of Grammatica 1.6 for its grammar and parsing. 
