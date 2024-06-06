grammar SQL;
@header{
package parser;
}

query:
    EOF
    | (simpleStatement) (SEMICOLON_SYMBOL EOF? | EOF)
;

simpleStatement:
      createStatement
    | selectStatement
    | insertStatement
    | updateStatement
    | deleteStatement
    ;

createStatement:
    createTable| createIndex
    ;


selectStatement:
    SELECT ASTERISK FROM table (WHERE selectCondition)
   ;

insertStatement:
    INSERT INTO table LB column (COMMA column)* RB  VALUES LB valueList RB
    ;

updateStatement:
    UPDATE table SET columnsToBeUpdated (WHERE updateCondition)
    ;

deleteStatement:
    DELETE FROM table (WHERE deleteCondition)
    ;

createTable:
    CREATE TABLE table LB columnDefinition (COMMA columnDefinition)* (COMMA PRIMARY KEY LB IDENTIFIER RB)  RB
    ;

createIndex:
    CREATE INDEX index ON table LB column RB (USINGBTREE)?
    ;

columnDefinition:
    IDENTIFIER dataType
    ;

columnsToBeUpdated:
    column EQ value (COMMA column EQ value)*
    ;

dataType:
    INTEGER
    | DOUBLE
    | STRING
    | CHAR LB INTEGER RB
    | INT
    ;


valueList: value (COMMA value)*;

table: IDENTIFIER;

selectCondition:
    column operator value (LINKAGE column operator value)*
   ;
updateCondition:
    column  EQ  value
   ;
deleteCondition:
    column  EQ  value (AND column  EQ  value)*
    ;
column: IDENTIFIER;


value: INTEGER_VALUE| DOUBLE_VALUE |STRING_VALUE;

index: IDENTIFIER;

operator : EQ | NEQ | LT | LTE | GT | GTE;

EQ  : '=';
NEQ : '!=';
LT  : '<';
LTE : '<=';
GT  : '>';
GTE : '>=';

PLUS: '+';
MINUS: '-';
ASTERISK: '*';
SEMICOLON_SYMBOL: ';';
COMMA: ',';
SINGLE_QUOTE: '\'';

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Za-z]
    ;

fragment EXPONENT
    : 'E' [+-]? DIGIT+
    ;


INTEGER_VALUE
    : DIGIT+
    ;

DOUBLE_VALUE
    : DIGIT+ '.' DIGIT*
    ;
LINKAGE : AND | OR | XOR;

AND: A N D;
CHAR: C H A R;
CREATE: C R E A T E;
DELETE: D E L E T E;
DOUBLE: D O U B L E;
FROM: F R O M;
INDEX: I N D E X;
INSERT: I N S E R T;
INT: I N T;
INTEGER: I N T E G E R;
INTO: I N T O;
KEY: K E Y;
LB: '(';
ON: O N;
OR : O R;
PRIMARY: P R I M A R Y;
RB: ')';
SELECT: S E L E C T;
SET: S E T;
STRING: S T R I N G;
TABLE: T A B L E;
USINGBTREE: U S I N G  B '_' T R E E ;
UPDATE: U P D A T E;
VALUES: V A L U E S;
WHERE: W H E R E;
XOR: X O R;

IDENTIFIER
    : (LETTER | '_') (LETTER | DIGIT | '_' | '@' | ':')*
    ;

STRING_VALUE
    : '\'' ( ~'\'' | '\'\'' )* '\''
    ;

UNICODE_STRING
    : 'U&\'' ( ~'\'' | '\'\'' )* '\''
    ;

SIMPLE_COMMENT
    : '--' ~[\r\n]* '\r'? '\n'? -> skip
    ;

BRACKETED_COMMENT
    : '/*' .*? '*/' -> skip
    ;

WS
    : [ \r\n\t]+ -> skip
    ;