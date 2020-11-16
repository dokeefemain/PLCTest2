//java while
//ebnf
//<while_loop> --> 'while' '(' <boolexpr> ')' '{' <stmt>';'{<stmt>';'} '}'
public class whileloop {
	lex();
	if(nextToken!=while_code) {
		error();
	}else {
		lex();
		if(nextToken!=LEFT_PAREN) {
			error()
		}else {
			lex();
			boolexpr();
			if(nextToken!=RIGHT_PAREN) {
				error();
			}else {
				lex();
				if(nextToken!=LEFT_BRACKET) {
					error();
				}else {
					lex();
					stmt();
					if(nextToken!=SEMICOLON) {
						error();
					}else{
                        lex();
                        if(nextToken!=RIGHT_BRACKET){
                            error();
                        }
                    }
				}
			}
		}
	}
}


//java if stmt
//ebnf
//<if_stmt> --> 'if' '(' <boolexpr> ')' <stmt> ['else' <stmt>]

public class if {
	lex();
	if(nextToken!=if_code) {
		error();
	}else {
		lex();
		if(nextToken!=LEFT_PAREN) {
			error()
		}else {
			lex();
			boolexpr();
			if(nextToken!=RIGHT_PAREN) {
				error();
			}else {
				lex();
				if(nextToken!=LEFT_BRACKET) {
					error();
				}else {
					lex();
					stmt();
					if(nextToken!=else_code) {
						lex();
                        stmt();
					}
				}
			}
		}
	}
}

//Mathematical expression
//EBNF
//<S> --> <A>{('+'|'-')<A>}
//<A> --> <B>{('/'|'*'|'%')<B>}
//<B> --> ’1’ | ’2’ | ’3’ | ’4’ | ’5’ | ’6’ | ’7’ | ’8’ | ’9’ | <B>

public class Mathematical(){
	lex();
	if(nextToken!=PLUS_CODE || nextToken!=MINUS_CODE){
		error();
	}
}
public class A(){
	lex();
	if(nextToken!=DIVISION_CODE || nextToken!=MUL_CODE || nextToken!=MOD_CODE){
		Mathematical();
	}
}
public class B(){
	if(nextToken!=NUM_CODE){
			error();
	}
	else{
		lex();
		while(nextToken!=ENDOFEXPR_CODE){
			A();
			lex();
			if(nextToken!=NUM_CODE){
				error();
			}
		}
	}
	}
}

//Mathematical Assignment Statement
//EBNF var is supposed to represent a variable name
//<S> --> <var> '=' <A>
//<A> --> <B>{('+'|'-')<B>}
//<B> --> <C>{('/'|'*'|'%')<C>}
//<D> --> ’1’ | ’2’ | ’3’ | ’4’ | ’5’ | ’6’ | ’7’ | ’8’ | ’9’ | <D> | <var>

public class stmt(){
	lex();
	if(nextToken!=VAR_CODE){
		error();
	}else{
		lex();
		if(nextToken!=EQUAL_CODE){
			error();
		}else{
			A();
		}
	}
}

public class A(){
	if(nextToken!=NUM_CODE || nextToken!=VAR_CODE){
			error();
	}else{
		lex();
		while(nextToken!=ENDOFEXPR_CODE){
			B();
			lex();
			if(nextToken!=NUM_CODE || nextToken!=VAR_CODE){
				error();
			}
		}
	}
}

public class B(){
	lex();
	if(nextToken!=DIVISION_CODE || nextToken!=MUL_CODE || nextToken!=MOD_CODE){
		C();
	}
}

public class C(){
	lex();
	if(nextToken!=PLUS_CODE || nextToken!=MINUS_CODE){
		error();
	}
}

