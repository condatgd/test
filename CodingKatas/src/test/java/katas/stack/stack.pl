/*
            isempty(newstack) = true
            isempty(push(x, l)) = false
            top(push(x, l)) = x
            pop(push(x, l)) = l
            push(top(s),pop(s)) = s
            pop(emptyStack()) = ERROR
            top(emptyStack()) = ERROR
*/

isempty(newstack).
top(stack(X, _), X).
pop(stack(_, L), L).
push(X,S, stack(X,S)).

test:-
    isempty(newstack),
    not(isempty(stack(x, _))),
    top(stack(x, _),x),
    pop(stack(_, L), L),
    push(X,S, stack(X,S)),
    not(pop(newstack, _)),
    not(top(newstack, _)).