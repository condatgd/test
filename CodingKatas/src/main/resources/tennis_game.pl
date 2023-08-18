transition('WIN',X, 'WIN',X) :- !.
transition(X, 'WIN', X,'WIN') :- !.

transition('LOVE',X, 'FIFTEEN',X).
transition('FIFTEEN',X, 'THIRTY',X).
transition('THIRTY','FOURTY', 'DEUCE','DEUCE'). % FOURTY-FOURTY gibt es nicht
transition('THIRTY',X, 'FOURTY',X).
transition('FOURTY',X, 'WIN',X).
transition('DEUCE','DEUCE', 'ADV','-').
transition('ADV',X, 'WIN',X).
transition(_,'ADV', 'DEUCE','DEUCE').

say('ADV',_, 'ADV1').
say(_,'ADV', 'ADV2').
say('DEUCE','DEUCE', 'DEUCE').
say('WIN',_, 'WIN1').
say(_,'WIN', 'WIN2').
say(X,Y, SAY) :- atomic_list_concat([X,'-',Y], SAY).


initialState(('LOVE','LOVE')).

game([], (State1,State2), Say) :- say(State1,State2, Say).

game([1|R], (State1,State2), FinalState) :-
    transition(State1,State2, NewState1,NewState2),!,
    game(R, (NewState1,NewState2), FinalState).

game([2|R], (State1,State2), FinalState) :-
    transition(State2,State1, NewState2,NewState1),!,
    game(R, (NewState1,NewState2), FinalState).

test:-
    initialState(InitState),
    game([1,2], InitState, 'FIFTEEN-FIFTEEN'),
    game([1,2,2], InitState, 'FIFTEEN-THIRTY'),
    game([1,2,2,2], InitState, 'FIFTEEN-FOURTY'),
    game([1,2,2,2,2], InitState, 'WIN2'),
    game([1,2,2,2,2,1], InitState, 'WIN2'),
    game([1,2,2,2,2,1,2], InitState, 'WIN2'),

    game([1,2,2,2,1,1], InitState, 'DEUCE'),
    game([1,2,2,2,1,1,2], InitState, 'ADV2'),
    game([1,2,2,2,1,1,2,1], InitState, 'DEUCE'),
    game([1,2,2,2,1,1,2,1,1], InitState, 'ADV1'),
    game([1,2,2,2,1,1,2,1,1,1], InitState, 'WIN1').
