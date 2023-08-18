rule('LOVE',X, 'FIFTEEN',X).
rule('FIFTEEN',X, 'THIRTY',X).
rule('THIRTY','FOURTY', 'DEUCE','DEUCE').
rule('THIRTY',X, 'FOURTY',X).
rule('FOURTY',X, 'WIN',X).
rule('DEUCE','DEUCE', 'ADV','DEUCE').
rule('ADV',X, 'WIN',X).
rule(X,'ADV', 'DEUCE','DEUCE').

say('ADV',_, 'ADV1').
say(_,'ADV', 'ADV2').
say('DEUCE','DEUCE', 'DEUCE').
say('WIN',_, 'WIN1').
say(_,'WIN', 'WIN2').
say(X,Y, SAY) :- atomic_list_concat([X,'-',Y], SAY).